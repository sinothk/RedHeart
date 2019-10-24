package com.sinothk.redheart.controller;

import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.redheart.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.TopicEntity;
import com.sinothk.redheart.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "话题")
@RestController
@RequestMapping("/topic")
public class TopicController {

    @Resource(name = "topicService")
    private TopicService topicService;

    @ApiOperation(value = "新增：话题实体信息", notes = "新增：话题实体信息")
    @PostMapping("/add")
    @ResponseBody
    @TokenCheck
    public ResultData<Boolean> add(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "话题实体信息", required = true) @RequestBody TopicEntity topicEntity) {


//        if (StringUtil.isEmpty(email)) {
//            // 验证邮箱
//            return ResultData.error("邮箱不能为空");
//        }
//        if (StringUtil.isEmpty(password)) {
//            // 验证密码
//            return ResultData.error("密码不能为空");
//        }

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

//        TopicVo topicVo = new TopicVo();
        topicEntity.setAccount(Long.valueOf(account));
//        user.setUserPwd(password);
        return topicService.addTopic(topicEntity);
    }

}
