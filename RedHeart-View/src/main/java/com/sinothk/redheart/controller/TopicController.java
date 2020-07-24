package com.sinothk.redheart.controller;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.redheart.controller.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.domain.TopicEntity;
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

        if (StringUtil.isEmpty(topicEntity.getTopicContent()) && StringUtil.isEmpty(topicEntity.getTopicImg())) {
            return ResultData.error("内容和文件不能全为空");
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
    public ResultData<PageData<TopicAo>> getTopicFromUserPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "account") @RequestParam("account") Long account,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String loginAccount = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(loginAccount)) {
            return ResultData.error("Token解析失败");
        }

        boolean isLoginUser = Long.valueOf(loginAccount).equals(account);

        String sex = TokenUtil.getTokenValue(token, "sex");
        if (sex == null) {
            sex = "-1";
        }

        return topicService.getTopicFromUserPageList(account, isLoginUser, Integer.valueOf(sex), pageNum, pageSize);
    }

    @ApiOperation(value = "话题：查询热门话题分页列表", notes = "查询热门话题分页列表")
    @GetMapping("/getHotTopicPageList")
    @TokenCheck
    public ResultData<PageData<TopicAo>> getHotTopicPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String loginAccount = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(loginAccount)) {
            return ResultData.error("Token解析失败");
        }

        String sex = TokenUtil.getTokenValue(token, "sex");
        if (sex == null) {
            sex = "-1";
        }

        return topicService.getHotTopicPageList(Integer.valueOf(sex), pageNum, pageSize);
    }

    @ApiOperation(value = "话题：查询关注人发布的话题列表", notes = "话题：查询关注人发布的话题列表")
    @GetMapping("/getTopicFromILikeUserPageList")
    @TokenCheck
    public ResultData<PageData<TopicAo>> getTopicFromILikeUserPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }
//
//        String sex = TokenUtil.getTokenValue(token, "sex");
//        if (sex == null) {
//            sex = "-1";
//        }

        return topicService.getTopicFromILikeUserPageList(Long.valueOf(account), pageNum, pageSize);
    }

    @ApiOperation(value = "话题：查询最新发布的话题分页列表", notes = "话题：查询最新发布的话题分页列表")
    @GetMapping("/getTopicPageListByType")
    @TokenCheck
    public ResultData<PageData<TopicAo>> getTopicPageListByType(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询类型") @RequestParam("type") int type,
            @ApiParam(value = "关键字") @RequestParam("type") String keyword,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        String sex = TokenUtil.getTokenValue(token, "sex");
        if (sex == null) {
            sex = "-1";
        }

        return topicService.getNewTopicPageList(Integer.valueOf(sex), pageNum, pageSize);
    }

    @ApiOperation(value = "话题：查询最新发布的话题分页列表", notes = "话题：查询最新发布的话题分页列表")
    @GetMapping("/getNewTopicPageList")
    @TokenCheck
    public ResultData<PageData<TopicAo>> getNewTopicPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        String sex = TokenUtil.getTokenValue(token, "sex");
        if (sex == null) {
            sex = "-1";
        }

        return topicService.getNewTopicPageList(Integer.valueOf(sex), pageNum, pageSize);
    }

    @ApiOperation(value = "话题：查询话题分页列表", notes = "查询话题分页列表")
    @GetMapping("/getTopicByThemePageList")
    @TokenCheck
    public ResultData<PageData<TopicAo>> getTopicByThemePageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "主题编码") @RequestParam("themeCode") String themeCode,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        return topicService.getTopicByThemePageList(account, themeCode, pageNum, pageSize);
    }

    @ApiOperation(value = "话题：查询当前用户喜欢的话题分页列表", notes = "查询当前用户喜欢的话题分页列表")
    @GetMapping("/getTopicWhereUserPraisePageList")
    @TokenCheck
    public ResultData<PageData<TopicAo>> getTopicWhereUserPraisePageList(
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
