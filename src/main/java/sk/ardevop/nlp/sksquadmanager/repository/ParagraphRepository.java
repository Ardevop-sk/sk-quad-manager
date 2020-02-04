package sk.ardevop.nlp.sksquadmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sk.ardevop.nlp.sksquadmanager.entity.Paragraph;

public interface ParagraphRepository extends MongoRepository<Paragraph, String> {

}
