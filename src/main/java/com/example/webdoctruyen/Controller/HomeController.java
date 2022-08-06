package com.example.webdoctruyen.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping({"/","index"})
    public String index(Model model, @RequestParam("login") boolean login){
        Boolean isLogin = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        model.addAttribute("isLogin",isLogin);
        model.addAttribute("login",login);
        return "index";
    }
}
