package com.cg.baseproject.request.data;

/**
 * @author
 * @version 1.0
 * @date 2018/4/13
 */
public class DataBean {
    public String name;

    public String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "DataBean{" + "name='" + name + '\'' + ", phone='" + phone + '\'' + '}';
    }
}
