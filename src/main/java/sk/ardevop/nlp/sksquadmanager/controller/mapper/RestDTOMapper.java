package sk.ardevop.nlp.sksquadmanager.controller.mapper;

import org.mapstruct.Mapper;
import sk.ardevop.nlp.sksquadmanager.entity.Corpus;
import sk.ardevop.nlp.sksquadmanager.entity.Dataset;
import sk.ardevop.nlp.sksquadmanager.model.CorpusBaseRestDTO;
import sk.ardevop.nlp.sksquadmanager.model.CorpusRestDTO;
import sk.ardevop.nlp.sksquadmanager.model.DatasetBaseRestDTO;
import sk.ardevop.nlp.sksquadmanager.model.DatasetRestDTO;

@Mapper(componentModel = "spring")
public interface RestDTOMapper {
  Corpus corpus(CorpusBaseRestDTO corpusBaseRestDTO);
  CorpusRestDTO corpusRestDTO(Corpus corpus);

  Dataset dataset(DatasetBaseRestDTO datasetBaseRestDTO);
  DatasetRestDTO datasetRestDTO(Dataset corpus);
}
