package com.niit.esports.entity;

import java.util.Date;

public class Team {
    private int teamId;
    private String teamName;
    private String region;
    private String logoUrl;
    private Date createdAt;

    // 构造函数
    public Team() {}

    public Team(int teamId, String teamName, String region, String logoUrl, Date createdAt) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.region = region;
        this.logoUrl = logoUrl;
        this.createdAt = createdAt;
    }

    // Getter和Setter
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId='" + teamId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", region='" + region + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}