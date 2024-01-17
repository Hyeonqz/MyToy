package spring.project.web.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import spring.project.domain.question.QuestionRepository;
import spring.project.web.dto.QuestionDto;

@RequiredArgsConstructor
@Service
public class QuestionService {
	private final QuestionRepository questionRepository;

	@Transactional
	public Long save(QuestionDto questionDto) {
		return questionRepository.save(questionDto.toEntity()).getId();
	}
}
