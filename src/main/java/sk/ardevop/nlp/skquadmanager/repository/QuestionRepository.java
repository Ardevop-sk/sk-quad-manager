package sk.ardevop.nlp.skquadmanager.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import sk.ardevop.nlp.skquadmanager.entity.Question;

public interface QuestionRepository extends PagingAndSortingRepository<Question, String> {

}
