package com.niit.esports.controller;

import com.niit.esports.entity.Player;
import com.niit.esports.entity.Team;
import com.niit.esports.service.PlayerService;
import com.niit.esports.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    // 显示所有选手
    @GetMapping
    public String listPlayers(Model model) {
        List<Player> players = playerService.getAllPlayers();
        model.addAttribute("player", players);
        model.addAttribute("pageTitle", "选手列表");
        return "player/player_list";
    }

    // 添加选手
    @GetMapping("/add")
    public String addPlayerForm(Model model) {
        model.addAttribute("player", new Player());
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        model.addAttribute("pageTitle", "添加选手");
        return "player/player_form";
    }

    // 新增或更新 选手
    @PostMapping("/save")
    public String savePlayer(@ModelAttribute Player player) {
        if (player.getPlayerId() == 0) {
            // 新增选手
            player.setCreatedAt(new Date());
            playerService.addPlayer(player);
        } else {
            // 更新选手
            Player existingPlayer = playerService.getPlayerById(player.getPlayerId());
            if (existingPlayer != null) {
                player.setCreatedAt(existingPlayer.getCreatedAt());
            }
            playerService.updatePlayer(player);
        }
        return "redirect:/player";
    }

    // 删除选手
    @GetMapping("/delete/{playerId}")
    public String deletePlayer(@PathVariable int playerId) {
        playerService.deletePlayer(playerId);
        return "redirect:/player";
    }

    // 编辑选手
    @GetMapping("/edit/{playerId}")
    public String editPlayerForm(@PathVariable int playerId, Model model) {
        Player player = playerService.getPlayerById(playerId);
        model.addAttribute("player", player);
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        model.addAttribute("pageTitle", "编辑选手");
        return "player/player_form";
    }

    // 搜索选手
    @GetMapping("/search")
    public String searchPlayers(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Player> players;
        if (keyword != null && !keyword.isEmpty()) {
            // 这里需要在 PlayerService 中添加按游戏名搜索的方法
            players = playerService.getPlayersByGameName(keyword);
        } else {
            players = playerService.getAllPlayers();
        }
        model.addAttribute("player", players);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageTitle", "选手列表");
        return "player/player_list";
    }
}
