package spring.project.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.project.dto.Answer;
import spring.project.dto.Question;
import spring.project.dto.SiteUser;
import spring.project.repository.AnswerRepository;
import spring.project.toy.DataNotFoundException;

@Service
@RequiredArgsConstructor
public class AnswerService {
	private final AnswerRepository answerRepository;

	//답변 생성
	public Answer create(Question question, String content, SiteUser author) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setWriteday(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setAuthor(author);
		this.answerRepository.save(answer);
		return answer;
	}

	//id에 따른 답변 가져오는 메소드
	public Answer getAnswer(Integer id) {
		Optional<Answer> answer = this.answerRepository.findById(id);
		if (answer.isPresent()) {
			return answer.get();
		} else {
			throw new DataNotFoundException("answer is not found");
		}
	}

	//id값에 따라 답변 수정하는 메소드
	public void modify(Answer answer, String content) {
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());
		this.answerRepository.save(answer);
	}

	//답변 삭제
	public void delete(Answer answer) {
		this.answerRepository.delete(answer);
	}

	//추천
	public void vote(Answer answer, SiteUser siteUser) {
		answer.getVoter().add(siteUser);
		this.answerRepository.save(answer);
	}


}
