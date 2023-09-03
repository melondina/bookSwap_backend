package de.ait.gr5.bs.services.impl;

import de.ait.gr5.bs.dto.CategoriesDto;
import de.ait.gr5.bs.dto.CategoryDto;
import de.ait.gr5.bs.models.Category;
import de.ait.gr5.bs.repositories.CategoriesRepository;
import de.ait.gr5.bs.services.CategoriesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class CategoriesServiceImpl implements CategoriesService {

  CategoriesRepository categoriesRepository;

  @Override
  public CategoriesDto getCategories() {

    List<Category> categories = categoriesRepository.findAll();

    return CategoriesDto.from(CategoryDto.from(categories));
  }
}
