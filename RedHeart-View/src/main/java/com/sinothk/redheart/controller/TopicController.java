package com.sinothk.redheart.controller;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.redheart.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.domain.TopicEntity;
import com.sinothk.redheart.domain.TopicThemeEntity;
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

    @ApiOperation(value = "话题：新增话题实体信息", notes = "话题：新增话题实体信息")
    @PostMapping("/add")
    @ResponseBody
    @TokenCheck
    public ResultData<Boolean> add(
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

    @ApiOperation(value = "话题：查询由我发布的话题列表,", notes = "话题：查询由我发布的话题列表")
    @GetMapping("/getTopicFromMePageList")
    @TokenCheck
    public ResultData<PageData<List<TopicAo>>> getTopicFromMePageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        return topicService.getTopicFromMePageList(Long.valueOf(account), pageNum, pageSize);
    }
}
