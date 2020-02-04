package sk.ardevop.nlp.skquadmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sk.ardevop.nlp.skquadmanager.entity.Answer;

public interface AnswerRepository extends MongoRepository<Answer, String> {

}
