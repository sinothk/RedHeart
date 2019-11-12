package com.sinothk.redheart.controller;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.redheart.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.domain.TopicEntity;
import com.sinothk.redheart.domain.TopicThemeEntity;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "话题相关")
@RestController
@RequestMapping("/topic")
public class TopicController {

    @Resource(name = "topicService")
    private TopicService topicService;

    @ApiOperation(value = "话题：新增话题", notes = "话题：新增话题")
    @PostMapping("/addTopic")
    @ResponseBody
    @TokenCheck
    public ResultData<Boolean> addTopic(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "话题实体信息", required = true) @RequestBody TopicEntity topicEntity) {
        if (StringUtil.isEmpty(topicEntity.getTopicContent())) {
            return ResultData.error("话题内容不能为空");
        }
        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }
        topicEntity.setAccount(Long.valueOf(account));
        return topicService.addTopic(topicEntity);
    }

    @ApiOperation(value = "话题：查询由用户发布的话题列表", notes = "话题：查询由用户发布的话题列表")
    @GetMapping("/getTopicFromUserPageList")
    @TokenCheck
    public ResultData<PageData<List<TopicAo>>> getTopicFromMePageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "account") @RequestParam("account") Long account,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {
//        String account = TokenUtil.getTokenValue(token, "account");
//        if (StringUtil.isEmpty(account)) {
//            return ResultData.error("Token解析失败");
//        }
        return topicService.getTopicFromUserPageList(account, pageNum, pageSize);
    }

    @ApiOperation(value = "话题：查询关注人发布的话题列表", notes = "话题：查询关注人发布的话题列表")
    @GetMapping("/getNewTopicPageList")
    @TokenCheck
    public ResultData<PageData<List<TopicAo>>> getNewTopicPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        return topicService.getTopicFromILikeUserPageList(Long.valueOf(account), pageNum, pageSize);
    }

    @ApiOperation(value = "话题：查询最新发布的话题分页列表", notes = "话题：查询最新发布的话题分页列表")
    @GetMapping("/getHotTopicPageList")
    @TokenCheck
    public ResultData<PageData<List<TopicAo>>> getHotTopicPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        return topicService.getNewTopicPageList(pageNum, pageSize);
    }

    @ApiOperation(value = "话题：查询某种话题分页列表", notes = "话题：查询某种话题分页列表")
    @GetMapping("/getTopicByThemePageList")
    @TokenCheck
    public ResultData<PageData<List<TopicAo>>> getTopicByThemePageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "主题编码") @RequestParam("themeCode") String themeCode,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        return topicService.getTopicByThemePageList(themeCode, pageNum, pageSize);
    }

    @ApiOperation(value = "话题：查询当前用户喜欢的话题分页列表", notes = "查询当前用户喜欢的话题分页列表")
    @GetMapping("/getTopicWhereUserPraisePageList")
    @TokenCheck
    public ResultData<PageData<List<TopicAo>>> getTopicWhereUserPraisePageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "喜欢人账号") @RequestParam("targetAccount") String targetAccount,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        if (StringUtil.isEmpty(targetAccount)) {
            targetAccount = account;
        }

        return topicService.getTopicWhereUserPraisePageList(targetAccount, pageNum, pageSize);
    }

    @ApiOperation(value = "查询：模糊搜索话题(标题、内容)", notes = "模糊搜索话题(标题、内容)")
    @GetMapping("/findTopicByThemeOrContent")
    @TokenCheck
    public ResultData<List<TopicAo>> findTopicByThemeOrContent(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "关键字") @RequestParam("keyword") String keyword) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        return topicService.findTopicContent(account, keyword);
    }
}
