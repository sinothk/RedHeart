package com.sinothk.redheart.controller;

import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.redheart.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.ThemeOtherInfoAo;
import com.sinothk.redheart.domain.TopicThemeAo;
import com.sinothk.redheart.domain.TopicThemeEntity;
import com.sinothk.redheart.domain.TopicThemeUserEntity;
import com.sinothk.redheart.service.TopicThemeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Api(tags = "话题相关")
@RestController
@RequestMapping("/topic/theme")
public class TopicThemeController {

    @Resource(name = "topicThemeService")
    private TopicThemeService topicThemeService;

    @ApiOperation(value = "主题：新增主题实体信息", notes = "主题：新增主题实体信息")
    @PostMapping("/addTheme")
    @ResponseBody
    @TokenCheck
    public ResultData<Boolean> addTheme(
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

    @ApiOperation(value = "主题：查询所有主题列表", notes = "主题：查询所有主题列表")
    @GetMapping("/getAllTopicThemeList")
    @TokenCheck
    public ResultData<List<TopicThemeAo>> getAllTopicThemeList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token) {
        return topicThemeService.getAllTopicThemeList();
    }

    @ApiOperation(value = "主题: 关注或取消关注主题", notes = "主题: 关注或取消关注主题")
    @GetMapping("/like")
    @TokenCheck
    public ResultData<String> like(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "被喜欢主题编码", required = true) @RequestParam("themeCode") String themeCode) {

        if (StringUtil.isEmpty(themeCode)) {
            return ResultData.error("主题编码不能为空");
        }

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        TopicThemeUserEntity ttuEntity = new TopicThemeUserEntity();
        ttuEntity.setAccount(Long.valueOf(account));
        ttuEntity.setThemeCode(themeCode);
        ttuEntity.setCreateTime(new Date());
        return topicThemeService.likeTheme(ttuEntity);
    }

    @ApiOperation(value = "主题：查询用户关注的主题列表", notes = "主题：查询用户关注的主题列表")
    @GetMapping("/getMyTopicThemeList")
    @TokenCheck
    public ResultData<List<TopicThemeAo>> getMyTopicThemeList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }
        return topicThemeService.getMyTopicThemeList(Long.valueOf(account));
    }

    @ApiOperation(value = "主题：查询主题下关注人数、话题数及当前人和当前主题的关系", notes = "主题：查询主题下关注人数、话题数及当前人和当前主题的关系")
    @GetMapping("/getUserWhitThemeRelation")
    @TokenCheck
    public ResultData<ThemeOtherInfoAo> getUserWhitThemeRelation(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "被喜欢主题编码", required = true) @RequestParam("themeCode") String themeCode) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        if (StringUtil.isEmpty(themeCode)) {
            return ResultData.error("主题编码不能为空");
        }

        return topicThemeService.getUserWhitThemeRelation(Long.valueOf(account), themeCode);
    }
}
