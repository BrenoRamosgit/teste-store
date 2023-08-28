package br.com.stoom.store.exception.handler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Problem {

	private Integer status;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime timestamp;
	
	private String type;
	
	private String title;
	
	private String detail;
	
	private String userMessage;
	
	private List<Object> objectList;
	
	@Getter
	@Builder
	public static class Object {
		
		private String name;
		
		private String userMessage;
		
	}
	
}