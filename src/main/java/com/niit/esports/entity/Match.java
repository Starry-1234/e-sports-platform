package com.niit.esports.entity;

import java.util.Date;

public class Match {
    private String matchId;
    private Date matchDate;
    private String winnerTeamId;
    private String durationSeconds;
    private String patchVersion;
    private Date createdAt;
    private String teamAId;
    private String teamBId;

    // 关联对象
    private Team teamA;
    private Team teamB;
    private Team winnerTeam;

    // 构造函数
    public Match() {}

    public Match(String matchId, Date matchDate, String winnerTeamId, String durationSeconds,
                 String patchVersion, Date createdAt, String teamAId, String teamBId) {
        this.matchId = matchId;
        this.matchDate = matchDate;
        this.winnerTeamId = winnerTeamId;
        this.durationSeconds = durationSeconds;
        this.patchVersion = patchVersion;
        this.createdAt = createdAt;
        this.teamAId = teamAId;
        this.teamBId = teamBId;
    }

    // Getter和Setter
    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getWinnerTeamId() {
        return winnerTeamId;
    }

    public void setWinnerTeamId(String winnerTeamId) {
        this.winnerTeamId = winnerTeamId;
    }

    public String getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(String durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public String getPatchVersion() {
        return patchVersion;
    }

    public void setPatchVersion(String patchVersion) {
        this.patchVersion = patchVersion;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTeamAId() {
        return teamAId;
    }

    public void setTeamAId(String teamAId) {
        this.teamAId = teamAId;
    }

    public String getTeamBId() {
        return teamBId;
    }

    public void setTeamBId(String teamBId) {
        this.teamBId = teamBId;
    }

    public Team getTeamA() {
        return teamA;
    }

    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    // 工具方法：将秒数转换为分钟格式
    public String getFormattedDuration() {
        try {
            int totalSeconds = Integer.parseInt(durationSeconds);
            int minutes = totalSeconds / 60;
            int seconds = totalSeconds % 60;
            return String.format("%d分%d秒", minutes, seconds);
        } catch (NumberFormatException e) {
            return durationSeconds;
        }
    }

    // 工具方法：判断比赛是否已结束
    public boolean isFinished() {
        return winnerTeamId != null && !winnerTeamId.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId='" + matchId + '\'' +
                ", matchDate=" + matchDate +
                ", winnerTeamId='" + winnerTeamId + '\'' +
                ", durationSeconds='" + durationSeconds + '\'' +
                ", patchVersion='" + patchVersion + '\'' +
                ", createdAt=" + createdAt +
                ", teamAId='" + teamAId + '\'' +
                ", teamBId='" + teamBId + '\'' +
                '}';
    }
}