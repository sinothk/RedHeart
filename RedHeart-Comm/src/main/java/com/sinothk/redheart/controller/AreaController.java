package com.sinothk.redheart.controller;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.protocol.x.Notice;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.GaoDeAreaEntity;
import com.sinothk.redheart.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "中国区域")
@RestController
@RequestMapping("/area")
public class AreaController {


    @Resource(name = "areaService")
    private AreaService areaService;

    @ApiOperation(value = "初始化", notes = "初始化")
    @GetMapping("/create")
    public ResultData<GaoDeAreaEntity> create(@ApiParam(value = "区域", required = true) @RequestParam("unit") String unit) {
        //http://192.168.2.135:8080/redheart/area/create

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> map = new HashMap();
        map.put("keywords", unit);
        map.put("subdistrict", "5");
        map.put("key", "a9301b47a533356426792775a4843439");

        GaoDeAreaEntity areaEntity = restTemplate.getForObject("https://restapi.amap.com/v3/config/district"
                , GaoDeAreaEntity.class, map);

        areaService.add(areaEntity);

        return ResultData.success(areaEntity);
    }


}
