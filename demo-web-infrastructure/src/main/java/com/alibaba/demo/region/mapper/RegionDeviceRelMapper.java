package com.alibaba.demo.region.mapper;

import com.alibaba.demo.region.bean.RegionDeviceRelDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionDeviceRelMapper {

    List<RegionDeviceRelDO> selectByRegionId(String id);

    List<RegionDeviceRelDO> selectByRegionIds(List<String> regionIdList);

    void update(List<RegionDeviceRelDO> regionDeviceRelDO);

    void save(RegionDeviceRelDO relDO);

    void delete(String id);
}
