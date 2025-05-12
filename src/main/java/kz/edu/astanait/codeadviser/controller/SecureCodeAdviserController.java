package kz.edu.astanait.qarzhytracker.controller;

import kz.edu.astanait.qarzhytracker.service.impl.SecureCodeAdviser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SecureCodeAdviserController {

    private final SecureCodeAdviser secureCodeAdviser;

    @GetMapping("/demo/code")
    public String showForm() {
        return "codeForm";
    }

    @PostMapping("/secure")
    public String secureCode(@RequestParam("code") String code, Model model) {
        String result = secureCodeAdviser.askForAdvice(code);
        model.addAttribute("originalCode", code);
        model.addAttribute("securedCode", result);
        return "codeForm";
    }
}
