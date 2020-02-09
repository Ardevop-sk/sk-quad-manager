package sk.ardevop.nlp.skquadmanager.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import sk.ardevop.nlp.skquadmanager.entity.Answer;

public interface AnswerRepository extends PagingAndSortingRepository<Answer, String> {

}
