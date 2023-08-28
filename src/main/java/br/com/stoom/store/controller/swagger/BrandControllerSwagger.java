package br.com.stoom.store.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.stoom.store.dto.request.BrandRequest;
import br.com.stoom.store.dto.response.BrandResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Controller de marca")
public interface BrandControllerSwagger {
	
	
	@Operation(summary = "Adiciona uma nova marca", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BrandResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<BrandResponse> create(BrandRequest request);

	@Operation(summary = "Lista todas as marcas", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BrandResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<List<BrandResponse>> findAll();

	
    @Operation(summary = "Busca marca por id", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BrandResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<BrandResponse> findBrandById(Long id);

   
    @Operation(summary = "Atualiza uma marca existente", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BrandResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<BrandResponse> update(Long id, BrandRequest request);

    @Operation(summary = "Deleta uma marca existente", method = "DELETE")
    @ApiResponses({
            @ApiResponse(responseCode = "204",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<Void> delete(Long id);
    

    @Operation(summary = "Atualiza status da marca existente", method = "PATCH")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BrandResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<BrandResponse> updateBrandStatus( Long brandId, boolean active);


}
