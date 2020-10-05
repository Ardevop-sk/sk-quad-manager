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
import sk.ardevop.nlp.skquadmanager.entity.Corpus;
import sk.ardevop.nlp.skquadmanager.entity.Dataset;
import sk.ardevop.nlp.skquadmanager.model.DatasetBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.DatasetRestDTO;
import sk.ardevop.nlp.skquadmanager.repository.DatasetRepository;

@RestController
@CrossOrigin("*")
public class DatasetApiController implements DatasetApi {

  private final RestDTOMapper restDTOMapper;
  private final DatasetRepository datasetRepository;

  public DatasetApiController(RestDTOMapper restDTOMapper,
      DatasetRepository datasetRepository) {
    this.restDTOMapper = restDTOMapper;
    this.datasetRepository = datasetRepository;
  }

  @Override
  public ResponseEntity<DatasetRestDTO> createCorpusDataset(String corpusId,
      @Valid DatasetBaseRestDTO datasetBaseRestDTO) {
    Dataset dataset = restDTOMapper.dataset(datasetBaseRestDTO);
    dataset.setCorpus(Corpus.builder().id(corpusId).build());
    return ResponseEntity.ok(restDTOMapper.datasetRestDTO(datasetRepository.save(dataset)));
  }

  @Override
  public ResponseEntity<Void> deleteDataset(String datasetId) {
    datasetRepository.deleteById(datasetId);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<List<DatasetRestDTO>> listDatasets(String corpusId) {
    return ResponseEntity.ok(
        StreamEx.of(datasetRepository.findAllByCorpus_Id(corpusId).iterator())
            .map(restDTOMapper::datasetRestDTO)
            .collect(toList())
    );
  }

}
