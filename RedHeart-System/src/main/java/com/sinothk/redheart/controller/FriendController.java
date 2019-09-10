package com.sinothk.redheart.controller;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.FriendEntity;
import com.sinothk.redheart.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@Api(tags = "好友管理")
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Resource(name = "friendService")
    private FriendService friendService;

    @ApiOperation(value = "获取好友信息", notes = "获取好友信息")
    @GetMapping("/getFriendsList")
    public ResultData<PageData<ArrayList<FriendEntity>>> getFriendsList(@ApiParam(value = "username") @RequestParam("username") String username,
                                                                        @ApiParam(value = "pageNum") @RequestParam("pageNum") int pageNum) {
        // http://192.168.124.3:11000/friend/getFriendsList

        return friendService.getFriendsList(username, pageNum);
    }
}
