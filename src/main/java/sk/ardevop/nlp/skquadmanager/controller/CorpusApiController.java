package sk.ardevop.nlp.skquadmanager.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import one.util.streamex.StreamEx;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import sk.ardevop.nlp.skquadmanager.api.CorpusApi;
import sk.ardevop.nlp.skquadmanager.controller.mapper.RestDTOMapper;
import sk.ardevop.nlp.skquadmanager.model.CorpusBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.CorpusRestDTO;
import sk.ardevop.nlp.skquadmanager.repository.CorpusRepository;

@RestController
@CrossOrigin("*")
public class CorpusApiController implements CorpusApi {

  private final RestDTOMapper restDTOMapper;
  private final CorpusRepository corpusRepository;

  public CorpusApiController(RestDTOMapper restDTOMapper,
      CorpusRepository corpusRepository) {
    this.restDTOMapper = restDTOMapper;
    this.corpusRepository = corpusRepository;
  }

  @Override
  public ResponseEntity<CorpusRestDTO> createCorpus(@Valid CorpusBaseRestDTO corpusBaseRestDTO) {
    return ResponseEntity.ok(
        restDTOMapper.corpusRestDTO(
            corpusRepository.save(restDTOMapper.corpus(corpusBaseRestDTO))));
  }

  @Override
  public ResponseEntity<Void> deleteCorpus(String corpusId) {
    corpusRepository.deleteById(corpusId);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<CorpusRestDTO> getCorpus(String corpusId) {
    return ResponseEntity.of(
        Optional.ofNullable(
            restDTOMapper.corpusRestDTO(
                corpusRepository.findById(corpusId).orElse(null))));
  }

  @Override
  public ResponseEntity<List<CorpusRestDTO>> listCorpus() {
    return ResponseEntity
        .ok(StreamEx.of(corpusRepository.findAll().iterator()).map(restDTOMapper::corpusRestDTO).collect(toList()));
  }

}
