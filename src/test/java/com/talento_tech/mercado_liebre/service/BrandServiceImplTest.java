package com.talento_tech.mercado_liebre.service;

import com.talento_tech.mercado_liebre.dto.BrandDTO_WithProducts;
import com.talento_tech.mercado_liebre.model.Brand;
import com.talento_tech.mercado_liebre.repository.BrandRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BrandServiceImplTest {
    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    @DisplayName("Debería retornar si la marca existe")
    void shouldReturnBrandIfExists(){
        Long brandId = 1L;
        Brand mockBrand = new Brand(brandId, "Apple", null);

        when(brandRepository.findById(brandId)).thenReturn(Optional.of(mockBrand));

        Optional<BrandDTO_WithProducts> result = brandService.getBrandById(brandId);

        assertTrue(result.isPresent());
        verify(brandRepository, times(1)).findById(brandId);

    }
}
