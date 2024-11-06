package springBootMVCShopping.command;

import lombok.Data;

@Data
public class FindPwCommand {
    String userId;
    String userPhone;
    String userEmail;
}
