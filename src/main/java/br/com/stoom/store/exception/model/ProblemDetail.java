package br.com.stoom.store.exception.model;


import java.net.URI;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemDetail {


    @Nullable
    private String title;
    
    private int status;
    @Nullable
    private String detail;
    @Nullable
    private URI instance;
    @Nullable
    private Map<String, Object> properties;

    protected ProblemDetail(int rawStatusCode) {
        this.status = rawStatusCode;
    }

    public static ProblemDetail forStatus(HttpStatus status) {
        Assert.notNull(status, "HttpStatusCode is required");
        return forStatus(status.value());
    }

    public static ProblemDetail forStatus(int status) {
        return new ProblemDetail(status);
    }

    public static ProblemDetail forStatusAndDetail(HttpStatus status, String detail) {
        Assert.notNull(status, "HttpStatusCode is required");
        ProblemDetail problemDetail = forStatus(status.value());
        problemDetail.setDetail(detail);
        return problemDetail;
    }

	
}
