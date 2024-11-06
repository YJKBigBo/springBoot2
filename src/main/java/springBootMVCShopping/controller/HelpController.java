package springBootMVCShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springBootMVCShopping.command.FindPwCommand;
import springBootMVCShopping.service.help.FindIdService;
import springBootMVCShopping.service.help.FindPasswordService;

@Controller
@RequestMapping("help")
public class HelpController {

    @Autowired
    FindPasswordService findPasswordService;
    @Autowired
    FindIdService findIdService;


    @GetMapping("findPassword")
    public String findPassword() {
        return "thymeleaf/help/findPw";
    }

    @PostMapping("findPassword")
    public String findPassword(FindPwCommand command, Model model) {
        findPasswordService.execute(command, model);
        return "thymeleaf/help/findPwOk";
    }

    @GetMapping("findId")
    public String findId() {
        return "thymeleaf/help/findId";
    }

    @PostMapping("findId")
    public String findIdP(FindPwCommand command, Model model){
        findIdService.execute(command, model);
        return "thymeleaf/help/findIdOk";
    }
}
