package com.sinothk.redheart.controller;

import com.alibaba.fastjson.JSON;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.redheart.domain.AreaEntity;
import com.sinothk.redheart.domain.GaoDeAreaEntity;
import com.sinothk.redheart.service.AreaService;
import com.sinothk.redheart.utils.HttpClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "中国区域")
@RestController
@RequestMapping("/area")
public class AreaController {

    @Resource(name = "areaService")
    private AreaService areaService;

    @ApiOperation(value = "根据编码 adcode 获取其下一级区域列表", notes = "根据编码 adcode 获取其下一级区域列表")
    @GetMapping("/getAreaList")
    public ResultData<ArrayList<AreaEntity>> getAreaList(@ApiParam(value = "adCode", required = true) @RequestParam("adCode") String adCode) {

        if (StringUtil.isEmpty(adCode)) {
            return ResultData.error("参数不能为空");
        }

        ArrayList<AreaEntity> areaList = areaService.getAreaList(adCode);
        return ResultData.success(areaList);
    }


//    /**
//     * 使用高德地图数据
//     *
//     * @param unit
//     * @param subdistrict
//     * @return
//     */
//    @ApiOperation(value = "初始化：使用高德地图数据", notes = "初始化：使用高德地图数据")
//    @GetMapping("/create")
//    public ResultData<String> create(
//            @ApiParam(value = "行政单位", required = true) @RequestParam("unit") String unit,
//            @ApiParam(value = "深度", required = true) @RequestParam("subdistrict") String subdistrict) {
//        //http://localhost:8081/redheart/area/create
//
//        try {
//            String json = getHttpHelper(unit, subdistrict);
//
//            areaService.add(JSON.parseObject(json, GaoDeAreaEntity.class));
//
//            return ResultData.success("成功");
//
//        } catch (Exception e) {
//            return ResultData.error(e.getMessage());
//        }
//    }
//
//    private static String getHttpHelper(String unit, String subdistrict) {
//        try {
//            Map<String, String> map = new HashMap<>();
//            map.put("keywords", unit);
//            map.put("subdistrict", subdistrict);
//            map.put("key", "a9301b47a533356426792775a4843439");
//
//            String url = "https://restapi.amap.com/v3/config/district";
//            return new HttpClientUtil().doGet(url, map);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }

//    @ApiOperation(value = "初始化：使用高德地图数据", notes = "初始化：使用高德地图数据")
//    @GetMapping("/initDistrict")
//    public ResultData<String> initDistrict() {
//        //http://localhost:8081/redheart/area/initDistrict
//
//        try {
//
//            ArrayList<AreaEntity> list = areaService.findAllDistricts();
//
//            for (int i = 0; i < list.size(); i++) {
//
//                if (i > 2305) {
//                    String json = getHttpHelper(list.get(i).getName(), "1");
//                    areaService.initDistrict(JSON.parseObject(json, GaoDeAreaEntity.class));
//
//                    System.out.println("i ============================= " + i);
//                }
//            }
//
//            return ResultData.success("成功");
//
//        } catch (Exception e) {
//            return ResultData.error(e.getMessage());
//        }
//    }
}
