package com.carlosfu.mobil.enums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 算法枚举
 * User: yijunzhang
 * Date: 13-11-25
 * Time: 下午5:03
 */
public enum ArithmeticEnum {
    /**
     * 实时个性化
     */
    USER_ONLINE("on", 1),
    /**
     * 协同个性化
     */
    USER_CF("cf", 3),
    /**
     * 矩阵个性化
     */
    USER_MATRIX("ma", 5),
    /**
     * 追剧
     */
    USER_CHASE("ch", 7),

    /**
     * dirty用户
     */
    USER_DIRTY("da",8),

    /**
     * 播客视频矩阵
     */
    VIDEO_MY_MATRIX("my_matrix", 9),
    VIDEO_MY_SMALL_MATRIX("my_small_matrix", 19),
    /**
     * 卡通视频矩阵
     */
    CARTOON_VIDEO_MATRIX("cartoon_matrix", 10),
    /**
     * 版本id转换视频id
     */
    VIDEO_VER_ID2TV_ID("ver_id2tv_id", 11),

    /** 热点视频 */
    VIDEO_HOT("video_hot", 12),
    /** 处理高危视频或者临时数据 */
    DIRTY("dirty", 13),
    /** 保存动漫专辑替换的结果 */
    CARTOON_PLAYLIST_REPLACE("cartoon_playlist_replace", 14),
    /** 对dirtyDiscoverVids进行分组 */
    DIRTY_DISCOVER_GROUPVIDS("dirty_discover_groupvids", 15),
    /** 高危视频分组 */
    GROUP_VID("group_vid", 16),
    /** 风月片 */
    H_MOVIES("h_movies", 17),
    /** 风月片 */
    HQ_USER_HOT_VIDEO("hq_user_hot_video", 18),
    /** hq_user */
    HQ_USER("hq_user",21),

    /** 机器学习 */
    MEMCACHE_LEARNING_KEY("learning_key",22),

    /** 机器学习 */
    MEMCACHE_LEARNING_KEY2("learning_key2",23),
    /** 基于用户的专辑替换 */
    USER_PLAYLIST_REPLACE("upr",24),
    /** 基于视频 电视剧的反馈 */
    DRAMA_FEEDBACK("drama_feedback",25),
    /** 基于视频 电视剧的矩阵 */
    DRAMA_MATRIX("drama_matrix",26),

    CARTOON_UGC("cartoon_ugc", 27),
    /** ugc针对用户的过滤 */

    UGC_FILTER("ugc_filter", 28),

    /** ugc的福利片 */
    UGC_BOON("ugc_boon", 29),

    /** ugc的福利片补充 */
    UGC_BOON_SUPPLY("ugc_boon_supply", 30),

    /** ugc的ctr feedback加实时矩阵结果 */
    UGC_ONLINE_MATRIX_FUNNY("ugc_online_matrix_funny", 31),

    /** ugc小矩阵结果 */
    UGC_SMALL_MATRIX_FUNNY("ugc_small_matrix_funny", 32),

    /** ugc机器学习结果 */
    UGC_MACHINE_LEARNING_FUNNY("ugc_machine_learning_funny", 33),

    /** ugc机器学习结果 */
    UGC_BIG_MATRIX_FUNNY("ugc_big_matrix_funny", 34),

    /** ugc热点结果 */
    UGC_HOT_FUNNY("ugc_hot_funny", 35),

    /** ugc dirty视频集 */
    UGC_DIRTY_VID("ugc_dirty_vid", 36),

    /** ugc dirty视频集 */
    TOPIC_TARGET_MATCH("topic_target_match", 37),
    VIDEO_MY_SMALL_MATRIX_FUNNY("my_small_matrix_funny", 38),

    MY_DIRTY_DISCOVER_LIST1("mydirtylist1", 39),

    MY_DIRTY_DISCOVER_LIST2("mydirtylist2", 40),

    MY_LEARNING_HOT("learning_hot", 41),

    VIDEO_MY_ONLINE_MATRIX("my_online_matrix", 42),

    VIDEO_MY_ONLINE_MATRIX_OLD("my_online_matrix_old", 43),

    UGC_ONLINE_MATRIX_FUNNY_OLD("ugc_online_matrix_funny_old", 44),

    UGC_CLASSIFY_TRAIN("ugc_classify_train", 45),

    UGC_CLASSIFY_CATECODE("ugc_classify_catecode", 46),

    UGC_GAME_FEEDBACK("ugc_game_feedback", 47),

    UGC_GAME_SMALL_MATRIX("ugc_game_small_matrix", 48),

    NEWS_CTR_FEEDBACK("news_ctr_feedback", 49),

    NEWS_SMALL_MATRIX("news_small_matrix", 50),

    NEWS_BIG_MATRIX("news_big_matrix", 51),

    UGC_CLASSIFY_TRAIN_TEST("ugc_classify_train_test", 52),
    
    //VRS动漫没有的片子
    CARTOON_UGC_NOT_IN_VRS("cartoon_ugc_not_in_vrs", 53),
    
    //VRS动漫相关的片段
    CARTOON_UGC_RELATED_TO_VRS("cartoon_ugc_related_to_vrs", 54),
    
    //体育正反馈
    SPORTS_CTR_FEEDBACK("sports_ctr_feedback", 55),

    //体育小矩阵
    SPORTS_SMALL_MATRIX("sports_small_matrix", 56),

    //体育大矩阵
    SPORTS_BIG_MATRIX("sports_big_matrix", 57),

    UGC_SELF_MEDIA("ugc_self_media", 58),

    UGC_FEATURE_MODEL("ugc_feature_model", 59),
    
    // ott大矩阵
    OTT_BIG_MATRIX("ott_big_matrix", 60),
    
    // ott小矩阵
    OTT_SMALL_MATRIX("ott_small_matrix", 61),

    UGC_FEATURE_MODEL1("ugc_feature_model1", 62),

    UGC_FEATURE_MODEL2("ugc_feature_model2", 63),

    UGC_VISIT_FREQUENCY("ugc_visit_frequency", 64),

    UGC_VISIT_FREQUENCY_RELATED("ugc_visit_frequency_related", 65),

    UGC_VISIT_FREQUENCY_ID("ugc_visit_frequency_id", 66),

    UGC_VISIT_FREQUENCY_CATEGORY_KEYWORD("ugc_visit_frequency_category_keyword", 67),

    UGC_KEYWORD_CVID("ugc_keyword_cvid", 68),

    USER_VF("vf", 69),

    UGC_FEATURE_MODEL3("ugc_feature_model3", 70),
    
    // 电视剧右侧tab相关专辑推荐
    DRAMA_TAB_PLAYLIST_RELATED("drama_tab_related_playlist", 71),
    //强推池子
    UGC_MUST_REC("ugc_must_rec", 72),
    //内容相关
    USER_CR("cr", 73),
    //用户画像
    USER_PR("pf", 74),
    UGC_TOPIC_CODE_CVID("ugc_topic_code_cvid", 75),
    UGC_FEATURE_MODEL_KEYWORD_PAIR("ugc_feature_model_keyword_pair", 76),
    //kis系列
    USER_KIS_SERIAL("user_kis_serial", 77),
    UGC_FEATURE_MODEL_KEYWORD_PAIR34("ugc_feature_model_keyword_pair34", 78),
    UGC_FEATURE_MODEL_KEYWORD_PAIR36("ugc_feature_model_keyword_pair36", 79),
    UGC_PGC("ugc_pgc", 80),
    UGC_VID_CATEID("ugc_vid_cateid", 81),
    USER_PROFILE("up", 82),
    UGC_TOPIC_CODE_CVID_NEW("ugc_topic_code_cvid_new", 83),
    CATECODE_PROFILE("catecode_profile", 84),
    MIX_MATRIX("mix_matrix", 85),
    VID_2_PID("vid_2_pid", 86),
    //TV首页负反馈
    TV_HOME_NAVIGATE_FEEDBACK("nfb", 87);

    private String value;

    private int index;

    private ArithmeticEnum(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public String value() {
        return value;
    }

    public int index() {
        return index;
    }

//    public static ArithmeticEnum transferOf(String value) {
//        if (value == null || "".equals(value.trim())) {
//            return null;
//        }
//        for (ArithmeticEnum arithmeticEnum : ArithmeticEnum.values()) {
//            if (arithmeticEnum.value.equals(value)) {
//                return arithmeticEnum;
//            }
//        }
//        return null;
//    }

    public static ArithmeticEnum indexOf(int index) {
        if (index == 0) {
            return null;
        }
        for (ArithmeticEnum arithmeticEnum : ArithmeticEnum.values()) {
            if (arithmeticEnum.index == index) {
                return arithmeticEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ArithmeticEnum{" +
                "value='" + value + '\'' +
                '}';
    }

    public static List<String> getValueList(List<ArithmeticEnum> arithmeticEnumList) {
        if (arithmeticEnumList == null || arithmeticEnumList.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<String>();
        for (ArithmeticEnum a : arithmeticEnumList) {
            if (a != null) {
                list.add(a.value());
            }
        }
        return list;
    }

    /**
     * 获取用户相关算法枚举
     *
     * @return  算法列表
     */
    public static List<ArithmeticEnum> getUserValues() {
        List<ArithmeticEnum> list = new ArrayList<ArithmeticEnum>();
        list.add(USER_ONLINE);
        list.add(USER_CF);
        list.add(USER_MATRIX);
        list.add(USER_CHASE);
        list.add(USER_CR);
        list.add(USER_PR);
        //电影负反馈
        list.add(UGC_FILTER);
        return list;
    }

    /**
     * 获取视频相关算法枚举
     *
     * @return 算法列表
     */
    public static List<ArithmeticEnum> getVideoValues() {
        List<ArithmeticEnum> list = new ArrayList<ArithmeticEnum>();
        list.add(VIDEO_MY_MATRIX);
        list.add(VIDEO_VER_ID2TV_ID);
        return list;
    }

    /*public static ArithmeticEnum value(String arithmeticCode){
        if(arithmeticCode == null){
            return null;
        }
        for(ArithmeticEnum a : ArithmeticEnum.values()){
            if(a.value.equals(arithmeticCode)){
                return a;
            }
        }
        return null;
    }*/

}
