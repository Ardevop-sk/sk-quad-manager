package sk.ardevop.nlp.skquadmanager.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import sk.ardevop.nlp.skquadmanager.entity.Dataset;

public interface DatasetRepository extends PagingAndSortingRepository<Dataset, String> {

  Iterable<Dataset> findAllByCorpus_Id(String corpusId);
}
