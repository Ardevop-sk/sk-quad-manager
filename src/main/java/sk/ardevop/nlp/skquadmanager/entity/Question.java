package sk.ardevop.nlp.skquadmanager.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = "paragraph")
@EqualsAndHashCode(exclude = "paragraph")
public class Question {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  private String question;
  @ManyToOne(fetch = FetchType.LAZY)
  private Paragraph paragraph;
  @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
  private List<Answer> answers;
  private Boolean isImpossible;
  @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
  private List<Answer> plaussibleAnswers;
}

