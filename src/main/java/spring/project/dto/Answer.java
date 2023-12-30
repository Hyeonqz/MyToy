package spring.project.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDateTime writeday;

	@ManyToOne
	private Question question;

	@ManyToOne   //1대 다 관계 일 때 사용
	private SiteUser author;
	private LocalDateTime modifyDate;

	@ManyToMany
	Set<SiteUser> voter;
}