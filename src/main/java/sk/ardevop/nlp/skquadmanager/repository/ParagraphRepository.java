package sk.ardevop.nlp.skquadmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sk.ardevop.nlp.skquadmanager.entity.Paragraph;

public interface ParagraphRepository extends MongoRepository<Paragraph, String> {

}
