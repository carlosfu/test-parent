package com.carlosfu.mobil.enums;

import java.util.*;

/**
 * 频道类型列表
 * User: yijunzhang
 * Date: 13-11-25
 * Time: 下午4:48
 */
public enum ChannelEnum {

    /**
     * 电影
     */
    MOVIE_CATECODE("100"),
    /**
     * 电视剧
     */
    DRAMA_CATECODE("101"),
    /**
     * 资讯
     */
    NEWS_CATECODE("122"),
    /**
     * 体育
     */
    SPORTS_CATECODE("1000"),
    /**
     * 综艺
     */
    VARIETY_CATECODE("106"),
    /**
     * 娱乐新闻
     */
    ENTERTAINMENT_CATECODE("112"),
    /**
     * 卡通
     */
    CARTOON_CATECODE("115"),

    /**
     * 播客的catecode
     */
    MY_CATECODE("900"),
    /**
     * 精品,搞笑UGC
     */
    BOUTIQUE_MY_CATECODE("900133"),

    /**
     * 过滤的精品UGC
     */
    CLEAN_BOUTIQUE_MY_CATECODE("900910"),


    GAME_MY_CATECODE("900128"),
    /**
     * 搞笑
     */
    RELAX_CATECODE("133"),
    /**
     * 音乐
     */
    MUSIC_CATECODE("121"),
    /**
     * 纪录片
     */
    DOCUMENTARY_CATECODE("107"),

    OTT_CATECODE("ott"),

    /**
     * 自媒体热点
     */
    WE_MEDIA_HOT_CATECODE("9005"),

    WE_MEDIA_NEW_CATECODE("900_new"),

    //偶像剧
    DRAMA_100("101100"),

    //家庭剧
    DRAMA_101("101101"),

    //历史剧
    DRAMA_102("101102"),

    //年代剧
    DRAMA_103("101103"),

    //言情剧
    DRAMA_104("101104"),

    //武侠剧
    DRAMA_105("101105"),

    //古装剧
    DRAMA_106("101106"),

    //都市剧
    DRAMA_107("101107"),

    //农村剧
    DRAMA_108("101108"),

    //军旅剧
    DRAMA_109("101109"),

    //刑侦剧
    DRAMA_110("101110"),

    //喜剧
    DRAMA_111("101111"),

    //悬疑剧
    DRAMA_112("101112"),

    //情景剧
    DRAMA_113("101113"),

    //传记剧
    DRAMA_114("101114"),

    //科幻剧
    DRAMA_115("101115"),

    //动画片
    DRAMA_116("101116"),

    //动作剧
    DRAMA_117("101117"),

    //真人秀
    DRAMA_118("101118"),

    //栏目剧
    DRAMA_119("101119"),

    //谍战剧
    DRAMA_120("101120"),

    //伦理剧
    DRAMA_121("101121"),

    //战争剧
    DRAMA_122("101122"),

    //神话剧
    DRAMA_123("101123"),

    //惊悚剧
    DRAMA_124("101124"),

    //剧情片
    DRAMA_127("101127"),
    
    //ifox弹窗电影
    MOVIE_CATECODE_N("100n"),

    //主题热点
    TOPIC_CODE_900("topic_code_900"),
    
    //tv首页混合追剧
    TV_HOME_USR_CHASE("m"),

    //tv首页混合用户画像
    TV_HOME_USR_PROFILE("106_112");

    public static Map<String, ChannelEnum> MAP = new HashMap<String, ChannelEnum>();

    static {
        for (ChannelEnum c : ChannelEnum.values()) {
            MAP.put(c.value(), c);
        }
    }

    /**
     * 有热点视频的频道

    public static final ChannelEnum[] HOT_VDIEOS = new ChannelEnum[]{
            MOVIE_CATECODE, DRAMA_CATECODE, VARIETY_CATECODE,
            DOCUMENTARY_CATECODE, ENTERTAINMENT_CATECODE,
            CARTOON_CATECODE, MUSIC_CATECODE, ENTERTAINMENT_CATECODE,
            MY_CATECODE, SPORTS_CATECODE
    };*/

    /**
     * ifox弹窗第四个tab页的频道列表

    public static final ChannelEnum[] IFOX_WINDOW_CHANNELS = new ChannelEnum[]{
            VARIETY_CATECODE, CARTOON_CATECODE,
            ENTERTAINMENT_CATECODE, MY_CATECODE
    };*/

    /**
     * ifox弹窗第四个tab页的频道代码列表

    public static final String[] IFOX_WINDOW_CHANNELS_STRING = new String[]{
            VARIETY_CATECODE.value(), CARTOON_CATECODE.value(),
            ENTERTAINMENT_CATECODE.value(), MY_CATECODE.value()
    };*/

    /**
     * ifox弹窗第四个tab页的频道代码列表

    public static final ChannelEnum[] IFOX_WINDOW_MANUAL_CHANNELS = new ChannelEnum[]{
            DRAMA_CATECODE, NEWS_CATECODE
    };*/

    private String value;

    private ChannelEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    /**
    public static List<ChannelEnum> getSpaceChannel() {
        List<ChannelEnum> list = new ArrayList<ChannelEnum>();
        list.add(NEWS_CATECODE);
        list.add(ENTERTAINMENT_CATECODE);
        list.add(MY_CATECODE);
        list.add(DRAMA_CATECODE);
        list.add(VARIETY_CATECODE);
        list.add(MOVIE_CATECODE);
        list.add(DOCUMENTARY_CATECODE);
        list.add(CARTOON_CATECODE);
        list.add(MUSIC_CATECODE);
        return list;
    }*/

    public static List<String> getValueList(List<ChannelEnum> channelEnumList) {
        if (channelEnumList == null || channelEnumList.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<String>();
        for (ChannelEnum c : channelEnumList) {
            if (c != null) {
                list.add(String.valueOf(c.value));
            }
        }
        return list;
    }

    /**
     * 提取历史保留pid的频道列表
     */
    public static final Set<String> HAS_PID = new HashSet<String>();

    static {
        HAS_PID.add(DRAMA_CATECODE.value());
        HAS_PID.add(VARIETY_CATECODE.value());
        HAS_PID.add(CARTOON_CATECODE.value());
        HAS_PID.add(DOCUMENTARY_CATECODE.value());
    }


    public static ChannelEnum indexOf(String channelCode) {
        if (channelCode == null) {
            return null;
        }
        for (ChannelEnum channelEnum : ChannelEnum.values()) {
            if (channelEnum.value.equals(channelCode)) {
                return channelEnum;
            }
        }
        return null;
    }

    /**
     * 电视剧二级分类列表
     */
    public static final Set<String> DARAM_SECONDE_CATE = new HashSet<String>();

    static {
        DARAM_SECONDE_CATE.add(DRAMA_100.value());
        DARAM_SECONDE_CATE.add(DRAMA_101.value());
        DARAM_SECONDE_CATE.add(DRAMA_102.value());
        DARAM_SECONDE_CATE.add(DRAMA_103.value());
        DARAM_SECONDE_CATE.add(DRAMA_104.value());
        DARAM_SECONDE_CATE.add(DRAMA_105.value());
        DARAM_SECONDE_CATE.add(DRAMA_106.value());
        DARAM_SECONDE_CATE.add(DRAMA_107.value());
        DARAM_SECONDE_CATE.add(DRAMA_108.value());
        DARAM_SECONDE_CATE.add(DRAMA_109.value());
        DARAM_SECONDE_CATE.add(DRAMA_110.value());
        DARAM_SECONDE_CATE.add(DRAMA_111.value());
        DARAM_SECONDE_CATE.add(DRAMA_112.value());
        DARAM_SECONDE_CATE.add(DRAMA_113.value());
        DARAM_SECONDE_CATE.add(DRAMA_114.value());
        DARAM_SECONDE_CATE.add(DRAMA_115.value());
        DARAM_SECONDE_CATE.add(DRAMA_116.value());
        DARAM_SECONDE_CATE.add(DRAMA_117.value());
        DARAM_SECONDE_CATE.add(DRAMA_118.value());
        DARAM_SECONDE_CATE.add(DRAMA_119.value());
        DARAM_SECONDE_CATE.add(DRAMA_120.value());
        DARAM_SECONDE_CATE.add(DRAMA_121.value());
        DARAM_SECONDE_CATE.add(DRAMA_122.value());
        DARAM_SECONDE_CATE.add(DRAMA_123.value());
        DARAM_SECONDE_CATE.add(DRAMA_124.value());
        DARAM_SECONDE_CATE.add(DRAMA_127.value());
    }

    /**
     * 二级catecode的中文
     */
    public static final Map<String, String> DARAM_SECONDE_ZH = new HashMap<String, String>();

    static {
        DARAM_SECONDE_ZH.put(DRAMA_100.value(), "偶像剧");
        DARAM_SECONDE_ZH.put(DRAMA_101.value(), "家庭剧");
        DARAM_SECONDE_ZH.put(DRAMA_102.value(), "历史剧");
        DARAM_SECONDE_ZH.put(DRAMA_103.value(), "年代剧");
        DARAM_SECONDE_ZH.put(DRAMA_104.value(), "言情剧");
        DARAM_SECONDE_ZH.put(DRAMA_105.value(), "武侠剧");
        DARAM_SECONDE_ZH.put(DRAMA_106.value(), "古装剧");
        DARAM_SECONDE_ZH.put(DRAMA_107.value(), "都市剧");
        DARAM_SECONDE_ZH.put(DRAMA_108.value(), "农村剧");
        DARAM_SECONDE_ZH.put(DRAMA_109.value(), "军旅剧");
        DARAM_SECONDE_ZH.put(DRAMA_110.value(), "刑侦剧");
        DARAM_SECONDE_ZH.put(DRAMA_111.value(), "喜剧");
        DARAM_SECONDE_ZH.put(DRAMA_112.value(), "悬疑剧");
        DARAM_SECONDE_ZH.put(DRAMA_113.value(), "情景剧");
        DARAM_SECONDE_ZH.put(DRAMA_114.value(), "传记剧");
        DARAM_SECONDE_ZH.put(DRAMA_115.value(), "科幻剧");
        DARAM_SECONDE_ZH.put(DRAMA_116.value(), "动画片");
        DARAM_SECONDE_ZH.put(DRAMA_117.value(), "动作剧");
        DARAM_SECONDE_ZH.put(DRAMA_118.value(), "真人秀");
        DARAM_SECONDE_ZH.put(DRAMA_119.value(), "栏目剧");
        DARAM_SECONDE_ZH.put(DRAMA_120.value(), "谍战剧");
        DARAM_SECONDE_ZH.put(DRAMA_121.value(), "伦理剧");
        DARAM_SECONDE_ZH.put(DRAMA_122.value(), "战争剧");
        DARAM_SECONDE_ZH.put(DRAMA_123.value(), "神话剧");
        DARAM_SECONDE_ZH.put(DRAMA_124.value(), "惊悚剧");
        DARAM_SECONDE_ZH.put(DRAMA_127.value(), "剧情片");
    }

    /**
     * 根据categoryCode获取ChannelEnum
     * @Time 2014年5月15日
     * @param defaultChannelEnum 默认值
     */
    public static ChannelEnum getChannelEnum(String categoryCode, ChannelEnum defaultChannelEnum) {
        ChannelEnum currentChannelEnum = defaultChannelEnum;
        if (categoryCode != null && !categoryCode.equals("")) {
            ChannelEnum ruleChannelEnum = ChannelEnum.MAP.get(categoryCode);
            if (ruleChannelEnum != null) {
                currentChannelEnum = ruleChannelEnum;
            }
        }
        return currentChannelEnum;
    }
 }
