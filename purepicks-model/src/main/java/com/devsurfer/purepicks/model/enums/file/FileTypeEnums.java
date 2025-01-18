package com.devsurfer.purepicks.model.enums.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/17 23:42
 * description TODO
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnums {

    DOCUMENT_FILE(1, "文档文件", Set.of(".docx", ".xlsx", ".pptx", ".pdf")),
    IMAGE_FILE(2, "图像文件", Set.of(".jpg", ".png", ".gif", ".bmp", ".tiff", ".webp")),
    AUDIO_FILE(3, "音频文件", Set.of(".mp3", ".wav", ".aac", ".flac", ".ogg", ".m4a")),
    VIDEO_FILE(4, "视频文件", Set.of(".mp4", ".avi", ".mkv", ".mov", ".flv", ".webm")),
    COMPRESSED_FILE(5, "压缩文件", Set.of(".mp4", ".avi", ".mkv", ".mov", ".flv", ".webm")),
    ;

    private final Integer type;

    private final String message;

    private final Set<String> fileExtensions;

    public static FileTypeEnums getFileTypeByExtension(String fileExtension) {
        for (FileTypeEnums value : FileTypeEnums.values()) {
            if (value.fileExtensions.contains(fileExtension)) {
                return value;
            }
        }
        return null;
    }

}
