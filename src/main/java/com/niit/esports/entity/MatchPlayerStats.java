package com.niit.esports.entity;

public class MatchPlayerStats {
    private String statsId;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private String goldEarned;
    private String damageDealt;
    private String damageTaken;
    private String visionScore;
    private String matchId;
    private String playerId;
    private String heroId;

    // 关联对象
    private Player player;
    private Hero hero;
    private Match match;

    // 构造函数
    public MatchPlayerStats() {}

    public MatchPlayerStats(String statsId, Integer kills, Integer deaths, Integer assists,
                            String goldEarned, String damageDealt, String damageTaken,
                            String visionScore, String matchId, String playerId, String heroId) {
        this.statsId = statsId;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.goldEarned = goldEarned;
        this.damageDealt = damageDealt;
        this.damageTaken = damageTaken;
        this.visionScore = visionScore;
        this.matchId = matchId;
        this.playerId = playerId;
        this.heroId = heroId;
    }

    // Getter和Setter
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

    public String getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(String goldEarned) {
        this.goldEarned = goldEarned;
    }

    public String getDamageDealt() {
        return damageDealt;
    }

    public void setDamageDealt(String damageDealt) {
        this.damageDealt = damageDealt;
    }

    public String getDamageTaken() {
        return damageTaken;
    }

    public void setDamageTaken(String damageTaken) {
        this.damageTaken = damageTaken;
    }

    public String getVisionScore() {
        return visionScore;
    }

    public void setVisionScore(String visionScore) {
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

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    // 工具方法：计算KDA
    public Double getKDA() {
        if (deaths == null || deaths == 0) {
            return kills != null ? kills.doubleValue() + (assists != null ? assists.doubleValue() : 0) : 0.0;
        }
        return (kills != null ? kills.doubleValue() : 0) +
                (assists != null ? assists.doubleValue() : 0) / deaths.doubleValue();
    }

    // 工具方法：格式化KDA显示
    public String getFormattedKDA() {
        return String.format("%d/%d/%d",
                kills != null ? kills : 0,
                deaths != null ? deaths : 0,
                assists != null ? assists : 0);
    }

    // 工具方法：计算参团率
    public Double getParticipationRate(Integer teamTotalKills) {
        if (teamTotalKills == null || teamTotalKills == 0) {
            return 0.0;
        }
        int playerKills = kills != null ? kills : 0;
        int playerAssists = assists != null ? assists : 0;
        return (playerKills + playerAssists) * 100.0 / teamTotalKills;
    }

    @Override
    public String toString() {
        return "MatchPlayerStats{" +
                "statsId='" + statsId + '\'' +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", assists=" + assists +
                ", goldEarned='" + goldEarned + '\'' +
                ", damageDealt='" + damageDealt + '\'' +
                ", damageTaken='" + damageTaken + '\'' +
                ", visionScore='" + visionScore + '\'' +
                ", matchId='" + matchId + '\'' +
                ", playerId='" + playerId + '\'' +
                ", heroId='" + heroId + '\'' +
                '}';
    }
}