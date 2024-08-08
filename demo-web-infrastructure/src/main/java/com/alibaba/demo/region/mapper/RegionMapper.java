package com.alibaba.demo.region.mapper;

import com.alibaba.demo.region.bean.RegionDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionMapper {

    List<RegionDO> regionList();

    RegionDO selectOneExcludeIdByName(String id, String name);

    void save(RegionDO transform);

    RegionDO getById(String id);

    void update(RegionDO transform);
}
