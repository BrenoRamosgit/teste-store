package br.com.stoom.store.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import br.com.stoom.store.builder.MockBrandBuilder;
import br.com.stoom.store.builder.MockCategoryBuilder;
import br.com.stoom.store.builder.MockProductBuilder;
import br.com.stoom.store.dto.request.ProductFilterRequest;
import br.com.stoom.store.dto.request.ProductRequest;
import br.com.stoom.store.dto.response.ProductResponse;
import br.com.stoom.store.mapper.ProductMapper;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.ProductRepository;
import br.com.stoom.store.repository.specification.ProductSpecifications;

@ExtendWith(MockitoExtension.class) 
public class ProductBOTest {

    @Mock
    private ProductRepository productRepository;
    
    @Mock
    private BrandBO brandService;
    
    @Mock
    private CategoryBO categoryService;
    
    @Mock
    private ProductMapper mapper;
   
    @Mock
    private ProductSpecifications productSpecifications;
    
    @InjectMocks
    private ProductBO productBO; 

    private ProductResponse productResponse;
    private Product product;
    private List<ProductResponse> productResponseList;
    private ProductRequest request;
    private ProductFilterRequest filterRequest;
    private Page<Product> productPage;
    private Pageable pageable;
    private Brand brand;
    private Category category;
    private Long productId = 1L;
    private Long categoryId = 1L;
    private Long brandId = 1L;
    
    @BeforeEach
    public void setUp() {
    	 this.request = MockProductBuilder.buildProductRequest();
         this.productResponseList = Arrays.asList(
    	         MockProductBuilder.buildProductResponse(productId),
    	         MockProductBuilder.buildProductResponse(2L)
    	     );
         this.productResponse = MockProductBuilder.buildProductResponse(productId);
         this.filterRequest = MockProductBuilder.buildProductFilterRequest();
         this.product = MockProductBuilder.buildProduct(productId);
         this.pageable = PageRequest.of(0, 10);
         this.productPage = new PageImpl<>(Arrays.asList(product), pageable, 1);
         this.brand = MockBrandBuilder.buildBrand(brandId);
         this.category = MockCategoryBuilder.buildCategory(categoryId);
         
         
    }

    @Test
    public void testFindAll() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        when(mapper.response(anyList())).thenReturn(productResponseList);
        
       productBO.findAll();
        
        verify(productRepository).findAll();
        verify(mapper).response(anyList());
    }

    @Test
    public void testFindProductsByFilters() {

        Specification<Product> spec = mock(Specification.class);
        when(productSpecifications.withFilters(filterRequest)).thenReturn(spec);
        when(productRepository.findAll(spec, pageable)).thenReturn(productPage);

        productBO.findProductsByFilters(filterRequest, pageable);

        verify(productRepository).findAll(spec, pageable);
    }
    
    
    @Test
    public void testCreateProduct() {
        when(mapper.model(request)).thenReturn(product);
        when(brandService.findById(anyLong())).thenReturn(brand);
        when(categoryService.findById(anyLong())).thenReturn(category);
        when(productRepository.save(product)).thenReturn(product);
        when(mapper.response(product)).thenReturn(productResponse);

        ProductResponse result = productBO.create(request);

        verify(mapper).model(request);
        verify(brandService).findById(anyLong());
        verify(categoryService).findById(anyLong());
        verify(productRepository).save(product);
        verify(mapper).response(product);

        assertEquals(productResponse, result);
    }
    
    @Test
    public void testFindProductById() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(mapper.response(product)).thenReturn(productResponse);

        ProductResponse result = productBO.findProductById(productId);
        assertEquals(productResponse, result);
    }

    @Test
    public void testUpdate() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(mapper.updateProductFromRequest(any(), eq(product))).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(mapper.response(product)).thenReturn(productResponse);

        ProductResponse result = productBO.update(productId, request);

        assertEquals(productResponse, result);
    }

    @Test
    public void testDelete() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        productBO.delete(1L);
        verify(productRepository).delete(product);
    }
    
    @Test
    public void testUpdateProductStatus() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        when(mapper.response(product)).thenReturn(productResponse);

        ProductResponse result = productBO.updateProductStatus(productId, true);

        assertEquals(productResponse, result);
        assertTrue(product.getActive()); 
    }

    @Test
    public void testGetProductsByCategory() {
       
        when(productRepository.findByCategories_IdAndActive(categoryId, true)).thenReturn(Arrays.asList(product));
        when(mapper.response(Arrays.asList(product))).thenReturn(Arrays.asList(productResponse, productResponse));

        List<ProductResponse> result = productBO.getProductsByCategory(categoryId);

        assertEquals(2, result.size());
        assertEquals(productResponse, result.get(0));
    }

    @Test
    public void testGetProductsByBrand() {

        when(productRepository.findByBrand_IdAndActive(brandId, true)).thenReturn(Arrays.asList(product));
        when(mapper.response(Arrays.asList(product))).thenReturn(Arrays.asList(productResponse, productResponse));

        List<ProductResponse> result = productBO.getProductsByBrand(brandId);

        assertEquals(2, result.size());
        assertEquals(productResponse, result.get(0));
    }


}
