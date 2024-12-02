package com.hk.sampleproject.context;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommandContext {
    private final String userId;
    private final String userIp;

    public static CommandContext of(String userId, String userIp) {
        return CommandContext.builder()
                .userId(userId)
                .userIp(userIp)
                .build();
    }
}

