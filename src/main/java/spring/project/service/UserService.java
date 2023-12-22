package spring.project.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.project.dto.SiteUser;
import spring.project.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public SiteUser create(String username, String password, String email, String addr, LocalDateTime gaipday) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setAddr(addr);
		user.setGaipday(LocalDateTime.now());

		//비밀번호 암호화
		//스프링 시큐리티의 BCryptPasswordEncoder클래스 사용.
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		return user;
	}

}
