package com.alibaba.demo.dto.region;

import lombok.Data;

@Data
public class RegionAddDTO {
    private String id;
    private String name;
    private String pid;
    private String regionCode;
    private String typeId;
    private Boolean isSystem;
    private String orgId;
    private String createId;
    private String createName;
    private String isDelete;
}
