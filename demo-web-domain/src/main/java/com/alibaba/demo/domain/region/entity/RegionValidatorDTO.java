package com.alibaba.demo.domain.region.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@Data
public class RegionValidatorDTO {
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String pid;
    @NotBlank
    private String regionCode;
    @NotBlank
    private String typeId;
    private Boolean isSystem;
    private String orgId;
    private String createId;
    private String createName;
    private String isDelete;
    private List<RegionDeviceRel> regionDeviceRelList;
}
