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

import br.com.stoom.store.builder.MockCategoryBuilder;
import br.com.stoom.store.business.CategoryBO;
import br.com.stoom.store.dto.request.CategoryRequest;
import br.com.stoom.store.dto.response.CategoryResponse;

@WebMvcTest(CategoryController.class)
@WithMockUser(username = "user", password = "password", roles = "ADMIN_TEST")
public class CategoryControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private CategoryBO categoryService;
 
 private List<CategoryResponse> categoryResponseList;
 private CategoryResponse categoryResponse;
 private CategoryRequest request;
 
 @Autowired
 private ObjectMapper objectMapper;
 
 @BeforeEach
 public void setup() {
     this.categoryResponseList = Arrays.asList(
	         MockCategoryBuilder.buildCategoryResponse(1L),
	         MockCategoryBuilder.buildCategoryResponse(2L)
	     );
     this.categoryResponse = MockCategoryBuilder.buildCategoryResponse(1L);
     this.request = MockCategoryBuilder.buildCategoryRequest();
   
 }
 
	 @Test
	 public void testFindAll() throws Exception {
	     when(categoryService.findAll()).thenReturn(categoryResponseList);
	
	     mockMvc.perform(MockMvcRequestBuilders
	         .get("/api/categories")
	         .accept(MediaType.APPLICATION_JSON))
	         .andExpect(status().isOk())
	         .andExpect(jsonPath("$.length()").value(categoryResponseList.size()))
	         .andExpect(jsonPath("$[0].id").value(1))
	         .andExpect(jsonPath("$[1].id").value(2));
	 }
	 
	 @Test
	 public void testFindCategoryById() throws Exception {
	     Long id = 1L;
	     
	     when(categoryService.findCategoryById(id)).thenReturn(categoryResponse);

	     mockMvc.perform(MockMvcRequestBuilders
	             .get("/api/categories/"+id)
	             .accept(MediaType.APPLICATION_JSON))
	             .andExpect(status().isOk())
	             .andExpect(jsonPath("$.id").value(id))
	             .andExpect(jsonPath("$.name").value(categoryResponse.getName()));
	 }
	 
	 @Test
	 public void testDeleteCategory() throws Exception {
	     Long id = 1L;

	    doNothing().when(categoryService).delete(id);

	     mockMvc.perform(MockMvcRequestBuilders
	             .delete("/api/categories/" + id))
	             .andExpect(status().isNoContent());

	     verify(categoryService, times(1)).delete(id);
	 }
	 
	 @Test
	 public void testCreateCategory() throws Exception {
	  
		 when(categoryService.create(request)).thenReturn(categoryResponse);

	     mockMvc.perform(MockMvcRequestBuilders
	             .post("/api/categories")
	             .contentType(MediaType.APPLICATION_JSON)
	             .content(objectMapper.writeValueAsString(request)))
	             .andExpect(status().isCreated())
	             .andExpect(jsonPath("$.id").exists());
	       
	             verify(categoryService, times(1)).create(request);
	 }
	 
	 @Test
	 public void testUpdateCategoryStatus() throws Exception {
	     Long categoryId = 1L;
	     boolean newStatus = true;

	     when(categoryService.updateCategoryStatus(categoryId, newStatus)).thenReturn(categoryResponse);

	     mockMvc.perform(patch("/api/categories/{categoryId}/status", categoryId)
	             .param("active", String.valueOf(newStatus))
	             .accept(MediaType.APPLICATION_JSON))
	             .andExpect(status().isOk())
	             .andExpect(jsonPath("$.id").value(categoryId))
	             .andExpect(jsonPath("$.active").value(newStatus));
	 }
	
	@Test
    public void testUpdatCategory() throws Exception {
        Long categoryId = 1L;

        when(categoryService.update(categoryId, request)).thenReturn(categoryResponse);

        mockMvc.perform(put("/api/categories/{id}", categoryId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(categoryResponse.getId()));
        
        verify(categoryService, times(1)).update(categoryId, request);
    }
	 

}
