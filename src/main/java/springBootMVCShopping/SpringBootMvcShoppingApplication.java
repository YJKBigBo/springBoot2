package springBootMVCShopping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springBootMVCShopping.command.LoginCommand;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.mapper.LoginMapper;
import springBootMVCShopping.service.EmailSendService;
import springBootMVCShopping.service.SMSMassageService;
import springBootMVCShopping.service.goods.MainGoodsListService;
import springBootMVCShopping.service.login.UserLoginService;

import java.net.http.HttpRequest;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@Controller
public class SpringBootMvcShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMvcShoppingApplication.class, args);
	}

	@Autowired
	MainGoodsListService mainGoodsListService;
	@Autowired
	LoginMapper loginMapper;

	@GetMapping("/")
	public String index(LoginCommand loginCommand, HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("idStore")) {
					req.setAttribute("sid", cookie.getValue());
				}
				if (cookie.getName().equals("autoLogin")) {
					AuthInfoDTO auth = loginMapper.loginSelectOne(cookie.getValue());
					HttpSession session = req.getSession();
					session.setAttribute("auth", auth);
				}
			}
		}
		return "thymeleaf/index";
	}
	@PostMapping("/")
	public ModelAndView index(int page, Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jsonView");
		mainGoodsListService.execute(page, model);
		return mav;
	}
	
	@GetMapping("/mailling")
	public String mail() {
		return "thymeleaf/email";
	}
	@Autowired
	EmailSendService emailSendService;
	@PostMapping("/mailling")
	public String mail(String fromEmail
			,String toEmail, String subject
			,String contents) {
		emailSendService.mailSend(fromEmail, toEmail, subject, contents);
		return "redirect:/";
	}
	@GetMapping("smsSend")
	public String smsSend() {
		return "thymeleaf/sms";
	}
	@Autowired
	SMSMassageService sMSMassageService;
	@PostMapping("smsSend")
	public String smsSend(String userPhone
			,String message) {
		sMSMassageService.smsSend(userPhone, "010-7146-1970", message);
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
}
