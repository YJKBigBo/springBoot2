package springBootMVCShopping.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.LoginCommand;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.mapper.LoginMapper;

@Service
public class UserLoginService {
	@Autowired
	LoginMapper loginMapper;
	@Autowired
	PasswordEncoder passwordEncoder;

	public void execute(LoginCommand loginCommand, HttpSession session, BindingResult result, HttpServletResponse response) {
		AuthInfoDTO auth = loginMapper.loginSelectOne(loginCommand.getUserId());

		if (auth != null) {
			if (passwordEncoder.matches(loginCommand.getUserPw(), auth.getUserPw())) {
				session.setAttribute("auth", auth);

				if (loginCommand.isIdStore()) {
					Cookie idStoreCookie = new Cookie("idStore", loginCommand.getUserId());
					idStoreCookie.setPath("/");
					idStoreCookie.setMaxAge(60 * 60 * 24 * 30);
					response.addCookie(idStoreCookie);
				} else {
					Cookie idStoreCookie = new Cookie("idStore", "");
					idStoreCookie.setPath("/");
					idStoreCookie.setMaxAge(0);
					response.addCookie(idStoreCookie);
				}

				if (loginCommand.isAutoLogin()) {
					Cookie autoLoginCookie = new Cookie("autoLogin", loginCommand.getUserId());
					autoLoginCookie.setPath("/");
					autoLoginCookie.setMaxAge(60 * 60 * 24 * 30);
					response.addCookie(autoLoginCookie);
				} else {
					Cookie autoLoginCookie = new Cookie("autoLogin", "");
					autoLoginCookie.setPath("/");
					autoLoginCookie.setMaxAge(0);
					response.addCookie(autoLoginCookie);
				}
			} else {
				result.rejectValue("userPw", "loginCommand.userPw", "비밀번호가 틀렸습니다.");
			}
		} else {
			result.rejectValue("userId", "loginCommand.userId", "아이디가 존재하지 않습니다.");
		}
	}
}