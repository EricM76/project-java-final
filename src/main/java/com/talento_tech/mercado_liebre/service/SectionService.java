package com.talento_tech.mercado_liebre.service;

import com.talento_tech.mercado_liebre.dto.SectionDTO_WithProducts;
import com.talento_tech.mercado_liebre.model.Section;

import java.util.List;
import java.util.Optional;

public interface SectionService {
    List<Section> listSections();
    Optional<SectionDTO_WithProducts> getSectionById(Long id);
    Section createSection(Section section);
}
