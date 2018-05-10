package com.cg.baseproject.configs;

public interface IConstants {
    /**
     * 加载界面的三种状态
     */
    String STATE_LOADING = "loading";
    String STATE_SUCCESSED = "success";
    String STATE_FAILED = "failed";
    /**
     * 根据资源id跳转的界面
     */
    int FIND_CARPORT_LIST = 1;// 找车场
    int PARKING_DETAIL = 2;// 找车场列表的详情
    int CARPORT_DETAIL = 3;// 车位的详情
    int NAVI_FRAGMENT = 4;// 导航界面
    int SEARCH_MAP = 5;// 地图搜索界面

    /**
     * 界面中的一些常亮
     */
    int MAX_ITEM_LOAD_MORE = 5;// 当首次请求数据超过条后开启加载更多功能
    int PAGER_ROWS = 7;// 每一页的数据
    String EXIT_APP = "exit_app";// 退出登陆
}
