package com.bookhub.config;

/**
 * 存放应用环境的常量类。使用 {@code final} 阻止继承，符合“常量类必须 final”的最佳实践。
 */
public final class ApplicationProfiles {

    public static final String DEV = "dev";
    public static final String TEST = "test";
    public static final String PROD = "prod";

    private ApplicationProfiles() {
    }
}
