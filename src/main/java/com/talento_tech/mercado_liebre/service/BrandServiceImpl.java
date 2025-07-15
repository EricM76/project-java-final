package com.talento_tech.mercado_liebre.service;

import com.talento_tech.mercado_liebre.dto.*;
import com.talento_tech.mercado_liebre.dto.BrandDTO;
import com.talento_tech.mercado_liebre.model.Brand;
import com.talento_tech.mercado_liebre.model.Category;
import com.talento_tech.mercado_liebre.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService{

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository){
        this.brandRepository = brandRepository;
    }

    private BrandDTO_WithProducts mapToBrandDTOWithProducts(Brand category) {
        List<ProductDTO> productDTOs = category.getProducts().stream()
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

        return new BrandDTO_WithProducts(
                category.getId(),
                category.getName(),
                category.getImage(),
                productDTOs
        );
    }
    @Override
    public List<Brand> listBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<BrandDTO_WithProducts> getBrandById(Long id) {
        return brandRepository.findById(id)
                .map(this::mapToBrandDTOWithProducts);
    }

    @Override
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(Long id, Brand brand) {
        brand.setId(id);
        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrandById(Long id) {
        brandRepository.deleteById(id);
    }
}
