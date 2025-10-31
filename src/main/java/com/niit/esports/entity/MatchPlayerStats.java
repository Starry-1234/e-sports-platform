package com.niit.esports.entity;

import java.math.BigDecimal;

public class MatchPlayerStats {
    private String statsId;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private BigDecimal goldEarned;
    private BigDecimal damageDealt;
    private BigDecimal damageTaken;
    private BigDecimal visionScore;
    private String matchId;
    private String playerId;
    private String heroId;
    
    // 关联对象
    private Match match;
    private Player player;
    private Hero hero;

    public MatchPlayerStats() {
    }

    // Getters and Setters
    public String getStatsId() {
        return statsId;
    }

    public void setStatsId(String statsId) {
        this.statsId = statsId;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public BigDecimal getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(BigDecimal goldEarned) {
        this.goldEarned = goldEarned;
    }

    public BigDecimal getDamageDealt() {
        return damageDealt;
    }

    public void setDamageDealt(BigDecimal damageDealt) {
        this.damageDealt = damageDealt;
    }

    public BigDecimal getDamageTaken() {
        return damageTaken;
    }

    public void setDamageTaken(BigDecimal damageTaken) {
        this.damageTaken = damageTaken;
    }

    public BigDecimal getVisionScore() {
        return visionScore;
    }

    public void setVisionScore(BigDecimal visionScore) {
        this.visionScore = visionScore;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getHeroId() {
        return heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public String toString() {
        return "MatchPlayerStats{" +
                "statsId='" + statsId + '\'' +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", assists=" + assists +
                ", goldEarned=" + goldEarned +
                ", damageDealt=" + damageDealt +
                ", damageTaken=" + damageTaken +
                ", visionScore=" + visionScore +
                ", matchId='" + matchId + '\'' +
                ", playerId='" + playerId + '\'' +
                ", heroId='" + heroId + '\'' +
                '}';
    }
}