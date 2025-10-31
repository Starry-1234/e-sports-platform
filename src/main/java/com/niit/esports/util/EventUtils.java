package com.niit.esports.util;

import org.springframework.stereotype.Component;

@Component
public class EventUtils {

    public static String getEventIconClass(String eventType) {
        switch (eventType) {
            case "FIRST_BLOOD":
                return "fa-tint text-danger";
            case "DRAGON_OCEAN":
                return "fa-water text-primary";
            case "DRAGON_INFERNAL":
                return "fa-fire text-danger";
            case "DRAGON_MOUNTAIN":
                return "fa-mountain text-warning";
            case "DRAGON_CLOUD":
                return "fa-cloud text-info";
            case "HERALD":
                return "fa-eye text-success";
            case "FIRST_TOWER":
                return "fa-fort-awesome text-warning";
            case "BARON":
                return "fa-crown text-purple";
            case "ELDER_DRAGON":
                return "fa-dragon text-danger";
            case "ACE":
                return "fa-skull-crossbones text-dark";
            default:
                return "fa-circle text-secondary";
        }
    }

    public static String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public static String formatTime(String timestampSeconds) {
        try {
            int totalSeconds = Integer.parseInt(timestampSeconds);
            return formatTime(totalSeconds);
        } catch (NumberFormatException e) {
            return timestampSeconds;
        }
    }
}