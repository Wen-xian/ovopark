package com.alibaba.demo.domain.region.handler;

import com.alibaba.demo.domain.region.validator.RegionNameValidator;
import org.springframework.stereotype.Component;

@Component
public class RegionFactory {

    private final RegionNameValidator regionNameValidator;

    // TODO:策略模式注入 private List<RegionValidator> regionValidators;

    public RegionFactory(RegionNameValidator regionNameValidator) {
        this.regionNameValidator = regionNameValidator;
    }

    public RegionBuilder create() {
        return new RegionBuilder(regionNameValidator);
    }
}
