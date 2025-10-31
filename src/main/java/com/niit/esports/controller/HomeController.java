package com.niit.esports.controller;

import com.niit.esports.service.MatchEventService;
import com.niit.esports.service.MatchService;
import com.niit.esports.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private StatsService statsService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "电竞数据中台 - 首页");
        model.addAttribute("teamCount", statsService.getTeamCount());
        model.addAttribute("playerCount", statsService.getPlayerCount());
        model.addAttribute("matchCount", statsService.getMatchCount());
        model.addAttribute("heroCount", statsService.getHeroCount());
        return "index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        return home(model);
    }
}