package com.niit.esports.entity;

import java.util.Date;

public class Player {
    private String playerId;
    private String gameName;
    private String position;
    private String nationality;
    private Date birthDate;
    private Date createdAt;
    private String teamId;

    // 关联对象
    private Team team;

    // 构造函数
    public Player() {}

    public Player(String playerId, String gameName, String position, String nationality,
                  Date birthDate, Date createdAt, String teamId) {
        this.playerId = playerId;
        this.gameName = gameName;
        this.position = position;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.teamId = teamId;
    }

    // Getter和Setter
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    // 工具方法：计算年龄
    public Integer getAge() {
        if (birthDate == null) {
            return null;
        }
        Date now = new Date();
        long ageInMillis = now.getTime() - birthDate.getTime();
        return (int) (ageInMillis / (1000L * 60 * 60 * 24 * 365));
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId='" + playerId + '\'' +
                ", gameName='" + gameName + '\'' +
                ", position='" + position + '\'' +
                ", nationality='" + nationality + '\'' +
                ", birthDate=" + birthDate +
                ", createdAt=" + createdAt +
                ", teamId='" + teamId + '\'' +
                '}';
    }
}