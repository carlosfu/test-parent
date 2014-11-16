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
 * 文章发表，投票，展示
 * 
 * @author leifu
 * @Date 2014-11-5
 * @Time 下午10:17:10
 */
public class Chapter01 {
	private static final int ONE_WEEK_IN_SECONDS = (int) TimeUnit.DAYS
			.toSeconds(7);
	private static final int VOTE_SCORE = 432;
	private static final int ARTICLES_PER_PAGE = 25;

	private Jedis jedis;

	public Chapter01() {
		jedis = new Jedis("10.16.15.86");
		jedis.select(15);
	}

	public static final void main(String[] args) {
		new Chapter01().run();
	}

	public void run() {

		// 发表一篇文章
		String articleId = postArticle("username", "A title",
				"http://www.google.com");
		// 展示一篇文章
		showArticle(articleId);

		// 其他人为文章投票
		articleVote("other_user", "article:" + articleId);
		String votes = jedis.hget("article:" + articleId, "votes");
		System.out.println("We voted for the article, it now has votes: "
				+ votes);
		assert Integer.parseInt(votes) > 1;

		 System.out.println("The currently highest-scoring articles are:");
		 List<Map<String, String>> articles = getArticles(1);
		 printArticles(articles);
		 assert articles.size() >= 1;
		
		 addGroups(articleId, new String[] { "new-group" });
		 System.out
		 .println("We added the article to a new group, other articles include:");
		 articles = getGroupArticles("new-group", 1);
		 printArticles(articles);
		 assert articles.size() >= 1;
	}

	private void showArticle(String articleId) {
		System.out.println("We posted a new article with id: " + articleId);
		System.out.println("Its HASH looks like:");
		Map<String, String> articleData = jedis.hgetAll("article:" + articleId);
		for (Map.Entry<String, String> entry : articleData.entrySet()) {
			System.out.println("  " + entry.getKey() + ": " + entry.getValue());
		}

	}

	/**
	 * 发表文章
	 * 
	 * @param user
	 *            用户名
	 * @param title
	 *            文章名
	 * @param link
	 *            文章链接
	 * @return
	 */
	public String postArticle(String user, String title, String link) {
		// 生成自增id
		String articleId = String.valueOf(jedis.incr("article:"));

		// 默认给自己投一票(每篇文章的投票者用一个集合表示)
		String voted = "voted:" + articleId;
		jedis.sadd(voted, user);
		jedis.expire(voted, ONE_WEEK_IN_SECONDS);

		// 用hash结果表示一篇文章, 有点像mysql中的一行，hash的key对应着列名，value是对应的值, 用id作为hash的key
		long now = System.currentTimeMillis() / 1000;
		String article = "article:" + articleId;
		HashMap<String, String> articleData = new HashMap<String, String>();
		articleData.put("title", title);
		articleData.put("link", link);
		articleData.put("user", user);
		articleData.put("now", String.valueOf(now));
		articleData.put("votes", "1");
		jedis.hmset(article, articleData);

		// 两个排序, 按照分数和日期给文章排序
		jedis.zadd("score:", now + VOTE_SCORE, article);
		jedis.zadd("time:", now, article);

		return articleId;
	}

	public void articleVote(String user, String article) {
		long cutoff = (System.currentTimeMillis() / 1000) - ONE_WEEK_IN_SECONDS;
		if (jedis.zscore("time:", article) < cutoff) {
			return;
		}

		String articleId = article.substring(article.indexOf(':') + 1);
		if (jedis.sadd("voted:" + articleId, user) == 1) {
			// 更新分数的zset
			jedis.zincrby("score:", VOTE_SCORE, article);
			// 更新文章的投票数
			jedis.hincrBy(article, "votes", 1l);
		}
	}

	public List<Map<String, String>> getArticles(int page) {
		return getArticles(page, "score:");
	}

	/**
	 * 
	 * @param page 分页并排序
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

	public void addGroups(String articleId, String[] toAdd) {
		String article = "article:" + articleId;
		for (String group : toAdd) {
			jedis.sadd("group:" + group, article);
		}
	}

	public List<Map<String, String>> getGroupArticles(String group, int page) {
		return getGroupArticles(group, page, "score:");
	}

	public List<Map<String, String>> getGroupArticles(String group, int page,
			String order) {
		String key = order + group;
		if (!jedis.exists(key)) {
			ZParams params = new ZParams().aggregate(ZParams.Aggregate.MAX);
			jedis.zinterstore(key, params, "group:" + group, order);
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
