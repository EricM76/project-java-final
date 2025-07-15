package com.talento_tech.mercado_liebre.controller;

import com.talento_tech.mercado_liebre.dto.SectionDTO_WithProducts;
import com.talento_tech.mercado_liebre.model.Section;
import com.talento_tech.mercado_liebre.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionController {
    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping
    public List<Section> list() {
        return sectionService.listSections();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionDTO_WithProducts> findById(@PathVariable Long id) {
        return sectionService.getSectionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Section create(@RequestBody Section section) {
        return sectionService.createSection((section));
    }
}
