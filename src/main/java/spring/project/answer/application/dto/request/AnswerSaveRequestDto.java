package spring.project.answer.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.project.answer.domain.Answer;

@Getter
@NoArgsConstructor
@Builder
public class AnswerSaveRequestDto {
	private String content;


	public AnswerSaveRequestDto(String content) {
		this.content = content;
	}

	//AnswerDto를 사용하여 Answer 엔티티 생성하는 메서드
	public Answer toEntity() {
		return Answer.builder()
			.content(content)
			.build();
	}

}
