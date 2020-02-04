package sk.ardevop.nlp.skquadmanager.controller.mapper;

import org.mapstruct.Mapper;
import sk.ardevop.nlp.skquadmanager.entity.Corpus;
import sk.ardevop.nlp.skquadmanager.entity.Dataset;
import sk.ardevop.nlp.skquadmanager.model.CorpusBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.CorpusRestDTO;
import sk.ardevop.nlp.skquadmanager.model.DatasetBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.DatasetRestDTO;

@Mapper(componentModel = "spring")
public interface RestDTOMapper {
  Corpus corpus(CorpusBaseRestDTO corpusBaseRestDTO);
  CorpusRestDTO corpusRestDTO(Corpus corpus);

  Dataset dataset(DatasetBaseRestDTO datasetBaseRestDTO);
  DatasetRestDTO datasetRestDTO(Dataset corpus);
}
