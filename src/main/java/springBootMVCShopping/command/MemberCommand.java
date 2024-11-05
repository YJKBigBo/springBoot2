package springBootMVCShopping.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberCommand {
	/// 자료형이 String @NotEmpty, @NotBlank를 사용할 수 있다.
	/// String이 아닌 자료형은 @NotNull이다 
	String memberNum;

	String memberId;

	String memberPw;

	String memberPwCon;

	String memberName;

	String memberAddr;
	String memberAddrDetail;
	String memberPost;


	String memberPhone1;
	
	String memberPhone2;
	String gender;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memberBirth;
	String memberEmail;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memberRegist;
	
	public boolean isMemberPwEqualMemberPwCon() {
		return memberPw.equals(memberPwCon);
	}
}




