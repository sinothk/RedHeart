package com.sinothk.redheart.controller;

import com.sinothk.base.entity.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "中国区域")
@RestController
@RequestMapping("/area")
public class AreaController {


    @ApiOperation(value = "新增：保存文件不需要封面到Linux", notes = "新增：保存文件不需要封面到Linux")
    @PostMapping("/init")
    public ResultData<String> init() {
        //http://localhost:8086/init/init

        return ResultData.success("1111111111");
    }


}
