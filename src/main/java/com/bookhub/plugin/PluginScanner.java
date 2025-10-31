package com.bookhub.plugin;

import org.slf4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * 通过 ClassLoader 扫描类路径，演示反射与注解处理的基本流程。
 */
public class PluginScanner {

    private final Logger logger;

    public PluginScanner(Logger logger) {
        this.logger = logger;
    }

    public Set<Class<?>> scan(String packageName) {
        Set<Class<?>> result = new HashSet<>();
        String path = packageName.replace('.', '/');
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                logger.info("发现资源: {}", resource);
            }
        } catch (IOException e) {
            logger.error("扫描插件包失败", e);
        }
        // 这里保留扩展点：可结合第三方库实现真正的类扫描。
        return result;
    }
}
