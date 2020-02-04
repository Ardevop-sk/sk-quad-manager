package sk.ardevop.nlp.sksquadmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Corpus {

  @Id
  private String id;
  private String version;
  private List<Dataset> data = new ArrayList<>();
  private String language = "en";
}

