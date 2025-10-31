package com.bookhub.config;

import com.bookhub.service.download.DownloadService;
import com.bookhub.service.search.SearchService;
import com.bookhub.util.logging.LoggerFactory;
import org.slf4j.Logger;

/**
 * 简易配置类，用于集中创建服务实例。相比直接在 main 方法中 new 对象，
 * 这种方式更接近依赖注入框架（如 Spring）的思路，方便后续替换实现。
 */
public class AppConfig {

    private final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    public SearchService searchService() {
        return new SearchService(logger);
    }

    public DownloadService downloadService() {
        return new DownloadService(logger);
    }
}
