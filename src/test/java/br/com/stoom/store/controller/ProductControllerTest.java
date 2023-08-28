package br.com.stoom.store.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.stoom.store.TestSecurityConfig.WithMockCustomUser;
import br.com.stoom.store.builder.MockProductBuilder;
import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.dto.request.ProductRequest;
import br.com.stoom.store.dto.response.ProductResponse;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private ProductBO productService;
 
 private ProductResponse productResponse;
 private List<ProductResponse> productResponseList;
 private ProductRequest request;
 
 @Autowired
 private ObjectMapper objectMapper;
 
 @BeforeEach
 public void setup() {
	 this.request = MockProductBuilder.buildProductRequest();
     this.productResponseList = Arrays.asList(
	         MockProductBuilder.buildProductResponse(1L),
	         MockProductBuilder.buildProductResponse(2L)
	     );
     this.productResponse = MockProductBuilder.buildProductResponse(1L);
   
 }
 
	 @Test
	 @WithMockCustomUser(username = "user", password = "password", roles = "ADMIN_TEST")
	 public void testFindAll() throws Exception {
	     when(productService.findAll()).thenReturn(productResponseList);
	
	     mockMvc.perform(MockMvcRequestBuilders
	         .get("/api/products/")
	         .accept(MediaType.APPLICATION_JSON))
	         .andExpect(status().isOk())
	         .andExpect(jsonPath("$.length()").value(productResponseList.size()))
	         .andExpect(jsonPath("$[0].id").value(1))
	         .andExpect(jsonPath("$[1].id").value(2));
	 }
	 
	 @Test
	 @WithMockCustomUser(username = "user", password = "password", roles = "ADMIN_TEST")
	 public void testFindProductById() throws Exception {
	     Long productId = 1L;
	     
	     when(productService.findProductById(productId)).thenReturn(productResponse);

	     mockMvc.perform(MockMvcRequestBuilders
	             .get("/api/products/"+productId)
	             .accept(MediaType.APPLICATION_JSON))
	             .andExpect(status().isOk())
	             .andExpect(jsonPath("$.id").value(productId))
	             .andExpect(jsonPath("$.name").value(productResponse.getName()));
	 }
	 
	 @Test
	 @WithMockCustomUser(username = "user", password = "password", roles = "ADMIN_TEST")
	 public void testDeleteProduct() throws Exception {
	     Long productId = 1L;

	    doNothing().when(productService).delete(productId);

	     mockMvc.perform(MockMvcRequestBuilders
	             .delete("/api/products/" + productId))
	             .andExpect(status().isNoContent());

	     verify(productService, times(1)).delete(productId);
	 }
	 
	 @Test
	 @WithMockCustomUser(username = "user", password = "password", roles = "ADMIN_TEST")
	 public void testCreateProduct() throws Exception {
	  
		 when(productService.create(request)).thenReturn(productResponse);

	     mockMvc.perform(MockMvcRequestBuilders
	             .post("/api/products")
	             .contentType(MediaType.APPLICATION_JSON)
	             .content(objectMapper.writeValueAsString(request)))
	             .andExpect(status().isCreated())
	             .andExpect(jsonPath("$.id").exists());
	       
	             verify(productService, times(1)).create(request);
	 }
	 
	 @Test
	 @WithMockCustomUser(username = "user", password = "password", roles = "ADMIN_TEST")
	 public void testUpdateProductStatus() throws Exception {
	     Long productId = 1L;
	     boolean newStatus = true;

	     when(productService.updateProductStatus(productId, newStatus)).thenReturn(productResponse);

	     mockMvc.perform(patch("/api/products/{productId}/status", productId)
	             .param("active", String.valueOf(newStatus))
	             .accept(MediaType.APPLICATION_JSON))
	             .andExpect(status().isOk())
	             .andExpect(jsonPath("$.id").value(productId))
	             .andExpect(jsonPath("$.active").value(newStatus));
	 }
	 
	@Test
	@WithMockCustomUser(username = "user", password = "password", roles = "ADMIN_TEST")
    public void testGetProductsByCategory() throws Exception {
        Long categoryId = 1L;
      
        when(productService.getProductsByCategory(categoryId)).thenReturn(productResponseList);

        mockMvc.perform(get("/api/products/category/{categoryId}", categoryId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(productResponseList.size()));
        
        verify(productService, times(1)).getProductsByCategory(categoryId);
    }

	@Test
    @WithMockCustomUser(username = "user", password = "password", roles = "ADMIN_TEST")
    public void testGetProductsByBrand() throws Exception {
        Long brandId = 1L;

        when(productService.getProductsByBrand(brandId)).thenReturn(productResponseList);

        mockMvc.perform(get("/api/products/brand/{brandId}", brandId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(productResponseList.size()));
        
        verify(productService, times(1)).getProductsByBrand(brandId);
    }
	
	
	@Test
	@WithMockCustomUser(username = "user", password = "password", roles = "ADMIN_TEST")
    public void testUpdateProduct() throws Exception {
        Long productId = 1L;

        when(productService.update(productId, request)).thenReturn(productResponse);

        mockMvc.perform(put("/api/products/{id}", productId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(productResponse.getId()));
        
        verify(productService, times(1)).update(productId, request);
    }
	 

}
