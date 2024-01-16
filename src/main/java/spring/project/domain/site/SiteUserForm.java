package spring.project.domain.site;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteUserForm {

	@Size(min=3, max=25) //최소, 최대 글자 수 제한
	@NotEmpty(message = "사용자 이름은 필수 입니다.")
	private String username;

	@NotEmpty(message="비밀번호 입력은 필수 입니다.")
	private String password1;

	@NotEmpty(message = "비밀번호 확인은 필수항목 입니다.")
	private String password2;

	@NotEmpty(message="이메일은 필수 입니다.")
	@Email //이메일 확인 어노테이션
	private String email;

	@NotEmpty(message="주소는 필수 입니다.")
	private String addr;

	private LocalDateTime gaipday;

}
