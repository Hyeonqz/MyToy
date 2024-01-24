package spring.project.question.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.project.question.domain.Question;

@Getter
@NoArgsConstructor
public class QuestionRequestDto {

	//Entity와 거의 유사한 형태인 Dto를 생성했다
	//절대로 Entity클래스를 Request, Response 클래스로 사용해서는 안된다.
	//view를 위한 클래스로 자주 변경이 되는 작업을 처리할 것이다.
	//Entity 클래스와 Controller에서 사용할 dto는 분리해서 사용해야함.

	private String subject;
	private String content;

	@Builder
	public QuestionRequestDto(String subject, String content) {
		this.subject = subject;
		this.content = content;
	}

	public Question toEntity() {
		return Question.builder()
			.subject(subject)
			.content(content)
			.build();
	}
}
