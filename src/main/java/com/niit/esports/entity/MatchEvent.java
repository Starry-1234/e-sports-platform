package com.niit.esports.entity;

public class MatchEvent {

    // === 数据库中的实际事件类型 ===
    public static final String EVENT_FIRST_BLOOD = "FIRST_BLOOD";
    public static final String EVENT_DRAGON_OCEAN = "DRAGON_OCEAN";
    public static final String EVENT_DRAGON_INFERNAL = "DRAGON_INFERNAL";
    public static final String EVENT_DRAGON_MOUNTAIN = "DRAGON_MOUNTAIN";
    public static final String EVENT_DRAGON_CLOUD = "DRAGON_CLOUD";
    public static final String EVENT_HERALD = "HERALD";
    public static final String EVENT_FIRST_TOWER = "FIRST_TOWER";
    public static final String EVENT_BARON = "BARON";
    public static final String EVENT_ELDER_DRAGON = "ELDER_DRAGON";
    public static final String EVENT_ACE = "ACE";

    // 获取所有事件类型的方法
    public static String[] getEventTypes() {
        return new String[]{
                EVENT_FIRST_BLOOD,
                EVENT_DRAGON_OCEAN,
                EVENT_DRAGON_INFERNAL,
                EVENT_DRAGON_MOUNTAIN,
                EVENT_DRAGON_CLOUD,
                EVENT_HERALD,
                EVENT_FIRST_TOWER,
                EVENT_BARON,
                EVENT_ELDER_DRAGON,
                EVENT_ACE
        };
    }

    // 获取事件类型显示名称的方法
    public static String getEventTypeDisplayName(String eventType) {
        switch (eventType) {
            case EVENT_FIRST_BLOOD:
                return "一血";
            case EVENT_DRAGON_OCEAN:
                return "海洋亚龙";
            case EVENT_DRAGON_INFERNAL:
                return "炼狱亚龙";
            case EVENT_DRAGON_MOUNTAIN:
                return "山脉亚龙";
            case EVENT_DRAGON_CLOUD:
                return "云端亚龙";
            case EVENT_HERALD:
                return "峡谷先锋";
            case EVENT_FIRST_TOWER:
                return "一塔";
            case EVENT_BARON:
                return "纳什男爵";
            case EVENT_ELDER_DRAGON:
                return "远古巨龙";
            case EVENT_ACE:
                return "团灭";
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

    // 关联字段（用于显示）
    private String heroName;
    private String playerName;
    private String targetPlayerName;

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

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTargetPlayerName() {
        return targetPlayerName;
    }

    public void setTargetPlayerName(String targetPlayerName) {
        this.targetPlayerName = targetPlayerName;
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
        String displayName = getEventTypeDisplayName(eventType);

        if (playerName != null) {
            switch (eventType) {
                case EVENT_FIRST_BLOOD:
                    return playerName + " 拿到一血" + (targetPlayerName != null ? "（击杀 " + targetPlayerName + "）" : "");
                case EVENT_ACE:
                    return playerName + " 完成团灭";
                default:
                    return playerName + " " + displayName;
            }
        }

        return displayName;
    }

    // 工具方法：获取事件图标类
    public String getEventIconClass() {
        switch (eventType) {
            case EVENT_FIRST_BLOOD:
                return "fas fa-tint text-danger";
            case EVENT_DRAGON_OCEAN:
                return "fas fa-water text-primary";
            case EVENT_DRAGON_INFERNAL:
                return "fas fa-fire text-danger";
            case EVENT_DRAGON_MOUNTAIN:
                return "fas fa-mountain text-warning";
            case EVENT_DRAGON_CLOUD:
                return "fas fa-cloud text-info";
            case EVENT_HERALD:
                return "fas fa-eye text-success";
            case EVENT_FIRST_TOWER:
                return "fas fa-fort-awesome text-warning";
            case EVENT_BARON:
                return "fas fa-crown text-purple";
            case EVENT_ELDER_DRAGON:
                return "fas fa-dragon text-danger";
            case EVENT_ACE:
                return "fas fa-skull-crossbones text-dark";
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