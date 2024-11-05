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
public class UserCommand {

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


	Date memberBirth;

	String memberEmail;
}
