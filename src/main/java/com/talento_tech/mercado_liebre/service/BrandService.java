package com.talento_tech.mercado_liebre.service;

import com.talento_tech.mercado_liebre.dto.BrandDTO_WithProducts;
import com.talento_tech.mercado_liebre.dto.SectionDTO_WithProducts;
import com.talento_tech.mercado_liebre.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<Brand> listBrands();
    Optional<BrandDTO_WithProducts> getBrandById(Long id);
    Brand createBrand(Brand brand);
    Brand updateBrand(Long id, Brand brand);
    void deleteBrandById(Long id);
}
