package com.app.academyregistration.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationError {
    private Long errorCode;

    private String errorMessage;
}
