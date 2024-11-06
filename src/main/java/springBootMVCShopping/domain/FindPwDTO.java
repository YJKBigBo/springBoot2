package springBootMVCShopping.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("FindPwDTO")
public class FindPwDTO {
    String userId;
    String userPhone;
    String userEmail;
    String userPw;

    String tableName;
    String pwColumName;
    String userIdColumName;
    String phoneColumn;
}
