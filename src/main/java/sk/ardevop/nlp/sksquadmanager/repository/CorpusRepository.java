package sk.ardevop.nlp.sksquadmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sk.ardevop.nlp.sksquadmanager.entity.Corpus;

public interface CorpusRepository extends MongoRepository<Corpus, String> {

}
