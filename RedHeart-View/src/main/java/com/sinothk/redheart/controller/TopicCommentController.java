package com.sinothk.redheart.controller;

import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.redheart.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.TopicCommentEntity;
import com.sinothk.redheart.service.TopicCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Api(tags = "话题相关")
@RestController
@RequestMapping("/topic/comment")
public class TopicCommentController {

    @Resource(name = "topicCommentService")
    private TopicCommentService topicCommentService;

    @ApiOperation(value = "评论：新增信息", notes = "评论：新增信息")
    @PostMapping("/add")
    @ResponseBody
    @TokenCheck
    public ResultData<Boolean> add(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "话题Id", required = true) @RequestParam("topicId") String topicId,
            @ApiParam(value = "接收人账号", required = true) @RequestParam("receiveAccount") String receiveAccount,
            @ApiParam(value = "评论内容", required = true) @RequestParam("content") String content) {

        if (StringUtil.isEmpty(content)) {
            return ResultData.error("评论内容不能为空");
        }

        if (StringUtil.isEmpty(receiveAccount)) {
            return ResultData.error("被评论人账号不能为空");
        }

        if (StringUtil.isEmpty(topicId)) {
            return ResultData.error("话题ID不能为空");
        }

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        TopicCommentEntity tcEntity = new TopicCommentEntity();
        tcEntity.setSendAccount(Long.valueOf(account));
        tcEntity.setCreateTime(new Date());

        tcEntity.setTopicId(topicId);
        tcEntity.setReceiveAccount(Long.valueOf(receiveAccount));
        tcEntity.setComContent(content);

        return topicCommentService.add(tcEntity);
    }

    @ApiOperation(value = "评论：删除", notes = "评论：删除")
    @GetMapping("/del")
    @TokenCheck
    public ResultData<Boolean> del(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "评论Id", required = true) @RequestParam("comId") Long comId) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        return topicCommentService.del(Long.valueOf(account), comId);
    }
//
//    @ApiOperation(value = "主题: 关注或取消关注主题", notes = "主题: 关注或取消关注主题")
//    @GetMapping("/like")
//    @TokenCheck
//    public ResultData<String> like(
//            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
//            @ApiParam(value = "被喜欢主题编码", required = true) @RequestParam("themeCode") String themeCode) {
//
//        if (StringUtil.isEmpty(themeCode)) {
//            return ResultData.error("主题编码不能为空");
//        }
//
//        String account = TokenUtil.getTokenValue(token, "account");
//        if (StringUtil.isEmpty(account)) {
//            return ResultData.error("Token解析失败");
//        }
//
//        TopicThemeUserEntity ttuEntity = new TopicThemeUserEntity();
//        ttuEntity.setAccount(Long.valueOf(account));
//        ttuEntity.setThemeCode(themeCode);
//        ttuEntity.setCreateTime(new Date());
//        return topicThemeService.likeTheme(ttuEntity);
//    }
//
//    @ApiOperation(value = "主题：查询用户关注的主题列表", notes = "主题：查询用户关注的主题列表")
//    @GetMapping("/getMyTopicThemeList")
//    @TokenCheck
//    public ResultData<List<TopicThemeAo>> getMyTopicThemeList(
//            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token) {
//
//        String account = TokenUtil.getTokenValue(token, "account");
//        if (StringUtil.isEmpty(account)) {
//            return ResultData.error("Token解析失败");
//        }
//        return topicThemeService.getMyTopicThemeList(Long.valueOf(account));
//    }
}
