package com.niit.esports.controller;

import com.niit.esports.entity.Team;
import com.niit.esports.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    // 显示所有队伍列表
    @GetMapping
    public String listTeams(Model model) {
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("team", teams);
        model.addAttribute("pageTitle", "队伍列表");
        return "team/team_list";
    }

    // 显示添加队伍表单
    @GetMapping("/add")
    public String addTeamForm(Model model) {
        model.addAttribute("team", new Team());
        model.addAttribute("pageTitle", "添加队伍");
        return "team/team_form";
    }

    // 保存队伍（新增或更新）
    @PostMapping("/save")
    public String saveTeam(@ModelAttribute Team team) {
        if (team.getTeamId() == 0) {
            // 新增队伍
            team.setCreatedAt(new Date());
            team.setLogoUrl("default_logo.png");
//            if (team.getTeamName() != null && !team.getTeamName().isEmpty()) {
//                String logoUrl = team.getTeamName().toLowerCase().replaceAll("\\s+", "") + "_logo.png";
//                team.setLogoUrl(logoUrl);
//            }
            teamService.addTeam(team);
        } else {
            // 更新队伍 - 需要保持原有的创建时间
            Team existingTeam = teamService.getTeamById(team.getTeamId());
            if (existingTeam != null) {
                team.setCreatedAt(existingTeam.getCreatedAt());
            }
            teamService.updateTeam(team);
        }
        return "redirect:/team";
    }

    // 删除队伍
    @GetMapping("/delete/{teamId}")
    public String deleteTeam(@PathVariable int teamId) {
        teamService.deleteTeam(teamId);
        return "redirect:/team";
    }

    // 编辑队伍
    @GetMapping("/edit/{teamId}")
    public String editTeamForm(@PathVariable int teamId, Model model) {
        Team team = teamService.getTeamById(teamId);
        model.addAttribute("team", team);
        model.addAttribute("pageTitle", "编辑队伍");
        return "team/team_form";
    }

    // 搜索队伍
    @GetMapping("/search")
    public String searchTeams(@RequestParam("keyword") String keyword, Model model) {
        List<Team> teams;
        if (keyword != null && !keyword.isEmpty()) {
            teams = teamService.getTeamsByName(keyword);
        } else {
            teams = teamService.getAllTeams();
        }
        model.addAttribute("team", teams);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageTitle", "队伍列表");
        return "team/team_list";
    }
}
