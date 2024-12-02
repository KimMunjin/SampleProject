package com.hk.sampleproject.dto.request;

import com.hk.sampleproject.dto.validation.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateBoardRequest {
    @NotNull(message = ValidationMessages.INVALID_BOARD_ID)
    private final Long boardId;
    @NotBlank(message = ValidationMessages.TITLE_REQUIRED)
    private final String title;
    @NotBlank(message = ValidationMessages.CONTENT_REQUIRED)
    private final String content;
}
