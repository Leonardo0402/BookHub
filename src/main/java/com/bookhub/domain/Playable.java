package com.bookhub.domain;

/**
 * 可播放媒体（音频、视频）统一接口。该接口的存在展示了“多态”在策略模式中的运用，
 * UI 或下载模块只关心 Playable，而无需了解具体的媒体类型。
 */
public interface Playable {

    /**
     * @return 媒体文件的持续时长（秒）。如无法确定，返回负数表示未知。
     */
    long durationSeconds();

    /**
     * @return 播放提示信息，可用于 UI 状态栏展示。
     */
    String playDescription();
}
