package sk.ardevop.nlp.sksquadmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sk.ardevop.nlp.sksquadmanager.entity.Answer;

public interface AnswerRepository extends MongoRepository<Answer, String> {

}
