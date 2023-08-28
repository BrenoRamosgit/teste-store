package br.com.stoom.store.controller.swagger;

import java.util.List;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.stoom.store.dto.request.ProductFilterRequest;
import br.com.stoom.store.dto.request.ProductRequest;
import br.com.stoom.store.dto.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Controller de produtos")
public interface ProductControllerSwagger {

	
    @Operation(summary = "Lista todos os produtos", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    @PageableAsQueryParam 
    ResponseEntity<Page<ProductResponse>> list(ProductFilterRequest request, @Parameter(hidden = true) Pageable pageable);


    @Operation(summary = "Busca produto por id", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<ProductResponse> findProductById(Long id);

    @Operation(summary = "Adiciona um novo produto", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<ProductResponse> create(ProductRequest request);

    @Operation(summary = "Atualiza um produto existente", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<ProductResponse> update(Long id, ProductRequest request);

    @Operation(summary = "Deleta um produto existente", method = "DELETE")
    @ApiResponses({
            @ApiResponse(responseCode = "204",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<Void> delete(Long id);

    @Operation(summary = "Atualiza status do produto existente", method = "PATCH")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<ProductResponse> updateProductStatus( Long productId, boolean active);

    
    @Operation(summary = "Lista todos os produtos de uma categoria existente", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<List<ProductResponse>> getProductsByCategory(Long categoryId);

    
    @Operation(summary = "Lista todos os produtos da marca", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<List<ProductResponse>> getProductsByBrand(Long categoryId);


    @Operation(summary = "Lista todos produtos", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<List<ProductResponse>> findAll();

    
    


}
