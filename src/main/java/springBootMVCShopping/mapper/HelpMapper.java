package springBootMVCShopping.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import springBootMVCShopping.domain.FindPwDTO;

@Mapper
public interface HelpMapper {
    public String findPw(FindPwDTO dto);
    public void PwUpdate(@Param("encodePw") String encodePw, @Param("email") String email);

    public String findId(@Param("userPhone") String userPhone, @Param("userEmail") String userEmail);
    public String parseOb(FindPwDTO dto);

    public void pwUdate(FindPwDTO dto);
}
