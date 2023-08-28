package br.com.stoom.store.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.stoom.store.builder.MockBrandBuilder;
import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.dto.request.BrandRequest;
import br.com.stoom.store.dto.response.BrandResponse;

@WebMvcTest(BrandController.class)
@WithMockUser(username = "user", password = "password", roles = "ADMIN_TEST")
public class BrandControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private BrandBO brandService;
 
 private List<BrandResponse> brandResponseList;
 private BrandResponse brandResponse;
 private BrandRequest request;
 
 @Autowired
 private ObjectMapper objectMapper;
 
 @BeforeEach
 public void setup() {
     this.brandResponseList = Arrays.asList(
	         MockBrandBuilder.buildBrandResponse(1L),
	         MockBrandBuilder.buildBrandResponse(2L)
	     );
     this.brandResponse = MockBrandBuilder.buildBrandResponse(1L);
     this.request = MockBrandBuilder.buildBrandRequest();
    
   
 }
 
	 @Test
	 public void testFindAll() throws Exception {
	     when(brandService.findAll()).thenReturn(brandResponseList);
	
	     mockMvc.perform(MockMvcRequestBuilders
	         .get("/api/brands/")
	         .accept(MediaType.APPLICATION_JSON))
	         .andExpect(status().isOk())
	         .andExpect(jsonPath("$.length()").value(brandResponseList.size()))
	         .andExpect(jsonPath("$[0].id").value(1))
	         .andExpect(jsonPath("$[1].id").value(2));
	 }
	 
	 @Test
	 public void testFindBrandById() throws Exception {
	     Long brandId = 1L;
	     
	     when(brandService.findBrandById(brandId)).thenReturn(brandResponse);

	     mockMvc.perform(MockMvcRequestBuilders
	             .get("/api/brands/"+brandId)
	             .accept(MediaType.APPLICATION_JSON))
	             .andExpect(status().isOk())
	             .andExpect(jsonPath("$.id").value(brandId))
	             .andExpect(jsonPath("$.name").value(brandResponse.getName()));
	 }
	 
	 @Test
	 public void testDeleteBrand() throws Exception {
	     Long id = 1L;

	    doNothing().when(brandService).delete(id);

	     mockMvc.perform(MockMvcRequestBuilders
	             .delete("/api/brands/" + id))
	             .andExpect(status().isNoContent());

	     verify(brandService, times(1)).delete(id);
	 }
	 
	 @Test
	 public void testCreateBrand() throws Exception {
	  
		 when(brandService.create(request)).thenReturn(brandResponse);

	     mockMvc.perform(MockMvcRequestBuilders
	             .post("/api/brands")
	             .contentType(MediaType.APPLICATION_JSON)
	             .content(objectMapper.writeValueAsString(request)))
	             .andExpect(status().isCreated())
	             .andExpect(jsonPath("$.id").exists());
	       
	             verify(brandService, times(1)).create(request);
	 }
	 
	 @Test
	 public void testUpdateBrandStatus() throws Exception {
	     Long brandId = 1L;
	     boolean newStatus = true;

	     when(brandService.updateBrandStatus(brandId, newStatus)).thenReturn(brandResponse);

	     mockMvc.perform(patch("/api/brands/{brandId}/status", brandId)
	             .param("active", String.valueOf(newStatus))
	             .accept(MediaType.APPLICATION_JSON))
	             .andExpect(status().isOk())
	             .andExpect(jsonPath("$.id").value(brandId))
	             .andExpect(jsonPath("$.active").value(newStatus));
	 }
	
	@Test
    public void testUpdateBrand() throws Exception {
        Long brandId = 1L;

        when(brandService.update(brandId, request)).thenReturn(brandResponse);

        mockMvc.perform(put("/api/brands/{id}", brandId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(brandResponse.getId()));
        
        verify(brandService, times(1)).update(brandId, request);
    }
	 

}
