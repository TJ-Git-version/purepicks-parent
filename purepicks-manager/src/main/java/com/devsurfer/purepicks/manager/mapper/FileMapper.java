package com.devsurfer.purepicks.manager.mapper;

import com.devsurfer.purepicks.model.dto.file.FileInfoQueryDto;
import com.devsurfer.purepicks.model.entity.file.FileInfo;
import com.devsurfer.purepicks.model.vo.file.FileInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/15 23:25
 * description TODO
 */
public interface FileMapper {

    void insertFile(FileInfo fileInfo);

    FileInfo selectByFileId(Long fileId);

    List<FileInfoVo> findFileList(FileInfoQueryDto fileInfoQueryDto);

    void updateFileStatus(@Param("status") int status,@Param("fileId") Long fileId);
}
