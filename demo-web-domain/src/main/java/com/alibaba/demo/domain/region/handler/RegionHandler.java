package com.alibaba.demo.domain.region.handler;

import com.alibaba.demo.domain.region.entity.Region;
import com.alibaba.demo.domain.region.entity.RegionDeviceRel;
import com.alibaba.demo.domain.region.entity.RegionValidatorDTO;
import com.alibaba.demo.domain.region.validator.RegionNameValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Component
public class RegionHandler {

    private final RegionNameValidator regionNameValidator;

    public RegionHandler(RegionNameValidator regionNameValidator) {
        this.regionNameValidator = regionNameValidator;
    }


    public void updateRegion(Region region, RegionValidatorDTO regionValidatorDTO) {
        updateNameValidate(region, regionValidatorDTO.getName());
        updateDeviceRelValidate(region, regionValidatorDTO.getRegionDeviceRelList());
    }

    private void updateDeviceRelValidate(Region region, List<RegionDeviceRel> regionDeviceRelList) {
        if(CollectionUtils.isEmpty(regionDeviceRelList)) {
            return;
        }
        if(!region.canBindDevice()) {
            throw new RuntimeException("RT003: 系统区域不能绑定设备");
        }
        // TODO：比对关联关系是否有变更, 新增调用新增、移除调用移除
        region.addDevice("XX");
        region.removeDevice("XX");
    }

    private void canUpdate(Region region) {
        // 业务判断内聚，方便之后统一修改
        if(!region.canUpdate()) {
            throw new RuntimeException("RT002: 系统区域不能变更");
        }
    }

    private void updateNameValidate(Region region, String name) {
        if(null == name) {
           throw new RuntimeException("name is null");
        }
        if(!region.getName().equals(name)) {
            canUpdate(region);
            regionNameValidator.validateRepeatName(region.getId(), name);
            region.setName(name);
        }
    }
}
