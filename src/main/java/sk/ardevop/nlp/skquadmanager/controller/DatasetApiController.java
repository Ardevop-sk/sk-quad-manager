package sk.ardevop.nlp.skquadmanager.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;
import javax.validation.Valid;
import one.util.streamex.StreamEx;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import sk.ardevop.nlp.skquadmanager.api.DatasetApi;
import sk.ardevop.nlp.skquadmanager.controller.mapper.RestDTOMapper;
import sk.ardevop.nlp.skquadmanager.entity.Answer;
import sk.ardevop.nlp.skquadmanager.entity.Corpus;
import sk.ardevop.nlp.skquadmanager.entity.Dataset;
import sk.ardevop.nlp.skquadmanager.entity.Paragraph;
import sk.ardevop.nlp.skquadmanager.entity.Question;
import sk.ardevop.nlp.skquadmanager.model.AnswerRestDTO;
import sk.ardevop.nlp.skquadmanager.model.DatasetBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.DatasetRestDTO;
import sk.ardevop.nlp.skquadmanager.model.ParagraphBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.ParagraphRestDTO;
import sk.ardevop.nlp.skquadmanager.model.QuestionBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.QuestionRestDTO;
import sk.ardevop.nlp.skquadmanager.repository.AnswerRepository;
import sk.ardevop.nlp.skquadmanager.repository.DatasetRepository;
import sk.ardevop.nlp.skquadmanager.repository.ParagraphRepository;
import sk.ardevop.nlp.skquadmanager.repository.QuestionRepository;

@RestController
@CrossOrigin("*")
public class DatasetApiController implements DatasetApi {

  private final RestDTOMapper restDTOMapper;
  private final DatasetRepository datasetRepository;
  private final ParagraphRepository paragraphRepository;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  public DatasetApiController(RestDTOMapper restDTOMapper,
      DatasetRepository datasetRepository,
      ParagraphRepository paragraphRepository,
      QuestionRepository questionRepository, AnswerRepository answerRepository) {
    this.restDTOMapper = restDTOMapper;
    this.datasetRepository = datasetRepository;
    this.paragraphRepository = paragraphRepository;
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
  }

  @Override
  public ResponseEntity<AnswerRestDTO> createAnswer(String questionId, @Valid AnswerRestDTO answerRestDTO) {
    Answer answer = restDTOMapper.answer(answerRestDTO);
    answer.setQuestion(Question.builder().id(questionId).build());
    return ResponseEntity.ok(restDTOMapper.answerRestDTO(answerRepository.save(answer)));
  }

  @Override
  public ResponseEntity<DatasetRestDTO> createCorpusDataset(String corpusId,
      @Valid DatasetBaseRestDTO datasetBaseRestDTO) {
    Dataset dataset = restDTOMapper.dataset(datasetBaseRestDTO);
    dataset.setCorpus(Corpus.builder().id(corpusId).build());
    return ResponseEntity.ok(restDTOMapper.datasetRestDTO(datasetRepository.save(dataset)));
  }

  @Override
  public ResponseEntity<ParagraphRestDTO> createParagraph(String datasetId,
      @Valid ParagraphBaseRestDTO paragraphBaseRestDTO) {
    Paragraph paragraph = restDTOMapper.paragraph(paragraphBaseRestDTO);
    paragraph.setDataset(Dataset.builder().id(datasetId).build());
    return ResponseEntity.ok(restDTOMapper.paragraphRestDTO(paragraphRepository.save(paragraph)));
  }

  @Override
  public ResponseEntity<QuestionRestDTO> createQuestion(String paragraphId,
      @Valid QuestionBaseRestDTO questionBaseRestDTO) {
    Question question = restDTOMapper.question(questionBaseRestDTO);
    question.setParagraph(Paragraph.builder().id(paragraphId).build());
    return ResponseEntity.ok(restDTOMapper.questionRestDTO(questionRepository.save(question)));
  }

  @Override
  public ResponseEntity<List<DatasetRestDTO>> listDatasets(String corpusId) {
    return ResponseEntity.ok(
        StreamEx.of(datasetRepository.findAllByCorpus_Id(corpusId).iterator())
            .map(restDTOMapper::datasetRestDTO)
            .collect(toList())
    );
  }

  @Override
  public ResponseEntity<List<ParagraphRestDTO>> listParagraphs(String datasetId) {
    return ResponseEntity.ok(
        StreamEx.of(paragraphRepository.findAllByDataset_Id(datasetId).iterator())
            .map(restDTOMapper::paragraphRestDTO)
            .collect(toList()));
  }
}
