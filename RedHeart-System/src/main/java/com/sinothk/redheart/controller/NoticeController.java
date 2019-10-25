package com.sinothk.redheart.controller;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.redheart.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.NoticeEntity;
import com.sinothk.redheart.domain.NoticeReaderVo;
import com.sinothk.redheart.domain.NoticeVo;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Api(tags = "通知管理")
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource(name = "noticeService")
    private NoticeService noticeService;

    @ApiOperation(value = "通知：发布通知", notes = "通知：发布通知")
    @PostMapping("/add")
    @ResponseBody
    @TokenCheck
    public ResultData<Boolean> add(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "通知实体信息", required = true) @RequestBody NoticeEntity noticeEntity) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }
        if (StringUtil.isEmpty(noticeEntity.getTitle())) {
            return ResultData.error("通知标题不能为空");
        }
        if (StringUtil.isEmpty(noticeEntity.getContent())) {
            return ResultData.error("通知内容不能为空");
        }

        noticeEntity.setCreateAccount(Long.valueOf(account));
        noticeEntity.setCreateTime(new Date());

        return noticeService.add(noticeEntity);
    }

    @ApiOperation(value = "通知：获取所有分页通知", notes = "通知：获取所有分页通知")
    @GetMapping("/getAllNoticeList")
    @TokenCheck
    public ResultData<PageData<List<NoticeVo>>> getAllNoticeList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页大小") @RequestParam("pageSize") int pageSize) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }

        return noticeService.getAllNoticeList(Long.valueOf(account), pageNum, pageSize);
    }

    @ApiOperation(value = "通知：获取所有阅读人员", notes = "通知：获取所有阅读人员")
    @GetMapping("/getNoticeReaderList")
    @TokenCheck
    public ResultData<PageData<List<NoticeReaderVo>>> getNoticeReaderList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "通知Id") @RequestParam("noticeId") String noticeId,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        if (StringUtil.isEmpty(noticeId)) {
            return ResultData.error("通知Id不能为空");
        }

        return noticeService.getNoticeReaderList(Long.valueOf(noticeId), pageNum, pageSize);
    }

    @ApiOperation(value = "阅读：新增阅读信息", notes = "阅读：新增阅读信息")
    @PostMapping("/addRead")
    @ResponseBody
    @TokenCheck
    public ResultData<Boolean> addRead(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "通知Id", required = true) @RequestParam("noticeId") String noticeId) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }
        if (StringUtil.isEmpty(noticeId)) {
            return ResultData.error("通知ID不能为空");
        }

        return noticeService.addRead(Long.valueOf(noticeId), Long.valueOf(account));
    }
}
