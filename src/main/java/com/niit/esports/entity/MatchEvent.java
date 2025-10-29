package com.niit.esports.entity;

public class MatchEvent {

    // === 事件类型常量 ===
    public static final String EVENT_KILL = "KILL";
    public static final String EVENT_DEATH = "DEATH";
    public static final String EVENT_ASSIST = "ASSIST";
    public static final String EVENT_TOWER_DESTROY = "TOWER_DESTROY";
    public static final String EVENT_DRAGON_KILL = "DRAGON_KILL";
    public static final String EVENT_BARON_KILL = "BARON_KILL";
    public static final String EVENT_INHIBITOR_DESTROY = "INHIBITOR_DESTROY";
    public static final String EVENT_HERO_SWAP = "HERO_SWAP";
    public static final String EVENT_ITEM_PURCHASE = "ITEM_PURCHASE";
    public static final String EVENT_LEVEL_UP = "LEVEL_UP";

    // 获取所有事件类型的方法
    public static String[] getEventTypes() {
        return new String[]{
                EVENT_KILL,
                EVENT_DEATH,
                EVENT_ASSIST,
                EVENT_TOWER_DESTROY,
                EVENT_DRAGON_KILL,
                EVENT_BARON_KILL,
                EVENT_INHIBITOR_DESTROY,
                EVENT_HERO_SWAP,
                EVENT_ITEM_PURCHASE,
                EVENT_LEVEL_UP
        };
    }

    // 获取事件类型显示名称的方法
    public static String getEventTypeDisplayName(String eventType) {
        switch (eventType) {
            case EVENT_KILL:
                return "击杀";
            case EVENT_DEATH:
                return "死亡";
            case EVENT_ASSIST:
                return "助攻";
            case EVENT_TOWER_DESTROY:
                return "推塔";
            case EVENT_DRAGON_KILL:
                return "击杀巨龙";
            case EVENT_BARON_KILL:
                return "击杀男爵";
            case EVENT_INHIBITOR_DESTROY:
                return "摧毁水晶";
            case EVENT_HERO_SWAP:
                return "英雄交换";
            case EVENT_ITEM_PURCHASE:
                return "购买装备";
            case EVENT_LEVEL_UP:
                return "升级";
            default:
                return eventType;
        }
    }

    private String eventId;
    private String eventType;
    private String timestampSeconds;
    private String gold;
    private String heroId;
    private String matchId;
    private String playerId;
    private String targetPlayerId;

    // 关联对象
    private Hero hero;
    private Player player;
    private Player targetPlayer;
    private Match match;

    // 构造函数
    public MatchEvent() {}

    public MatchEvent(String eventId, String eventType, String timestampSeconds, String gold,
                      String heroId, String matchId, String playerId, String targetPlayerId) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.timestampSeconds = timestampSeconds;
        this.gold = gold;
        this.heroId = heroId;
        this.matchId = matchId;
        this.playerId = playerId;
        this.targetPlayerId = targetPlayerId;
    }

    // Getter和Setter
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTimestampSeconds() {
        return timestampSeconds;
    }

    public void setTimestampSeconds(String timestampSeconds) {
        this.timestampSeconds = timestampSeconds;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getHeroId() {
        return heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId;
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

    public String getTargetPlayerId() {
        return targetPlayerId;
    }

    public void setTargetPlayerId(String targetPlayerId) {
        this.targetPlayerId = targetPlayerId;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getTargetPlayer() {
        return targetPlayer;
    }

    public void setTargetPlayer(Player targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    // 工具方法：将秒数转换为分钟:秒格式
    public String getFormattedTime() {
        try {
            int totalSeconds = Integer.parseInt(timestampSeconds);
            int minutes = totalSeconds / 60;
            int seconds = totalSeconds % 60;
            return String.format("%02d:%02d", minutes, seconds);
        } catch (NumberFormatException e) {
            return timestampSeconds;
        }
    }

    // 工具方法：获取事件描述
    public String getEventDescription() {
        if (player == null) {
            return eventType;
        }

        String playerName = player.getGameName();
        String targetName = targetPlayer != null ? targetPlayer.getGameName() : "敌方";

        switch (eventType) {
            case "KILL":
                return playerName + " 击杀了 " + targetName;
            case "DEATH":
                return playerName + " 被击杀";
            case "ASSIST":
                return playerName + " 提供了助攻";
            case "TOWER_DESTROY":
                return playerName + " 摧毁了防御塔";
            case "DRAGON_KILL":
                return playerName + " 击杀了巨龙";
            case "BARON_KILL":
                return playerName + " 击杀了男爵";
            case "INHIBITOR_DESTROY":
                return playerName + " 摧毁了水晶";
            default:
                return playerName + " " + eventType;
        }
    }

    // 工具方法：获取事件图标类
    public String getEventIconClass() {
        switch (eventType) {
            case "KILL":
                return "fas fa-skull text-danger";
            case "DEATH":
                return "fas fa-times-circle text-dark";
            case "ASSIST":
                return "fas fa-handshake text-info";
            case "TOWER_DESTROY":
                return "fas fa-fort-awesome text-warning";
            case "DRAGON_KILL":
                return "fas fa-dragon text-success";
            case "BARON_KILL":
                return "fas fa-crown text-purple";
            default:
                return "fas fa-circle text-secondary";
        }
    }

    @Override
    public String toString() {
        return "MatchEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", timestampSeconds='" + timestampSeconds + '\'' +
                ", gold='" + gold + '\'' +
                ", heroId='" + heroId + '\'' +
                ", matchId='" + matchId + '\'' +
                ", playerId='" + playerId + '\'' +
                ", targetPlayerId='" + targetPlayerId + '\'' +
                '}';
    }
}