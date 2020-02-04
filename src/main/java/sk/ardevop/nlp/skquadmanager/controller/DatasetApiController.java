package sk.ardevop.nlp.skquadmanager.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sk.ardevop.nlp.skquadmanager.api.DatasetApi;
import sk.ardevop.nlp.skquadmanager.controller.mapper.RestDTOMapper;
import sk.ardevop.nlp.skquadmanager.model.DatasetBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.DatasetRestDTO;
import sk.ardevop.nlp.skquadmanager.repository.DatasetRepository;

@RestController
public class DatasetApiController implements DatasetApi {

  private final RestDTOMapper restDTOMapper;
  private final DatasetRepository datasetRepository;

  public DatasetApiController(RestDTOMapper restDTOMapper,
      DatasetRepository datasetRepository) {
    this.restDTOMapper = restDTOMapper;
    this.datasetRepository = datasetRepository;
  }

  @Override
  public ResponseEntity<DatasetRestDTO> createCorpusDataset(@Valid DatasetBaseRestDTO datasetBaseRestDTO) {
    return ResponseEntity.ok(
        restDTOMapper.datasetRestDTO(
            datasetRepository.save(restDTOMapper.dataset(datasetBaseRestDTO))));
  }
}
