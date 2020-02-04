package sk.ardevop.nlp.skquadmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sk.ardevop.nlp.skquadmanager.entity.Corpus;

public interface CorpusRepository extends MongoRepository<Corpus, String> {

}
