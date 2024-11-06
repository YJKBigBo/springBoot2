package springBootMVCShopping.service.help;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import springBootMVCShopping.command.FindPwCommand;
import springBootMVCShopping.mapper.HelpMapper;

@Service
public class FindIdService {
    @Autowired
    HelpMapper helpMapper;

    public void execute(FindPwCommand command, Model model) {
        String userPhone = command.getUserPhone();
        String userEmail = command.getUserEmail();
        String id = helpMapper.findId(userPhone, userEmail);
        if(id != null) {
            model.addAttribute("userId", id);
        } else {
            model.addAttribute("userId", null);
        }
    }
}
