package com.niit.esports.entity;

public class Hero {
    private String heroId;
    private String heroName;
    private String role;
    private String avatarUrl;

    // 构造函数
    public Hero() {}

    public Hero(String heroId, String heroName, String role, String avatarUrl) {
        this.heroId = heroId;
        this.heroName = heroName;
        this.role = role;
        this.avatarUrl = avatarUrl;
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
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "heroId='" + heroId + '\'' +
                ", heroName='" + heroName + '\'' +
                ", role='" + role + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}