package sk.ardevop.nlp.skquadmanager.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import one.util.streamex.StreamEx;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import sk.ardevop.nlp.skquadmanager.api.ParagraphApi;
import sk.ardevop.nlp.skquadmanager.api.QuestionApi;
import sk.ardevop.nlp.skquadmanager.controller.mapper.RestDTOMapper;
import sk.ardevop.nlp.skquadmanager.entity.Answer;
import sk.ardevop.nlp.skquadmanager.entity.Dataset;
import sk.ardevop.nlp.skquadmanager.entity.Paragraph;
import sk.ardevop.nlp.skquadmanager.entity.Question;
import sk.ardevop.nlp.skquadmanager.model.AnswerBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.AnswerRestDTO;
import sk.ardevop.nlp.skquadmanager.model.ParagraphBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.ParagraphRestDTO;
import sk.ardevop.nlp.skquadmanager.model.QuestionBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.QuestionRestDTO;
import sk.ardevop.nlp.skquadmanager.repository.AnswerRepository;
import sk.ardevop.nlp.skquadmanager.repository.ParagraphRepository;
import sk.ardevop.nlp.skquadmanager.repository.QuestionRepository;

@RestController
@CrossOrigin("*")
public class QuestionApiController implements QuestionApi {

  private final RestDTOMapper restDTOMapper;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  public QuestionApiController(RestDTOMapper restDTOMapper,
      QuestionRepository questionRepository, AnswerRepository answerRepository) {
    this.restDTOMapper = restDTOMapper;
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
  }

  @Override
  public ResponseEntity<Void> deleteAnswer(String answerId) {
    answerRepository.deleteById(answerId);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<AnswerRestDTO> createAnswer(String questionId, @Valid AnswerBaseRestDTO answerBaseRestDTO) {
    Answer answer = restDTOMapper.answer(answerBaseRestDTO);
    answer.setQuestion(Question.builder().id(questionId).build());
    return ResponseEntity.ok(restDTOMapper.answerRestDTO(answerRepository.save(answer)));
  }

  @Override
  public ResponseEntity<QuestionRestDTO> createQuestion(String paragraphId,
      @Valid QuestionBaseRestDTO questionBaseRestDTO) {
    Question question = restDTOMapper.question(questionBaseRestDTO);
    question.setParagraph(Paragraph.builder().id(paragraphId).build());
    Question question2 = questionRepository.save(question);
    return ResponseEntity.ok(restDTOMapper.questionRestDTO(question2));
  }

  @Override
  public ResponseEntity<Void> deleteQuestion(String questionId) {
    questionRepository.deleteById(questionId);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<QuestionRestDTO> getQuestion(String questionId) {
    Optional<Question> question = questionRepository.findById(questionId);
    return ResponseEntity.of(
        Optional.ofNullable(
            restDTOMapper.questionRestDTO(question.orElse(null))));
  }

}
