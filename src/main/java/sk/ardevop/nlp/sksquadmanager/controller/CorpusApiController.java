package sk.ardevop.nlp.sksquadmanager.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sk.ardevop.nlp.sksquadmanager.api.CorpusApi;
import sk.ardevop.nlp.sksquadmanager.controller.mapper.RestDTOMapper;
import sk.ardevop.nlp.sksquadmanager.model.CorpusBaseRestDTO;
import sk.ardevop.nlp.sksquadmanager.model.CorpusRestDTO;
import sk.ardevop.nlp.sksquadmanager.repository.CorpusRepository;

@RestController
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
}
