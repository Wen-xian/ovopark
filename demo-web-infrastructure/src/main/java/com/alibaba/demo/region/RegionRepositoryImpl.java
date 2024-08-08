package com.alibaba.demo.region;

import com.alibaba.cola.demo.web.TransformBeanUtils;
import com.alibaba.demo.domain.ChangingStatus;
import com.alibaba.demo.domain.region.entity.Region;
import com.alibaba.demo.domain.region.repository.IRegionRepository;
import com.alibaba.demo.region.bean.RegionDO;
import com.alibaba.demo.region.bean.RegionDeviceRelDO;
import com.alibaba.demo.region.mapper.RegionDeviceRelMapper;
import com.alibaba.demo.region.mapper.RegionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RegionRepositoryImpl implements IRegionRepository {

    private final RegionMapper regionMapper;
    private final RegionDeviceRelMapper regionDeviceRelMapper;

    public RegionRepositoryImpl(RegionMapper regionMapper
            , RegionDeviceRelMapper regionDeviceRelMapper) {
        this.regionMapper = regionMapper;
        this.regionDeviceRelMapper = regionDeviceRelMapper;
    }

    @Override
    public Region selectOneExcludeIdByName(String id, String name) {
        RegionDO regionDO = regionMapper.selectOneExcludeIdByName(id, name);
        return TransformBeanUtils.transform(regionDO, Region.class);
    }

    @Override
    public List<Region> regionList() {
        List<RegionDO> regionDOS = regionMapper.regionList();
        List<String> regionIdList = regionDOS.stream().map(RegionDO::getId).collect(Collectors.toList());
        List<RegionDeviceRelDO> regionDeviceRelDOS = regionDeviceRelMapper.selectByRegionIds(regionIdList);
        // TODO:组装Region和Device的关系
        return TransformBeanUtils.transformList(regionMapper.regionList(), Region.class);
    }

    @Override
    public void save(Region region) {
        RegionDO transform = TransformBeanUtils.transform(region, RegionDO.class);
        regionMapper.save(transform);
    }

    @Override
    public Region getById(String id) {
        List<RegionDeviceRelDO> regionDeviceRelDO =  regionDeviceRelMapper.selectByRegionId(id);
        Region region = TransformBeanUtils.transform(regionMapper.getById(id), Region.class);
        regionDeviceRelDO.forEach(relDO ->{
            region.addDevice(relDO.getDeviceId());
        });
        return region;
    }

    @Override
    public void update(Region region) {
        RegionDO transform = TransformBeanUtils.transform(region, RegionDO.class);
        regionMapper.update(transform);
        region.getRegionDeviceRelList().forEach(rel ->{
            if(rel.getChangingStatus() == ChangingStatus.NEW){
                RegionDeviceRelDO relDO = TransformBeanUtils.transform(rel, RegionDeviceRelDO.class);
                relDO.setRegionId(region.getId());
                regionDeviceRelMapper.save(relDO);
            }else if(rel.getChangingStatus() == ChangingStatus.DELETED){
                regionDeviceRelMapper.delete(rel.getId());
            }
        });
    }
}
