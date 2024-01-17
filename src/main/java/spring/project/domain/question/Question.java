package spring.project.domain.question;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.project.domain.BaseTimeEntity;
import spring.project.domain.answer.Answer;

@Getter
@NoArgsConstructor
@Entity
public class Question extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment 역할
	private Long id;

	@Column(length=200, nullable=false)
	private String subject;

	@Column(columnDefinition = "TEXT", nullable=false)
	private String content;

	//질문에서 답변을 참조하게 하기 위함. -> question.getAnswerList() 가능
	@OneToMany(mappedBy="question", cascade = CascadeType.REMOVE) //DB랑 같은 맥락 cascade.remove시 원글 삭제시 다 삭제되는 로직
	private List<Answer> answerList;


	@Builder
	public Question(String subject, String content) {
		this.subject = subject;
		this.content = content;
	}
}
