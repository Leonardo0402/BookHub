package com.bookhub.domain;

import java.util.Collection;

/**
 * 描述可被索引与搜索的领域对象。接口设计突出“面向接口编程”的思想，
 * 通过抽象出统一的查询关键字与标签集合，让不同的实体都能被通用搜索服务处理。
 */
public interface Searchable {

    /**
     * 提供用于全文检索或关键词匹配的搜索字段。
     * @return 例如图书的标题、作者与简介的组合文本。
     */
    String searchableText();

    /**
     * 返回与实体关联的标签集合，用于标签过滤、分组统计。
     * @return 标签集合，可为空但不应为 {@code null}
     */
    Collection<String> tags();
}
