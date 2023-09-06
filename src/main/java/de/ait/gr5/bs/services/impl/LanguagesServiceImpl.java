package de.ait.gr5.bs.services.impl;

import de.ait.gr5.bs.dto.LanguageDto;
import de.ait.gr5.bs.dto.LanguagesDto;
import de.ait.gr5.bs.models.Language;
import de.ait.gr5.bs.repositories.LanguagesRepository;
import de.ait.gr5.bs.services.LanguagesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class LanguagesServiceImpl implements LanguagesService {

  LanguagesRepository languagesRepository;

  @Override
  public LanguagesDto getLanguages() {

    List<Language> languages = languagesRepository.findAll();

    return LanguagesDto.from(LanguageDto.from(languages));
  }
}
