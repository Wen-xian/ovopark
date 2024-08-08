package com.alibaba.demo.domain.region.repository;


import com.alibaba.demo.domain.region.entity.Region;

import java.util.List;

public interface IRegionRepository {

    Region selectOneExcludeIdByName(String id, String name);

    List<Region> regionList();

    void save(Region region);

    Region getById(String id);

    void update(Region region);
}
