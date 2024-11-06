package springBootMVCShopping.service.help;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import springBootMVCShopping.command.FindPwCommand;
import springBootMVCShopping.domain.FindPwDTO;
import springBootMVCShopping.mapper.HelpMapper;
import springBootMVCShopping.service.EmailSendService;
import springBootMVCShopping.service.SMSMassageService;

import java.util.UUID;

@Service
public class FindPasswordService {
    @Autowired
    HelpMapper helpMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailSendService emailSendService;

    public void execute(FindPwCommand command, Model model) {
        FindPwDTO dto = new FindPwDTO();
        dto.setUserId(command.getUserId());
        dto.setUserPhone(command.getUserPhone());
        String email = helpMapper.findPw(dto);
        if(!email.isBlank()){
            String part = helpMapper.parseOb(dto);
            if(part.equals("mem")){
                dto.setTableName("members");
                dto.setPwColumName("member_pw");
                dto.setUserIdColumName("member_id");
                dto.setPhoneColumn("member_phone1");
            } else{
                dto.setTableName("employees");
                dto.setPwColumName("emp_pw");
                dto.setUserIdColumName("emp_id");
                dto.setPhoneColumn("emp_phone");
            }
            String newPw = UUID.randomUUID().toString().substring(0,8);
            //emailSendService.mailSend(email,email,"새 비밀번호",newPw);
            System.out.println(newPw);
            String encodePw = passwordEncoder.encode(newPw);
            dto.setUserPw(encodePw);
            helpMapper.pwUdate(dto);
//            helpMapper.PwUpdate(encodePw, email);
            model.addAttribute("auth",email);
        } else{
            model.addAttribute("auth", " ");
        }
    }
}
