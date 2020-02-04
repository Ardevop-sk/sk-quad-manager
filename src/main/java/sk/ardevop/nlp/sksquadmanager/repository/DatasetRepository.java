package sk.ardevop.nlp.sksquadmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sk.ardevop.nlp.sksquadmanager.entity.Dataset;

public interface DatasetRepository extends MongoRepository<Dataset, String> {

}
