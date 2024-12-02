package com.hk.sampleproject.dto.request;

import com.hk.sampleproject.dto.validation.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateBoardRequest {
    @NotBlank(message = ValidationMessages.TITLE_REQUIRED)
    private final String title;
    @NotBlank(message = ValidationMessages.CONTENT_REQUIRED)
    private final String content;
}
