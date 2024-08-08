package com.alibaba.demo.domain.region.entity;


import com.alibaba.demo.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Region extends BaseEntity {
    // todo: 如果使用Mybatis框架：在聚合内部使用对象导航，跨聚合则使用ID导航
    //  （使用ID导航的时候，由于领域对象之间难以通过导航来协作，所以对象内部能实现的领域逻辑就很有限了，
    //  大量的逻辑就要在领域服务中实现。所以这种方式下，多数聚合都至少要搭配一个自己的领域服务。
    //  这样，编程风格就只能是偏过程式的了)
    @Getter
    private String id;
    @Setter
    @Getter
    private String name;
    @Getter
    private String pid;
    @Getter
    private String regionCode;
    // 聚合外用ID
    @Getter
    private String typeId;
    @Getter
    private Boolean isSystem;
    @Getter
    private String orgId;
    @Getter
    private String createId;
    @Getter
    private String createName;
    @Setter
    @Getter
    private String isDelete;
    // 聚合内用对象
    private List<RegionDeviceRel> regionDeviceRelList;

    public Region(String id, String name, String pid, String regionCode, String typeId, Boolean isSystem, String orgId, String createId, String createName, String isDelete) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.regionCode = regionCode;
        this.typeId = typeId;
        this.isSystem = isSystem;
        this.orgId = orgId;
        this.createId = createId;
        this.createName = createName;
        this.isDelete = isDelete;
    }

    public boolean canUpdate() {
        return !isSystem;
    }

    public List<RegionDeviceRel> getRegionDeviceRelList() {
        return Collections.unmodifiableList(regionDeviceRelList);
    }

    public void addDevice(String deviceId) {
        if(regionDeviceRelList == null) {
            regionDeviceRelList = Collections.singletonList(new RegionDeviceRel(null, id, deviceId));
        } else if(regionDeviceRelList.stream().noneMatch(rel -> rel.getDeviceId().equals(deviceId))) {
            RegionDeviceRel regionDeviceRel = new RegionDeviceRel(null, id, deviceId);
            regionDeviceRel.toNew();
            regionDeviceRelList.add(regionDeviceRel);
        }
    }

    public void removeDevice(String deviceId) {
        if(regionDeviceRelList == null) {
           return;
        }
        Optional<RegionDeviceRel> first = regionDeviceRelList.stream().filter(rel -> rel.getDeviceId().equals(deviceId)).findFirst();
        first.ifPresent(BaseEntity::toDelete);
    }

    public void removeRegion(){
        if(!CollectionUtils.isEmpty(regionDeviceRelList)){
            throw new RuntimeException("RT003: 区域下存在设备，不能删除");
        }
        isDelete = "1";
    }

    public boolean canBindDevice() {
        return !StringUtils.hasLength(pid);
    }
}
