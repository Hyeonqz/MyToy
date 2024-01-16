package spring.project.web.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import spring.project.domain.answer.Answer;
import spring.project.domain.question.Question;
import spring.project.domain.site.SiteUser;
import spring.project.domain.question.QuestionRepository;
import spring.project.toy.DataNotFoundException;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;

	//전체 조회 메소드
	public List<Question> getList() {

		return this.questionRepository.findAll();
	}

	//num에 해당하는 question 데이터 조회
	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);
		if(question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("question not found");
		}
	}

	//insert 메소드
	public void create(String subject, String content, SiteUser author) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setWirteday(LocalDateTime.now());
		q.setAuthor(author);
		this.questionRepository.save(q);
	}

	//페이징 메소드
	public Page<Question> getList(int page, String keywords) {
		List<Sort.Order> sorts = new ArrayList<Sort.Order>();
		sorts.add(Sort.Order.desc("wirteday"));
		Pageable pageable = PageRequest.of(page,10, Sort.by(sorts));
		Specification<Question> specification = search(keywords);
		return this.questionRepository.findAll(specification,pageable);
		//return this.questionRepository.findAllByKeyword(keywords,pageable); (쿼리 사용시)
	}

	//수정 메소드
	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}

	//삭제 메소드
	public void delete(Question question) {
		this.questionRepository.delete(question);
	}

	//추천 메소드
	public void vote(Question question, SiteUser siteUser) {
		question.getVoter().add(siteUser);
		this.questionRepository.save(question);
	}

	//검색 메소드
	private Specification<Question> search(String keywords) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);  // 중복을 제거
				Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
				Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
				Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
				return cb.or(cb.like(q.get("subject"), "%" + keywords + "%"), // 제목
					cb.like(q.get("content"), "%" + keywords + "%"),      // 내용
					cb.like(u1.get("username"), "%" + keywords + "%"),    // 질문 작성자
					cb.like(a.get("content"), "%" + keywords + "%"),      // 답변 내용
					cb.like(u2.get("username"), "%" + keywords + "%"));   // 답변 작성자
			}
		};
	}

}
