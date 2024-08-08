package com.alibaba.demo.api.region;

import com.alibaba.demo.dto.region.RegionAddDTO;
import com.alibaba.demo.dto.region.RegionDTO;
import com.alibaba.demo.dto.region.RegionUpdateDTO;

import java.util.List;

public interface IRegionService {

    List<RegionDTO> regionList();

    String updateRegion(RegionUpdateDTO regionUpdateDTO);

    String addRegion(RegionAddDTO regionAddDTO);
}
