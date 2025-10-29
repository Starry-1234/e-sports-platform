package com.niit.esports.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "电竞数据中台 - 首页");
        return "index";  // 这会指向 /WEB-INF/views/index.html
    }

    @GetMapping("/index")
    public String index(Model model) {
        return home(model);
    }
}