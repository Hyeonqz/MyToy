package spring.project.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.project.dto.Answer;
import spring.project.dto.Question;
import spring.project.dto.SiteUser;
import spring.project.repository.AnswerRepository;

@Service
@RequiredArgsConstructor
public class AnswerService {
	private final AnswerRepository answerRepository;

	public Answer create(Question question, String content, SiteUser author) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setWriteday(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setAuthor(author);
		this.answerRepository.save(answer);
		return answer;
	}
}
