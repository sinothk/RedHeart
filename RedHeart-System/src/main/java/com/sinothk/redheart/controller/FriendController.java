package com.sinothk.redheart.controller;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.redheart.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.FriendEntity;
import com.sinothk.redheart.domain.FriendRelationshipEntity;
import com.sinothk.redheart.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "好友管理")
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Resource(name = "friendService")
    private FriendService friendService;

    @ApiOperation(value = "获取好友信息", notes = "获取好友信息")
    @GetMapping("/getLikeUserList")
    @TokenCheck
    public ResultData<PageData<List<FriendEntity>>> getLikeUserList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String likingAccount = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(likingAccount)) {
            return ResultData.error("Token解析失败");
        }

        return friendService.getLikeUserList(Long.valueOf(likingAccount), pageNum, pageSize);
    }

    @ApiOperation(value = "获取粉丝信息", notes = "获取粉丝信息")
    @GetMapping("/getFensUserList")
    @TokenCheck
    public ResultData<PageData<List<FriendEntity>>> getFensUserList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String likingAccount = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(likingAccount)) {
            return ResultData.error("Token解析失败");
        }

        return friendService.getFensUserList(Long.valueOf(likingAccount), pageNum, pageSize);
    }

    @ApiOperation(value = "新增关系信息", notes = "新增关系信息")
    @GetMapping("/add")
    @TokenCheck
    public ResultData<String> add(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "被喜欢用户账号", required = true) @RequestParam("likedAccount") String likedAccount) {

        if (StringUtil.isEmpty(likedAccount)) {
            return ResultData.error("被喜欢用户账号不能为空");
        }

        String likingAccount = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(likingAccount)) {
            return ResultData.error("Token解析失败");
        }

        if (likingAccount.equals(likedAccount)) {
            return ResultData.error("不能关注自己");
        }

        FriendRelationshipEntity frEntity = new FriendRelationshipEntity();
        frEntity.setLikingAccount(Long.valueOf(likingAccount));
        frEntity.setLikedAccount(Long.valueOf(likedAccount));
        frEntity.setLikeTime(new Date());

        return friendService.addFriend(frEntity);
    }
}
