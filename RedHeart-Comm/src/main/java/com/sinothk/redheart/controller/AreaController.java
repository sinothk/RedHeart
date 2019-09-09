package com.sinothk.redheart.controller;

import com.alibaba.fastjson.JSON;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.GaoDeAreaEntity;
import com.sinothk.redheart.service.AreaService;
import com.sinothk.redheart.utils.HttpClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "中国区域")
@RestController
@RequestMapping("/area")
public class AreaController {


    @Resource(name = "areaService")
    private AreaService areaService;

    /**
     * 使用高德地图数据
     *
     * @param unit
     * @param subdistrict
     * @return
     */
    @ApiOperation(value = "初始化：使用高德地图数据", notes = "初始化：使用高德地图数据")
    @GetMapping("/create")
    public ResultData<String> create(
            @ApiParam(value = "行政单位", required = true) @RequestParam("unit") String unit,
            @ApiParam(value = "深度", required = true) @RequestParam("subdistrict") String subdistrict) {
        //http://localhost:8081/redheart/area/create

        try {
            String json = getHttpHelper(unit, subdistrict);

            areaService.add(JSON.parseObject(json, GaoDeAreaEntity.class));

            return ResultData.success("成功");

        } catch (Exception e) {
            return ResultData.error(e.getMessage());
        }
    }

    private static String getHttpHelper(String unit, String subdistrict) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("keywords", unit);
            map.put("subdistrict", subdistrict);
            map.put("key", "a9301b47a533356426792775a4843439");

            String url = "https://restapi.amap.com/v3/config/district";
            return new HttpClientUtil().doGet(url, map);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
