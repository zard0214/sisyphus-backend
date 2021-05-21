create table sisyphus_uac.s_uac_user_post
(
    id               bigint auto_increment comment 'ID'
        primary key,
    user_id          bigint      default 0                 null comment '用户ID',
    post_id          bigint      default 0                 null comment '岗位ID',
    creator          varchar(20) default ''                null comment '创建人',
    creator_id       bigint                                null comment '创建人ID',
    gmt_create       datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator    varchar(20) default ''                null comment '最近操作人',
    last_operator_id bigint                                null comment '最后操作人ID',
    gmt_modified     datetime    default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted       int         default 0                 null comment '是否删除'
)
    comment '用户岗位表' charset = utf8;

