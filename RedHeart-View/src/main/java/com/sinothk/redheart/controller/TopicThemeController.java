package com.sinothk.redheart.controller;

import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.redheart.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.TopicThemeEntity;
import com.sinothk.redheart.service.TopicThemeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "话题相关")
@RestController
@RequestMapping("/topic/theme")
public class TopicThemeController {

    @Resource(name = "topicThemeService")
    private TopicThemeService topicThemeService;

    @ApiOperation(value = "主题新增：主题实体信息", notes = "主题新增：主题实体信息")
    @PostMapping("/add")
    @ResponseBody
    @TokenCheck
    public ResultData<Boolean> add(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "主题实体信息", required = true) @RequestBody TopicThemeEntity topicThemeEntity) {

        if (StringUtil.isEmpty(topicThemeEntity.getThemeCode())) {
            return ResultData.error("主题编码不能为空");
        }

        if (StringUtil.isEmpty(topicThemeEntity.getThemeTxt())) {
            return ResultData.error("主题内容不能为空");
        }

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        return topicThemeService.add(topicThemeEntity);
    }
}
