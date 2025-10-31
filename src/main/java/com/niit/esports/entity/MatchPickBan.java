package com.niit.esports.entity;

public class MatchPickBan {
    private String pickBanId;
    private String heroId;
    private String action;  // PICK or BAN
    private Integer turn;
    private String matchId;
    private String teamId;

    // 关联对象
    private Hero hero;
    private Team team;
    private Match match;
    
    // 对局信息
    private Integer roundNumber;

    // 构造函数
    public MatchPickBan() {}

    public MatchPickBan(String pickBanId, String heroId, String action, Integer turn,
                        String matchId, String teamId) {
        this.pickBanId = pickBanId;
        this.heroId = heroId;
        this.action = action;
        this.turn = turn;
        this.matchId = matchId;
        this.teamId = teamId;
    }

    // Getter和Setter
    public String getPickBanId() {
        return pickBanId;
    }

    public void setPickBanId(String pickBanId) {
        this.pickBanId = pickBanId;
    }

    public String getHeroId() {
        return heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
    
    public Integer getRoundNumber() {
        return roundNumber;
    }
    
    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    // 工具方法：判断是否是选择
    public boolean isPick() {
        return "PICK".equalsIgnoreCase(action);
    }

    // 工具方法：判断是否是禁用
    public boolean isBan() {
        return "BAN".equalsIgnoreCase(action);
    }

    // 工具方法：获取行动描述
    public String getActionDescription() {
        return isPick() ? "选择" : "禁用";
    }
    
    // 工具方法：获取行动CSS类
    public String getActionBadgeClass() {
        return isPick() ? "badge bg-info" : "badge bg-warning text-dark";
    }

    @Override
    public String toString() {
        return "MatchPickBan{" +
                "pickBanId='" + pickBanId + '\'' +
                ", heroId='" + heroId + '\'' +
                ", action='" + action + '\'' +
                ", turn=" + turn +
                ", matchId='" + matchId + '\'' +
                ", teamId='" + teamId + '\'' +
                ", roundNumber=" + roundNumber +
                '}';
    }
}