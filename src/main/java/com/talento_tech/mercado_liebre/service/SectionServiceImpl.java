package com.talento_tech.mercado_liebre.service;

import com.talento_tech.mercado_liebre.dto.*;
import com.talento_tech.mercado_liebre.model.Section;
import com.talento_tech.mercado_liebre.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService{

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionServiceImpl(SectionRepository sectionRepository){
        this.sectionRepository = sectionRepository;
    }

    private SectionDTO_WithProducts mapToSectionDTOWithProducts(Section section) {
        List<ProductDTO> productDTOs = section.getProducts().stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getDiscount(),
                        product.getImage(),
                        product.getDescription(),
                        product.getStock(),
                        new CategoryDTO(product.getCategory().getId(), product.getCategory().getName(), product.getCategory().getImage()),
                        new BrandDTO(product.getBrand().getId(), product.getBrand().getName(), product.getBrand().getImage()),
                        new SectionDTO(product.getSection().getId(), product.getSection().getName())
                ))
                .collect(Collectors.toList());

        return new SectionDTO_WithProducts(
                section.getId(),
                section.getName(),
                productDTOs
        );
    }
    @Override
    public List<Section> listSections() {
        return sectionRepository.findAll();
    }

    @Override
    public Optional<SectionDTO_WithProducts> getSectionById(Long id) {
        return sectionRepository.findById(id)
                .map(this::mapToSectionDTOWithProducts);
    }

    @Override
    public Section createSection(Section section){
        return sectionRepository.save(section);
    }
}
