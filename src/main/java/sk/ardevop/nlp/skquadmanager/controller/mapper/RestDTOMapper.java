package sk.ardevop.nlp.skquadmanager.controller.mapper;

import static java.util.stream.Collectors.toList;

import org.mapstruct.Mapper;
import sk.ardevop.nlp.skquadmanager.entity.Answer;
import sk.ardevop.nlp.skquadmanager.entity.Corpus;
import sk.ardevop.nlp.skquadmanager.entity.Dataset;
import sk.ardevop.nlp.skquadmanager.entity.Paragraph;
import sk.ardevop.nlp.skquadmanager.entity.Question;
import sk.ardevop.nlp.skquadmanager.model.AnswerBaseRestDTO;
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

  default QuestionRestDTO questionRestDTO(Question question) {
    if (question == null) {
      return null;
    }
    return new QuestionRestDTO()
        .id(question.getId())
        .answers(question.getAnswers() == null ? null
            : question.getAnswers().stream().map(this::answerRestDTO).collect(toList()))
        .isImpossible(question.getIsImpossible())
        .question(question.getQuestion())
        .plaussibleAnswers(question.getPlaussibleAnswers() == null ? null :
            question.getPlaussibleAnswers().stream().map(this::answerRestDTO).collect(toList()))
        .paragraphText(question.getParagraph().getContext());
  }

  Answer answer(AnswerBaseRestDTO answerRestDTO);

  AnswerRestDTO answerRestDTO(Answer answer);
}
