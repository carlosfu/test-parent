package com.carlosfu.redis.book.inaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

/**
 * 文章发表，投票，展示， 打标签
 * 
 * @author leifu
 * @Date 2014-11-5
 * @Time 下午10:17:10
 */
public class Chapter01 {
	private static final int ONE_WEEK_IN_SECONDS = (int) TimeUnit.DAYS.toSeconds(7);
	
	/**
	 * 文章默认分数
	 */
	private static final int VOTE_SCORE = 1;
	
	/**
	 * 文章分页数
	 */
	private static final int ARTICLES_PER_PAGE = 25;

	/**
	 * 文章id生成器
	 */
	private static final String article_increment_id = "article:increment.id";

	/**
	 * 所有的投票用集合类表示(+文章号，代表每篇文章的投票人集合)
	 */
	private static final String vote_user_base_set = "article:votes";
	
	/**
	 * 文章map的基准key
	 */
	private static final String article_hmap_base_key = "article:";
	
	/**
	 * 文章按照分数排序
	 */
	private static final String article_score_zset_base_key = "score:";
	
	/**
	 * 文章按照时间排序
	 */
	private static final String article_time_zset_base_key = "time:"; 

	/**
	 * 文章分组基准key
	 */
	private static final String article_group_base_key = "group:";
	
	private Jedis jedis;

	public Chapter01() {
		jedis = new Jedis(RedisInActionConstants.REDIS_SINGLE_HOST);
	}

	public static final void main(String[] args) {
		new Chapter01().run();
	}

	public void run() {
		// 1.1 发表一篇文章
//		String articleId = postArticle("username", "A title",
//				"http://www.google.com");
		// 一篇文章的key
		String acticleKey = article_hmap_base_key + "1";
		
		// 1.2 展示一篇文章
		showArticle(acticleKey);

		
		// 2.1 其他人为文章投票
		articleVote("hehe", acticleKey);
		/*
		// 2.2 获取新的文章投票数
		String votes = jedis.hget(acticleKey, "votes");
		System.out.println("We voted for the article, it now has votes: "
				+ votes);
		assert Integer.parseInt(votes) > 1;

		// 3.1获取分数最高文章
		System.out.println("The currently highest-scoring articles are:");
		List<Map<String, String>> articles = getArticles(1);
		printArticles(articles);
		assert articles.size() >= 1;

		
		String group = "new-group";
		// articleId: 形如123
		// 4.1 为文章添加分组,也可以理解成打标签，例如java权威指南(可以添加java和计算机两个分组)
		addGroups(articleId, new String[] { group });
		System.out.println("We added the article to a new group, other articles include:");
		articles = getGroupArticles(group, 1, article_score_zset_base_key);
		printArticles(articles);
		assert articles.size() >= 1;
		*/
	}

	/**
	 * 展示一篇文章
	 * @param articleId
	 */
	private void showArticle(String articleKey) {
		System.out.println("We posted a new article with id: " + articleKey);
		System.out.println("Its HASH looks like:");
		Map<String, String> articleData = jedis.hgetAll(articleKey);
		for (Map.Entry<String, String> entry : articleData.entrySet()) {
			System.out.println("  " + entry.getKey() + ": " + entry.getValue());
		}
	}

	/**
	 * 生成文章自增id
	 * @return
	 */
	private String getIncrementActicleId(){
		return String.valueOf(jedis.incr(article_increment_id));
	}
	
	/**
	 * 发表文章
	 * @param user 用户名
	 * @param title 文章名
	 * @param link 文章链接
	 * @return
	 */
	public String postArticle(String user, String title, String link) {
		// 生成文章自增id
		String articleId = getIncrementActicleId();

		// 默认给自己投一票(每篇文章的投票者用一个集合表示)
		String voted = vote_user_base_set + articleId;
		jedis.sadd(voted, user);
		jedis.expire(voted, ONE_WEEK_IN_SECONDS);

		// 用hash结果表示一篇文章, 有点像mysql中的一行，hash的key对应着列名，value是对应的值, 用id作为hash的key
		long now = System.currentTimeMillis() / 1000;
		String article = article_hmap_base_key + articleId;
		HashMap<String, String> articleData = new HashMap<String, String>();
		articleData.put("title", title);
		articleData.put("link", link);
		articleData.put("user", user);
		articleData.put("now", String.valueOf(now));
		articleData.put("votes", "1");
		jedis.hmset(article, articleData);

		// 两个排序, 按照分数和日期给文章排序
		jedis.zadd(article_score_zset_base_key, VOTE_SCORE, article);
		jedis.zadd(article_time_zset_base_key, now, article);

		return articleId;
	}

	/**
	 * 用户给文章投票
	 * @param user
	 * @param article
	 */
	public void articleVote(String user, String article) {
		//一星期内投过票了，被视为废票
		long cutoff = (System.currentTimeMillis() / 1000) - ONE_WEEK_IN_SECONDS;
		if (jedis.zscore(article_time_zset_base_key, article) < cutoff) {
			return;
		}
		
		// 文章id
		String articleId = article.substring(article.indexOf(':') + 1);
		// 投票用户不重复
		if (jedis.sadd(vote_user_base_set + articleId, user) == 1) {
			// 更新分数的zset
			jedis.zincrby(article_score_zset_base_key, VOTE_SCORE, article);
			// 更新文章的投票数
			jedis.hincrBy(article, "votes", 1l);
		}
	}

	/**
	 * 按照分数排名
	 * @param page
	 * @return
	 */
	public List<Map<String, String>> getArticles(int page) {
		return getArticles(page, article_score_zset_base_key);
	}

	/**
	 * 
	 * @param page 分页
	 * @param order 按照什么顺序取
	 * @return
	 */
	public List<Map<String, String>> getArticles(int page, String order) {
		int start = (page - 1) * ARTICLES_PER_PAGE;
		int end = start + ARTICLES_PER_PAGE - 1;

		Set<String> ids = jedis.zrevrange(order, start, end);
		List<Map<String, String>> articles = new ArrayList<Map<String, String>>();
		for (String id : ids) {
			Map<String, String> articleData = jedis.hgetAll(id);
			articleData.put("id", id);
			articles.add(articleData);
		}
		return articles;
	}

	/**
	 * 为文章添加分组，也可以理解为打标签
	 * @param articleId
	 * @param toAdd
	 */
	public void addGroups(String articleId, String[] toAdd) {
		String article = article_hmap_base_key + articleId;
		for (String group : toAdd) {
			jedis.sadd(article_group_base_key + group, article);
		}
	}

	public List<Map<String, String>> getGroupArticles(String group, int page,
			String order) {
		String key = order + group;
		if (!jedis.exists(key)) {
			ZParams params = new ZParams().aggregate(ZParams.Aggregate.MAX);
			jedis.zinterstore(key, params, article_group_base_key + group, order);
			jedis.expire(key, 60);
		}
		return getArticles(page, key);
	}

	private void printArticles(List<Map<String, String>> articles) {
		for (Map<String, String> article : articles) {
			System.out.println("  id: " + article.get("id"));
			for (Map.Entry<String, String> entry : article.entrySet()) {
				if (entry.getKey().equals("id")) {
					continue;
				}
				System.out.println("    " + entry.getKey() + ": "
						+ entry.getValue());
			}
		}
	}
}
