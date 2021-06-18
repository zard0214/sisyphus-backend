package com.sisyphus.demo.activiti.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhecheng.zhao
 * @date Created in 13/06/2021 18:57
 */
@Setter
@Getter
public class ActReProcDef implements Serializable {

    private String id;

    private Integer rev;

    private String category;

    private String name;

    private String key;

    private Integer version;

    private String deploymentId;

    private String resourceName;

    private String dgrmResourceName;

    private String description;

    private Byte hasStartFormKey;

    private Byte hasGraphicalNotation;

    private Integer suspensionState;

    private String tenantId;

    private String engineVersion;

    private Date deployTime;

    private static final long serialVersionUID = 1L;

}
