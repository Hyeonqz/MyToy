package spring.project.domain.answer;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.project.domain.BaseTimeEntity;
import spring.project.domain.question.Question;

@Getter
@NoArgsConstructor
@Entity
public class Answer extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String content;

	@ManyToOne
	private Question question; //한 글에 여러개의 댓글이므로 1:N 관계, DB에서 외래키라고 보면댐

	@Builder
	public Answer(String content) {
		this.content = content;
	}
}
