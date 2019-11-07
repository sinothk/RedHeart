package com.sinothk.redheart.controller;

import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.redheart.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.TopicCommentEntity;
import com.sinothk.redheart.domain.TopicPraiseEntity;
import com.sinothk.redheart.service.TopicCommentService;
import com.sinothk.redheart.service.TopicPraiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Api(tags = "话题相关")
@RestController
@RequestMapping("/topic/praise")
public class TopicPraiseController {

    @Resource(name = "topicPraiseService")
    private TopicPraiseService topicPraiseService;

    @ApiOperation(value = "点赞：新增点赞信息", notes = "新增点赞信息")
    @PostMapping("/addPraise")
    @ResponseBody
    @TokenCheck
    public ResultData<TopicPraiseEntity> add(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "话题Id", required = true) @RequestParam("topicId") String topicId) {

        if (StringUtil.isEmpty(topicId)) {
            return ResultData.error("话题ID不能为空");
        }

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        TopicPraiseEntity entity = new TopicPraiseEntity();
        entity.setAccount(Long.valueOf(account));
        entity.setTopicId(topicId);
        return topicPraiseService.add(entity);
    }
}
