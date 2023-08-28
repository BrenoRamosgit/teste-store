package br.com.stoom.store.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.stoom.store.builder.MockBrandBuilder;
import br.com.stoom.store.dto.request.BrandRequest;
import br.com.stoom.store.dto.response.BrandResponse;
import br.com.stoom.store.mapper.BrandMapper;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.repository.BrandRepository;

@ExtendWith(MockitoExtension.class) 
public class BrandBOTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandMapper mapper;

    @InjectMocks
    private BrandBO brandBO;

    private Brand brand;
    private BrandResponse brandResponse;
    private BrandRequest brandRequest;
    private Long brandId = 1L;

    @BeforeEach
    public void setUp() {

        this.brand = MockBrandBuilder.buildBrand(brandId);
        this.brandResponse = MockBrandBuilder.buildBrandResponse(brandId);
        this.brandRequest = MockBrandBuilder.buildBrandRequest();
    }

    @Test
    public void testCreateBrand() {
        when(mapper.model(brandRequest)).thenReturn(brand);
        when(brandRepository.save(brand)).thenReturn(brand);
        when(mapper.response(brand)).thenReturn(brandResponse);

        BrandResponse result = brandBO.create(brandRequest);

        assertEquals(brandResponse, result);
    }

    @Test
    public void testFindAllBrands() {
        when(brandRepository.findAll()).thenReturn(Collections.emptyList());
        when(mapper.response(anyList())).thenReturn(Collections.emptyList());

        List<BrandResponse> result = brandBO.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindBrandById() {
        when(brandRepository.findById(brandId)).thenReturn(Optional.of(brand));
        when(mapper.response(brand)).thenReturn(brandResponse);

        BrandResponse result = brandBO.findBrandById(brandId);

        assertEquals(brandResponse, result);
    }

    @Test
    public void testUpdateBrand() {
        when(brandRepository.findById(brandId)).thenReturn(Optional.of(brand));
        when(brandRepository.save(brand)).thenReturn(brand);
        when(mapper.response(brand)).thenReturn(brandResponse);

        BrandResponse result = brandBO.update(brandId, brandRequest);

        assertEquals(brandResponse, result);
    }

    @Test
    public void testUpdateBrandStatus() {
        when(brandRepository.findById(brandId)).thenReturn(Optional.of(brand));
        when(brandRepository.save(brand)).thenReturn(brand);
        when(mapper.response(brand)).thenReturn(brandResponse);

        BrandResponse result = brandBO.updateBrandStatus(brandId, true);

        assertEquals(brandResponse, result);
        assertTrue(brand.getActive());
    }

    @Test
    public void testDeleteBrand() {
        when(brandRepository.findById(brandId)).thenReturn(Optional.of(brand));

        brandBO.delete(brandId);

        verify(brandRepository).delete(brand);
    }
}
