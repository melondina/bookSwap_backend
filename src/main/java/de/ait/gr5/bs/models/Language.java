package de.ait.gr5.bs.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Language {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long language;

  @Column(nullable = false)
  private String title;
}
