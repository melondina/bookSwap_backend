package de.ait.gr5.bs.controllers;

import de.ait.gr5.bs.controllers.api.CategoriesApi;
import de.ait.gr5.bs.dto.CategoriesDto;
import de.ait.gr5.bs.services.CategoriesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
public class CategoriesController implements CategoriesApi {

  CategoriesService categoriesService;

  @Override
  public ResponseEntity<CategoriesDto> getCategories() {
    return ResponseEntity.ok()
        .body(categoriesService.getCategories());
  }


}
