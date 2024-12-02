package com.hk.sampleproject.context;

public class RequestContextHolder {
    private static final ThreadLocal<CommandContext> contextHolder = new ThreadLocal<>();

    public static void setContext(CommandContext context) {
        contextHolder.set(context);
    }

    public static CommandContext getContext() {
        CommandContext ctx = contextHolder.get();
        if (ctx == null) {
            throw new IllegalStateException("CommandContext not initialized");
        }
        return ctx;
    }

    public static void clearContext() {
        contextHolder.remove();
    }
}
