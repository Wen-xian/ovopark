package com.alibaba.demo.domain.region.validator;

import com.alibaba.demo.domain.region.repository.IRegionRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RegionNameValidator {

    private final IRegionRepository IRegionRepository;

    public RegionNameValidator(IRegionRepository IRegionRepository) {
        this.IRegionRepository = IRegionRepository;
    }

    public void validateRepeatName(String id , String name) {
        if(Objects.isNull(IRegionRepository.selectOneExcludeIdByName(id, name))){
            throw new RuntimeException("RT004: 区域分组、区域分类名称不能重复");
        }
    }
}
