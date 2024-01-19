package spring.project.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring.project.domain.question.Question;
import spring.project.domain.question.QuestionRepository;
import spring.project.web.dto.AnswerDto;
import spring.project.web.dto.QuestionDto;

@RequiredArgsConstructor
@Service
@Slf4j
public class QuestionService {
	private final QuestionRepository questionRepository;
	private final ModelMapper modelMapper;

	// 다수의 Question 엔티티를 QuestionDto로 변환하는 메서드
	private QuestionDto convertToDto(Question question) {
		return modelMapper.map(question, QuestionDto.class);
	}

	// 다수의 Question 엔티티를 QuestionDto 리스트로 변환하는 메서드
	public List<QuestionDto> getAllQuestionDtos() {
		List<Question> questions = questionRepository.findAll();
		return questions.stream()
			.map(question -> convertToDto(question))
			.collect(Collectors.toList());
	}


}
