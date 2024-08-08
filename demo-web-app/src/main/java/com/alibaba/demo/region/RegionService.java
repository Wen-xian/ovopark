package com.alibaba.demo.region;

import com.alibaba.cola.demo.web.TransformBeanUtils;
import com.alibaba.demo.api.region.IRegionService;
import com.alibaba.demo.domain.region.handler.RegionBuilder;
import com.alibaba.demo.domain.region.handler.RegionFactory;
import com.alibaba.demo.domain.region.handler.RegionHandler;
import com.alibaba.demo.domain.region.entity.Region;
import com.alibaba.demo.domain.region.entity.RegionValidatorDTO;
import com.alibaba.demo.domain.region.repository.IRegionRepository;
import com.alibaba.demo.dto.region.RegionAddDTO;
import com.alibaba.demo.dto.region.RegionDTO;
import com.alibaba.demo.dto.region.RegionUpdateDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegionService implements IRegionService {

    private final RegionFactory regionFactory;
    private final IRegionRepository regionRepository;
    private final RegionHandler regionHandler;


    public RegionService(IRegionRepository regionRepository ,
                         RegionFactory regionFactory,
                            RegionHandler regionHandler) {
        this.regionRepository = regionRepository;
        this.regionFactory = regionFactory;
        this.regionHandler = regionHandler;
    }

    @Override
    public List<RegionDTO> regionList() {
        return TransformBeanUtils.transformList(regionRepository.regionList(), RegionDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addRegion(RegionAddDTO regionAddDTO) {
        RegionBuilder regionBuilder = regionFactory.create();

        Region region = regionBuilder
                .setName(regionAddDTO.getName())
                .setTypeId(regionAddDTO.getTypeId())
                .build();

        regionRepository.save(region);
        return region.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateRegion(RegionUpdateDTO regionUpdateDTO) {
        Region region = regionRepository.getById(regionUpdateDTO.getId());
        if(region == null){
            throw new RuntimeException("Region not found");
        }
        regionHandler.updateRegion(region, TransformBeanUtils.transform(regionUpdateDTO, RegionValidatorDTO.class));
        regionRepository.update(region);
        return region.getId();
    }
}
