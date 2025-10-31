package com.bookhub.domain;

import java.nio.file.Path;

/**
 * 标记领域对象支持导出功能。导出器可以通过该接口抽象地访问文件或结构化数据。
 */
public interface Exportable {

    /**
     * 将当前对象导出为 JSON 字符串，方便 API 或网络传输。
     */
    String toJson();

    /**
     * 指定导出时使用的默认文件名。
     */
    String defaultExportFilename();

    /**
     * @return 如果对象已经持有本地文件，则返回其路径；否则返回 {@code null}
     */
    Path associatedFile();
}
