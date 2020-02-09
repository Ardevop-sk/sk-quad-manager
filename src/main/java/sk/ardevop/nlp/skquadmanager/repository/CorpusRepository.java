package sk.ardevop.nlp.skquadmanager.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import sk.ardevop.nlp.skquadmanager.entity.Corpus;

public interface CorpusRepository extends PagingAndSortingRepository<Corpus, String> {

}
