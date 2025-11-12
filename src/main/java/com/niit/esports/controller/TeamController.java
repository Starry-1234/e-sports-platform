package com.niit.esports.controller;

import com.niit.esports.entity.Team;
import com.niit.esports.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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
    public String saveTeam(@ModelAttribute Team team,
                           @RequestParam(value = "logoFile", required = false) MultipartFile logoFile,
                           @RequestParam(value = "selectedLogo", required = false) String selectedLogo) {
        // 处理文件上传
        if (logoFile != null && !logoFile.isEmpty()) {
            try {
                // 原有的文件上传逻辑
                String uploadDir = "src/main/webapp/static/images/team_logos/";
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String originalFilename = logoFile.getOriginalFilename();
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

                byte[] bytes = logoFile.getBytes();
                Path path = Paths.get(uploadDir + uniqueFilename);
                Files.write(path, bytes);

                team.setLogoUrl(uniqueFilename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 处理预设图片选择
        else if (selectedLogo != null && !selectedLogo.isEmpty()) {
            team.setLogoUrl(selectedLogo);
        }

        if (team.getTeamId() == 0) {
            team.setCreatedAt(new Date());
            if (team.getLogoUrl() == null || team.getLogoUrl().isEmpty()) {
                team.setLogoUrl("default_logo.png");
            }
            teamService.addTeam(team);
        } else {
            Team existingTeam = teamService.getTeamById(team.getTeamId());
            if (existingTeam != null) {
                team.setCreatedAt(existingTeam.getCreatedAt());
                if (team.getLogoUrl() == null || team.getLogoUrl().isEmpty()) {
                    team.setLogoUrl(existingTeam.getLogoUrl());
                }
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
