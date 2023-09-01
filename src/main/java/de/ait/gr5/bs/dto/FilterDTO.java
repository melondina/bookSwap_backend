package de.ait.gr5.bs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilterDTO {
  private List<String> language;
  private List<String> location;
  private List<String> category;

}
