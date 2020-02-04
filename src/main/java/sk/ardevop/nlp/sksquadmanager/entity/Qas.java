package sk.ardevop.nlp.sksquadmanager.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Qas {

  @Id
  private String id;

  private String question;
  private List<Answer> answers = new ArrayList<>();
  private Boolean isImpossible;
  private List<Answer> plaussibleAnswers = null;
}

