package sk.ardevop.nlp.skquadmanager.controller.mapper;

import org.mapstruct.Mapper;
import sk.ardevop.nlp.skquadmanager.entity.Answer;
import sk.ardevop.nlp.skquadmanager.entity.Corpus;
import sk.ardevop.nlp.skquadmanager.entity.Dataset;
import sk.ardevop.nlp.skquadmanager.entity.Paragraph;
import sk.ardevop.nlp.skquadmanager.entity.Question;
import sk.ardevop.nlp.skquadmanager.model.AnswerRestDTO;
import sk.ardevop.nlp.skquadmanager.model.CorpusBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.CorpusRestDTO;
import sk.ardevop.nlp.skquadmanager.model.DatasetBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.DatasetRestDTO;
import sk.ardevop.nlp.skquadmanager.model.ParagraphBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.ParagraphRestDTO;
import sk.ardevop.nlp.skquadmanager.model.QuestionBaseRestDTO;
import sk.ardevop.nlp.skquadmanager.model.QuestionRestDTO;

@Mapper(componentModel = "spring")
public interface RestDTOMapper {
  Corpus corpus(CorpusBaseRestDTO corpusBaseRestDTO);
  CorpusRestDTO corpusRestDTO(Corpus corpus);

  Dataset dataset(DatasetBaseRestDTO datasetBaseRestDTO);
  DatasetRestDTO datasetRestDTO(Dataset corpus);

  Paragraph paragraph(ParagraphBaseRestDTO paragraphBaseRestDTO);

  ParagraphRestDTO paragraphRestDTO(Paragraph paragraph);

  Question question(QuestionBaseRestDTO questionBaseRestDTO);

  QuestionRestDTO questionRestDTO(Question question);

  Answer answer(AnswerRestDTO answerRestDTO);

  AnswerRestDTO answerRestDTO(Answer answer);
}
