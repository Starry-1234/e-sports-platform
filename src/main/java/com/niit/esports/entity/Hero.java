package com.niit.esports.entity;

public class Hero {
    private String heroId;
    private String heroName;
    private String role;
    private String avaterUrl;

    // 构造函数
    public Hero() {}

    public Hero(String heroId, String heroName, String role, String avaterUrl) {
        this.heroId = heroId;
        this.heroName = heroName;
        this.role = role;
        this.avaterUrl = avaterUrl;
    }

    // Getter和Setter
    public String getHeroId() {
        return heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatarUrl() {
        return avaterUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avaterUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "heroId='" + heroId + '\'' +
                ", heroName='" + heroName + '\'' +
                ", role='" + role + '\'' +
                ", avatarUrl='" + avaterUrl + '\'' +
                '}';
    }
}