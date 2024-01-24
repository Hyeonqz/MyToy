package spring.project.question.application;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring.project.question.domain.Question;
import spring.project.question.repository.QuestionRepository;
import spring.project.question.application.dto.request.QuestionRequestDto;

@RequiredArgsConstructor
@Service
@Slf4j
public class QuestionService {
	private final QuestionRepository questionRepository;
	private final ModelMapper modelMapper;

	// 다수의 Question 엔티티를 QuestionDto로 변환하는 메서드
	private QuestionRequestDto convertToDto(Question question) {
		return modelMapper.map(question, QuestionRequestDto.class);
	}

	// 다수의 Question 엔티티를 QuestionDto 리스트로 변환하는 메서드
	public List<QuestionRequestDto> getAllQuestionDtos() {
		List<Question> questions = questionRepository.findAll();

		return questions.stream()
			.map(this::convertToDto)
			//.map(question -> convertToDto(question))
			.collect(Collectors.toList());
	}

	//나만의 방법
	public List<QuestionRequestDto> getListDto() {
		List<Question> questionList = questionRepository.findAll();

		List<QuestionRequestDto> questionRequestDtos = questionList.stream()
			.map(question -> QuestionRequestDto.builder()
				.subject(question.getSubject())
				.content(question.getContent())
				.build())
			.collect(Collectors.toList());
		return questionRequestDtos;
	}



}
