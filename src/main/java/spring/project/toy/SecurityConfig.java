package spring.project.toy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	/*
	* SecurityConfig에 적용한@EnableMethodSecurity 애너테이션의 prePostEnabled = true 설정은
	* QuestionController와 AnswerController에서 로그인 여부를 판별하기 위해
	* 사용했던 @PreAuthorize 애너테이션을 사용하기 위해 반드시 필요하다.
	*/
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()) //여기까지 기본 로그인창 없애는 로직.
			.csrf((csrf) -> csrf
				.ignoringRequestMatchers(new AntPathRequestMatcher("/mysql-console/**"))) //DB시큐리티 설정등록하기.
			.headers((headers) -> headers
				.addHeaderWriter(new XFrameOptionsHeaderWriter(
					XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
			.formLogin((formLogin) -> formLogin
				.loginPage("/user/login")
				.defaultSuccessUrl("/question/list"))
			.logout((logout) -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.logoutSuccessUrl("/question/list")
				.invalidateHttpSession(true))
		;
		return http.build();
	}

	//비밀번호 암호화 bean 등록
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
