drop table if exists `file_info`;
create table `file_info`
(
    `file_id`           bigint auto_increment comment '文件唯一标识符',
    `original_filename` varchar(128) not null comment '原始文件名',
    `final_filename`    varchar(128) not null comment '存储名称',
    `file_size`         bigint       not null comment '文件大小（字节）',
    `file_type`         int          not null comment '文件类型',
    `file_extension`    varchar(10)  not null comment '文件扩展名',
    `upload_time`       datetime default CURRENT_TIMESTAMP comment '上传时间',
    `upload_uid`        bigint       not null comment '上传者（用户id）',
    `file_url`          varchar(512) not null comment '文件访问url',
    `file_path`          varchar(128) not null comment '文件存储路径',
    `file_status`       tinyint  default 1 comment '文件状态:1:active 或 2:deleted',
    `checksum`          varchar(256) not null comment '文件哈希值（如 sha256）',
    primary key (`file_id`),
    index idx_upload_uid (`upload_uid`)
) comment '文件管理';
