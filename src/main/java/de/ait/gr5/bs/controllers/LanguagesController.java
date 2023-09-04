package de.ait.gr5.bs.controllers;

import de.ait.gr5.bs.controllers.api.LanguagesApi;
import de.ait.gr5.bs.dto.LanguagesDto;
import de.ait.gr5.bs.services.LanguagesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
public class LanguagesController implements LanguagesApi {

  LanguagesService languagesService;

  @Override
  public ResponseEntity<LanguagesDto> getLanguages() {
    return ResponseEntity.ok()
        .body(languagesService.getLanguages());
  }

}
