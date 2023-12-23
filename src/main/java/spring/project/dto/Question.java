package spring.project.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 200)
	private String subject;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDateTime wirteday;

	//질문 하나에는 여러개의 답변이 작성 될 수 있다.
	//이때 질문 삭제시 그에 달린 모든 답변또한 삭제 -> sql cascade랑 같은 역할.
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;

	@ManyToOne
	private SiteUser author;
	//여러개의 질문이 한명의 사용자에게 요청될 수 있음
	private LocalDateTime modifyDate;
}