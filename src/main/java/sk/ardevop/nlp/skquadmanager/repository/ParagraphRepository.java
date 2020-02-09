package sk.ardevop.nlp.skquadmanager.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import sk.ardevop.nlp.skquadmanager.entity.Paragraph;

public interface ParagraphRepository extends PagingAndSortingRepository<Paragraph, String> {

  Iterable<Paragraph> findAllByDataset_Id(String datasetId);
}
