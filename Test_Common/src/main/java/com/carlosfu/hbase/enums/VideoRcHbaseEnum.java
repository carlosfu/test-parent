package com.carlosfu.hbase.enums;

/**
 * VedioRc
 * User: yijunzhang
 * Date: 13-11-26
 * Time: 下午4:26
 */
public enum VideoRcHbaseEnum {
    TABLE("rc_video_new"),
    TABLE_FILTER("rc_video_filter"),
    FAMILY("f"),
    KEY_TYPE_RC_VIDEO_FILTER_LIST("rc_video_filter_list"),
    KEY_TYPE_RC_VIDEO_FILTER_SET_MAP("rc_video_filter_set_map"),
    KEY_TYPE_RC_VIDEO_FILTER_SET("rc_video_filter_set_set"),
    KEY_TYPE_RC_VIDEO_VID_GROUP("rc_video_vid_group"),
    KEY_TYPE_RC_VIDEO_UGC_CLASSIFY_TRAIN("ugc_classify_train"),
    KEY_TYPE_RC_VIDEO_UGC_CLASSIFY_CATECODE("ugc_classify_catecode"),
    KEY_TYPE_RC_VIDEO_UGC_CLASSIFY_VID_CATEID("ugc_classify_vid_cateid"),
    KEY_TYPE_RC_VIDEO_UGC_SELF_MEDIA("ugc_self_media"),
    KEY_TYPE_USER_PROFILE_DICT("user_profile_dict"),
    KEY_TYPE_UGC_VID_2_PID("ugc_vid_2_pid");

    private String value;

    private VideoRcHbaseEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
