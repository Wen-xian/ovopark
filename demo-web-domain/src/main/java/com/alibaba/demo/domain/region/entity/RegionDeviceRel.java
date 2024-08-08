package com.alibaba.demo.domain.region.entity;

import com.alibaba.demo.domain.BaseEntity;

public class RegionDeviceRel extends BaseEntity {
    // 业务识别不需要更改
    private String id;
    // 业务识别不需要更改
    private String regionId;
    private String deviceId;

    public String getId() {
        return id;
    }

    public String getRegionId() {
        return regionId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    // 包级私有
    void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    // 包级私有
    RegionDeviceRel(String id, String regionId, String deviceId) {
        this.id = id;
        this.regionId = regionId;
        this.deviceId = deviceId;
    }
}

