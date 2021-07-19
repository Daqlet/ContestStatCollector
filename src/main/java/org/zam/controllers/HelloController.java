package org.zam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zam.dao.RequestDAO;
import org.zam.models.Request;

import java.io.IOException;

@Controller
@RequestMapping("/cf")
public class HelloController {
    private final RequestDAO requestDAO;
    @Autowired
    public HelloController(RequestDAO requestDAO) {
        this.requestDAO = requestDAO;
    }
    @GetMapping()
    public String index(@ModelAttribute("request") Request request) {
        return "cf/hello";
    }
    @PostMapping()
    public String find(@ModelAttribute("request") Request request, Model model) throws IOException, InterruptedException {
        model.addAttribute("contests", requestDAO.index());
        model.addAttribute("ordered", requestDAO.order());
        requestDAO.find(request);
        return "cf/show";
    }
}
