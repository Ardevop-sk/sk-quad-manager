package sk.ardevop.nlp.skquadmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sk.ardevop.nlp.skquadmanager.entity.Dataset;

public interface DatasetRepository extends MongoRepository<Dataset, String> {

}
