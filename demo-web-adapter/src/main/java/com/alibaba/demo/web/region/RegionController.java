package com.alibaba.demo.web.region;

import com.alibaba.demo.api.region.IRegionService;
import com.alibaba.demo.dto.region.RegionAddDTO;
import com.alibaba.demo.dto.region.RegionDTO;
import com.alibaba.demo.dto.region.RegionUpdateDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionController {

    private final IRegionService iRegionService;

    public RegionController(IRegionService iRegionService) {
        this.iRegionService = iRegionService;
    }

    @GetMapping(value = "/regionList")
    public List<RegionDTO> regionList(){
        return iRegionService.regionList();
    }

    @PostMapping(value = "/addRegion")
    public String addRegion(@RequestBody RegionAddDTO regionAddDTO){
        return iRegionService.addRegion(regionAddDTO);
    }

    @PostMapping(value = "/updateRegion")
    public String updateRegion(@RequestBody RegionUpdateDTO regionUpdateDTO){
        return iRegionService.updateRegion(regionUpdateDTO);
    }
}
