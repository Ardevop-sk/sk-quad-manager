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
import sk.ardevop.nlp.skquadmanager.controller.mapper.RestDTOMapper;
import sk.ardevop.nlp.skquadmanager.entity.Dataset;
import sk.ardevop.nlp.skquadmanager.entity.Paragraph;
import sk.ardevop.nlp.skquadmanager.model.ParagraphBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.ParagraphRestDTO;
import sk.ardevop.nlp.skquadmanager.repository.ParagraphRepository;
import sk.ardevop.nlp.skquadmanager.repository.QuestionRepository;

@RestController
@CrossOrigin("*")
public class ParagraphApiController implements ParagraphApi {

  private final RestDTOMapper restDTOMapper;
  private final ParagraphRepository paragraphRepository;

  public ParagraphApiController(RestDTOMapper restDTOMapper,
      ParagraphRepository paragraphRepository) {
    this.restDTOMapper = restDTOMapper;
    this.paragraphRepository = paragraphRepository;
  }

  @Override
  public ResponseEntity<List<ParagraphRestDTO>> listParagraphs(String datasetId) {
    return ResponseEntity.ok(
        StreamEx.of(paragraphRepository.findAllByDataset_Id(datasetId).iterator())
            .map(restDTOMapper::paragraphRestDTO)
            .collect(toList()));
  }

  @Override
  public ResponseEntity<Void> deleteParagraph(String paragraphId) {
    paragraphRepository.deleteById(paragraphId);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<ParagraphRestDTO> getParagraph(String paragraphId) {
    Optional<Paragraph> paragraph = paragraphRepository.findById(paragraphId);
    return ResponseEntity.of(
        Optional.ofNullable(
            restDTOMapper.paragraphRestDTO(paragraph.orElse(null))));
  }

  @Override
  public ResponseEntity<ParagraphRestDTO> createParagraph(String datasetId,
      @Valid ParagraphBaseRestDTO paragraphBaseRestDTO) {
    Paragraph paragraph = restDTOMapper.paragraph(paragraphBaseRestDTO);
    paragraph.setDataset(Dataset.builder().id(datasetId).build());
    return ResponseEntity.ok(restDTOMapper.paragraphRestDTO(paragraphRepository.save(paragraph)));
  }

}
