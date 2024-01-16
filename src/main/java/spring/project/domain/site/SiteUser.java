package spring.project.domain.site;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String password;

	@Column(unique = true) //유일한 값만 저장할 수 있음을 의미.
	private String username;
	//unique 설정한 속성들로 인해 UK_ 로 시작하는 인덱스들이 생성됨.

	@Column(unique = true)
	private String email;

	@Column
	private String addr;

	@Column
	private LocalDateTime gaipday;

}
