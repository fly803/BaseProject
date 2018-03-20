package com.cg.baseproject.rx.rxbus.event;

/**
 * @author
 * @version 1.0
 * @date 2017/6/20 0020
 */

public class Event {
    private String id;
    private String name;
    private String tips;

    public Event(String id, String name, String tips) {
        this.id = id;
        this.name = name;
        this.tips = tips;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
