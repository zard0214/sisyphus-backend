-- /*
-- Navicat MySQL Data Transfer
-- Source Server         : sisyphus
-- Source Server Version : 50719
-- Source Host           :
-- Source Database       : sisyphus_uac
-- Target Server Type    : MYSQL
-- Target Server Version : 50719
-- File Encoding         : 65001
-- Date:
-- */
create table persistent_logins
(
    username  varchar(64)                         not null,
    series    varchar(64)                         not null
        primary key,
    token     varchar(64)                         not null,
    last_used timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
);

create table s_uac_action
(
    id               bigint auto_increment comment 'ID'
        primary key,
    action_name      bigint       default 0                 null comment '权限ID',
    action_code      bigint       default 0                 null comment '权限ID',
    status           varchar(20)                            null comment '状态',
    menu_id          bigint                                 not null comment '菜单ID',
    remark           varchar(300) default ''                null comment '备注',
    creator          varchar(20)  default ''                null comment '创建人',
    creator_id       bigint                                 null comment '创建人ID',
    gmt_created      datetime     default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator    varchar(20)  default ''                null comment '最近操作人',
    last_operator_id bigint                                 null comment '最后操作人ID',
    gmt_modified     datetime     default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted       int          default 0                 null comment '是否删除',
    method           varchar(20)  default ''                null comment '权限类型'
)
    comment '角色部门表' charset = utf8;

create table s_uac_menu
(
    id               bigint auto_increment comment 'ID'
        primary key,
    version          int          default 0                 null comment '版本号',
    menu_name        varchar(100) default ''                null comment '菜单名称',
    menu_code        varchar(100) default ''                null comment '菜单代码',
    url              varchar(150) default ''                null comment '菜单URL',
    menu_type        varchar(20)  default ''                null comment '菜单类型（M目录 C菜单 F按钮）',
    visible          varchar(20)  default ''                null comment '菜单状态（0显示 1隐藏）',
    status           varchar(20)  default ''                null comment '状态',
    icon             varchar(100) default ''                null comment '图标',
    pid              bigint                                 null comment '父ID',
    level            int          default 1                 null comment '层级(最多三级1,2,3)',
    leaf             int          default 0                 null comment '是否叶子节点,1不是0是',
    number           int          default 0                 null comment '序号',
    application_id   bigint       default 1                 null comment '租户id',
    remark           varchar(300) default ''                null comment '描述',
    creator          varchar(20)  default ''                null comment '创建人',
    creator_id       bigint                                 null comment '创建人ID',
    gmt_created      datetime     default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator    varchar(20)  default ''                null comment '最近操作人',
    last_operator_id bigint                                 null comment '最后操作人ID',
    gmt_modified     datetime     default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted       int          default 0                 null comment '是否删除'
)
    comment '菜单表' charset = utf8;

create table s_uac_role
(
    id               bigint auto_increment comment 'ID'
        primary key,
    version          int          default 0                 null comment '版本号',
    role_name        varchar(100) default ''                null comment '角色名称',
    role_code        varchar(100) default ''                null comment '角色代码',
    data_scope       varchar(100) default '1'               null comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    status           varchar(20)  default ''                null comment '状态',
    remark           varchar(300) default ''                null comment '描述',
    creator          varchar(20)  default ''                null comment '创建人',
    creator_id       bigint                                 null comment '创建人ID',
    gmt_created      datetime     default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator    varchar(20)  default ''                null comment '最近操作人',
    last_operator_id bigint                                 null comment '最后操作人ID',
    gmt_modified     datetime     default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted       int          default 0                 null comment '是否删除',
    tenant_id        bigint       default 0                 null comment '租户id'
)
    comment '角色表' charset = utf8;

create table s_uac_role_action
(
    id               bigint auto_increment comment 'ID'
        primary key,
    role_id          bigint      default 0                 null comment '角色ID',
    action_id        bigint      default 0                 null comment '权限ID',
    creator          varchar(20) default ''                null comment '创建人',
    creator_id       bigint                                null comment '创建人ID',
    gmt_created      datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator    varchar(20) default ''                null comment '最近操作人',
    last_operator_id bigint                                null comment '最后操作人ID',
    gmt_modified     datetime    default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted       int         default 0                 null comment '是否删除'
)
    comment '角色权限表' charset = utf8;

create table s_uac_role_menu
(
    id               bigint auto_increment comment 'ID'
        primary key,
    role_id          bigint      default 0                 null comment '角色ID',
    menu_id          bigint      default 0                 null comment '菜单ID',
    creator          varchar(20) default ''                null comment '创建人',
    creator_id       bigint                                null comment '创建人ID',
    gmt_created      datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator    varchar(20) default ''                null comment '最近操作人',
    last_operator_id bigint                                null comment '最后操作人ID',
    gmt_modified     datetime    default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted       int         default 0                 null comment '是否删除'
)
    comment '角色菜单表' charset = utf8;

create table s_uac_user
(
    id                  bigint auto_increment comment 'ID'
        primary key,
    version             int          default 0                 null comment '版本号',
    gender              int          default 0                 null comment '性别',
    login_name          varchar(50)  default ''                null comment '登录名',
    login_pwd           varchar(300) default ''                null comment '登录密码',
    user_code           varchar(32)  default ''                null comment '工号',
    user_name           varchar(50)  default ''                null comment '姓名',
    phone               varchar(15)  default ''                null comment '手机号',
    email               varchar(50)  default ''                null comment '邮件地址',
    status              varchar(20)  default ''                null comment '状态',
    user_source         varchar(32)  default ''                null comment '用户来源',
    type                varchar(32)  default ''                null comment '类型',
    last_login_ip       varchar(20)  default ''                null comment '最后登录IP地址',
    last_login_location varchar(50)  default ''                null comment '最后登录位置',
    remark              varchar(300) default ''                null comment '描述',
    last_login_time     datetime                               null comment '最后登录时间',
    pwd_error_count     smallint     default 0                 null comment '连续输错密码次数（连续5次输错就冻结帐号）',
    pwd_error_time      datetime                               null comment '最后输错密码时间',
    creator             varchar(20)  default ''                null comment '创建人',
    creator_id          bigint                                 null comment '创建人ID',
    gmt_created         datetime     default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator       varchar(20)  default ''                null comment '最近操作人',
    last_operator_id    bigint                                 null comment '最后操作人ID',
    gmt_modified        datetime     default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted          int          default 0                 null comment '是否删除'
)
    comment '操作员表' charset = utf8;

create table s_uac_user_role
(
    id               bigint auto_increment comment 'ID'
        primary key,
    user_id          bigint      default 0                 null comment '用户ID',
    role_id          bigint      default 0                 null comment '角色ID',
    creator          varchar(20) default ''                null comment '创建人',
    creator_id       bigint                                null comment '创建人ID',
    gmt_created      datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator    varchar(20) default ''                null comment '最近操作人',
    last_operator_id bigint                                null comment '最后操作人ID',
    gmt_modified     datetime    default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted       int         default 0                 null comment '是否删除'
)
    comment '用户角色表' charset = utf8;

create table s_uac_user_token
(
    id                     bigint auto_increment comment 'ID'
        primary key,
    version                int                        default 0                 null comment '版本号',
    pid                    bigint                                               null comment '父ID',
    login_name             varchar(50) charset utf8   default ''                null comment '登录名',
    user_name              varchar(50) charset utf8   default ''                null comment '姓名',
    user_id                bigint                                               null comment '用户ID',
    os                     varchar(20) charset utf8   default ''                null comment '操作系统',
    browser                varchar(20) charset utf8   default ''                null comment '浏览器',
    login_ip               varchar(20) charset utf8   default ''                null comment '登陆人Ip地址',
    login_location         varchar(50) charset utf8   default ''                null comment '登录地址',
    login_time             datetime                   default CURRENT_TIMESTAMP null comment '登录时间',
    access_token           varchar(2000) charset utf8 default ''                null comment '访问token',
    refresh_token          varchar(2000) charset utf8 default ''                null comment '刷新token',
    token_type             varchar(20) charset utf8   default ''                null comment 'token类型',
    access_token_validity  int                                                  null comment '访问token的生效时间(秒)',
    refresh_token_validity int                                                  null comment '刷新token的生效时间(秒)',
    status                 int                        default 0                 null comment '0 在线 10已刷新 20 离线',
    tenant_id              bigint                                               null comment '租户id',
    group_id               bigint                                               null comment '组织ID',
    group_name             varchar(50) charset utf8   default ''                null comment '组织名称',
    creator                varchar(20)                default ''                null comment '创建人',
    creator_id             bigint                                               null comment '创建人ID',
    gmt_created            datetime                   default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator          varchar(20)                default ''                null comment '最近操作人',
    last_operator_id       bigint                                               null comment '最后操作人ID',
    gmt_modified           datetime                   default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted             int                        default 0                 null comment '是否删除'
)
    comment '用户Token表' charset = utf8mb4;

create table s_uac_application
(
    id               bigint auto_increment comment 'ID'
        primary key,
    application_name varchar(20) default ''                null,
    application_code varchar(20) default ''                null,
    namespace        varchar(20) default ''                null comment '命名空间',
    tenant_id        bigint                                null comment '公司id',
    status           varchar(20) default ''                null comment '状态',
    creator          varchar(20) default ''                null comment '创建人',
    creator_id       bigint                                null comment '创建人ID',
    gmt_created      datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator    varchar(20) default ''                null comment '最近操作人',
    last_operator_id bigint                                null comment '最后操作人ID',
    gmt_modified     datetime    default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted       int         default 0                 null comment '是否删除'
)
    comment '应用表' charset = utf8;

create table s_uac_group
(
    id               bigint auto_increment comment 'ID'
        primary key,
    pid              bigint                                 null comment '父部门id',
    group_name       varchar(100) default ''                null comment '部门名称',
    group_code       varchar(100) default ''                null comment '部门代码',
    contact_id       varchar(100) default ''                null comment '联络人',
    contact_name     varchar(100) default ''                null comment '联络人名称',
    application_id   varchar(100) default ''                null comment '公司id',
    status           varchar(20)  default ''                null comment '状态',
    phone            varchar(15)  default ''                null comment '手机号',
    remark           varchar(300) default ''                null comment '描述',
    creator          varchar(20)  default ''                null comment '创建人',
    creator_id       bigint                                 null comment '创建人ID',
    gmt_created      datetime     default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator    varchar(20)  default ''                null comment '最近操作人',
    last_operator_id bigint                                 null comment '最后操作人ID',
    gmt_modified     datetime     default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted       int          default 0                 null comment '是否删除'
)
    comment '部门表' charset = utf8;

create table s_uac_group_user
(
    id               bigint auto_increment comment 'ID'
        primary key,
    user_id          bigint      default 0                 null comment '用户ID',
    group_id         bigint      default 0                 null comment '部门ID',
    creator          varchar(20) default ''                null comment '创建人',
    creator_id       bigint                                null comment '创建人ID',
    gmt_created      datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator    varchar(20) default ''                null comment '最近操作人',
    last_operator_id bigint                                null comment '最后操作人ID',
    gmt_modified     datetime    default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted       int         default 0                 null comment '是否删除'
)
    comment '部门用户表' charset = utf8;

create table s_uac_log
(
    id               bigint auto_increment comment 'ID'
        primary key,
    group_id         bigint        default 0                 null comment '部门ID',
    group_name       bigint        default 0                 null comment '部门ID',
    log_type         varchar(10)   default ''                null comment '日志类型',
    log_name         varchar(50)   default ''                null comment '日志类型名称',
    permission_id    bigint                                  null comment '权限ID',
    permission_code  varchar(100)  default ''                null comment '权限编码',
    permission_name  varchar(255)  default ''                null comment '权限名称',
    os               varchar(20)   default ''                null comment '操作系统',
    browser          varchar(20)   default ''                null comment '浏览器类型',
    ip               varchar(50)   default ''                null comment 'IP地址',
    location         varchar(50)   default ''                null comment '登录位置',
    mac              varchar(20)   default ''                null comment '物理地址',
    description      varchar(1000) default ''                null comment '详细描述',
    request_data     varchar(4000) default ''                null comment '请求参数',
    request_url      varchar(2000) default ''                null comment '请求地址',
    response_data    varchar(4000) default ''                null comment '响应结果',
    class_name       varchar(100)  default ''                null comment '类名',
    method_name      varchar(100)  default ''                null comment '方法名',
    start_time       timestamp                               null comment '开始时间',
    end_time         timestamp                               null comment '结束时间',
    excute_time      int                                     null comment '耗时,秒',
    creator          varchar(20)   default ''                null comment '创建人',
    creator_id       bigint                                  null comment '创建人ID',
    gmt_created      datetime      default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator    varchar(20)   default ''                null comment '最近操作人',
    last_operator_id bigint                                  null comment '最后操作人ID',
    gmt_modified     datetime      default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted       int           default 0                 null comment '是否删除',
    tenant_id        bigint        default 0                 not null comment '租户id'
)
    comment '日志表' charset = utf8;

create table s_uac_position
(
    id               bigint auto_increment comment 'ID'
        primary key,
    version          int          default 0                 null comment '版本号',
    application_id   bigint       default 0                 null comment '租户id',
    position_name    varchar(100) default ''                null comment '岗位名称',
    position_code    varchar(100) default ''                null comment '岗位代码',
    status           varchar(20)  default ''                null comment '状态',
    remark           varchar(300) default ''                null comment '描述',
    creator          varchar(20)  default ''                null comment '创建人',
    creator_id       bigint                                 null comment '创建人ID',
    gmt_created      datetime     default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator    varchar(20)  default ''                null comment '最近操作人',
    last_operator_id bigint                                 null comment '最后操作人ID',
    gmt_modified     datetime     default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted       int          default 0                 null comment '是否删除'
)
    comment '岗位表' charset = utf8;

create table s_uac_role_group
(
    id               bigint auto_increment comment 'ID'
        primary key,
    role_id          bigint      default 0                 null comment '角色ID',
    group_id         bigint      default 0                 null comment '部门ID',
    creator          varchar(20) default ''                null comment '创建人',
    creator_id       bigint                                null comment '创建人ID',
    gmt_created      datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator    varchar(20) default ''                null comment '最近操作人',
    last_operator_id bigint                                null comment '最后操作人ID',
    gmt_modified     datetime    default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted       int         default 0                 null comment '是否删除'
)
    comment '角色部门表' charset = utf8;

create table s_uac_tenant
(
    id               bigint auto_increment comment 'ID'
        primary key,
    version          int          default 0                 null comment '版本号',
    tenant_name      varchar(100) default ''                null comment '公司名称',
    tenant_code      varchar(100) default ''                null comment '公司代码',
    contact_id       varchar(100) default ''                null comment '联络人',
    contact_name     varchar(100) default ''                null comment '联络人名称',
    region_code      varchar(100) default ''                null comment '行政区域编码',
    phone            varchar(15)  default ''                null comment '手机号',
    remark           varchar(300) default ''                null comment '描述',
    creator          varchar(20)  default ''                null comment '创建人',
    creator_id       bigint                                 null comment '创建人ID',
    gmt_created      datetime     default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator    varchar(20)  default ''                null comment '最近操作人',
    last_operator_id bigint                                 null comment '最后操作人ID',
    gmt_modified     datetime     default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted       int          default 0                 null comment '是否删除'
)
    comment '租户表' charset = utf8;

create table s_uac_user_position
(
    id               bigint auto_increment comment 'ID'
        primary key,
    user_id          bigint      default 0                 null comment '用户ID',
    position_id      bigint      default 0                 null comment '岗位ID',
    creator          varchar(20) default ''                null comment '创建人',
    creator_id       bigint                                null comment '创建人ID',
    gmt_created      datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    last_operator    varchar(20) default ''                null comment '最近操作人',
    last_operator_id bigint                                null comment '最后操作人ID',
    gmt_modified     datetime    default CURRENT_TIMESTAMP null comment '更新时间',
    is_deleted       int         default 0                 null comment '是否删除'
)
    comment '用户岗位表' charset = utf8;

