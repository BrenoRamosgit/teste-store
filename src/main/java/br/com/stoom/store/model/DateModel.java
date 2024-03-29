package br.com.stoom.store.model;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class DateModel implements Serializable {


	private static final long serialVersionUID = -2788311229324342006L;

	@Column(nullable = false, name = "dt_created")
	public LocalDateTime dt_created;

	@Column(nullable = true, name = "dt_updated")
	public LocalDateTime dt_updated;

	public DateModel() {
	}

	public DateModel(LocalDateTime dt_created, LocalDateTime dt_updated) {
		this.dt_created = dt_created;
		this.dt_updated = dt_updated;
	}

	public DateModel(LocalDateTime dt_updated) {
		this.dt_updated = dt_updated;
	}

	public String formattedCreatedAt() {
		return getFormattedDate(dt_created, "yyyy-MM-dd HH:mm:ss");
	}

	public String formattedUpdatedAt() {
		return getFormattedDate(dt_updated, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String getFormattedDate(LocalDateTime localDateTime, String pattern) {
		if (isNull(localDateTime) || isBlank(pattern)) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return localDateTime.format(formatter);
	}
}