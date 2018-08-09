package com.creator.waterweather.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class City {

    @Id(autoincrement = true)
    private long cityId;
    private String name;
    private String parentCity;
    private String adminArea;
    private String country;
    private boolean selected;
    private int priority;
    @Generated(hash = 475166051)
    public City(long cityId, String name, String parentCity, String adminArea,
            String country, boolean selected, int priority) {
        this.cityId = cityId;
        this.name = name;
        this.parentCity = parentCity;
        this.adminArea = adminArea;
        this.country = country;
        this.selected = selected;
        this.priority = priority;
    }
    @Generated(hash = 750791287)
    public City() {
    }
    public long getCityId() {
        return this.cityId;
    }
    public void setCityId(long cityId) {
        this.cityId = cityId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getParentCity() {
        return this.parentCity;
    }
    public void setParentCity(String parentCity) {
        this.parentCity = parentCity;
    }
    public String getAdminArea() {
        return this.adminArea;
    }
    public void setAdminArea(String adminArea) {
        this.adminArea = adminArea;
    }
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public boolean getSelected() {
        return this.selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public int getPriority() {
        return this.priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
}
