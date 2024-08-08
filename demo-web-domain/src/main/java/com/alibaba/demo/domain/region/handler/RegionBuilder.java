package com.alibaba.demo.domain.region.handler;


import com.alibaba.demo.domain.region.entity.Region;
import com.alibaba.demo.domain.region.validator.RegionNameValidator;


public class RegionBuilder {

    private final RegionNameValidator regionNameValidator;

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

    public RegionBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public RegionBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RegionBuilder setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public RegionBuilder setRegionCode(String regionCode) {
        this.regionCode = regionCode;
        return this;
    }

    public RegionBuilder setTypeId(String typeId) {
        this.typeId = typeId;
        return this;
    }

    public RegionBuilder setSystem(Boolean system) {
        isSystem = system;
        return this;
    }

    public RegionBuilder setOrgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public RegionBuilder setCreateId(String createId) {
        this.createId = createId;
        return this;
    }

    public RegionBuilder setCreateName(String createName) {
        this.createName = createName;
        return this;
    }

    public RegionBuilder setIsDelete(String isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public RegionBuilder(RegionNameValidator regionNameValidator){
        this.regionNameValidator = regionNameValidator;
    }

    public Region build(){
        // TODO：并发情况都会发生名称重复，需要加锁， 1.数据库加唯一索引 2.分布式锁
        // TODO: 责任链设计模式来对修改关闭
        validate();
        return new Region(id, name, pid, regionCode, typeId, isSystem, orgId, createId, createName, isDelete);
    }

    private void validate() {
        regionNameValidator.validateRepeatName(id, name);
    }
}
