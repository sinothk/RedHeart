package com.sinothk.redheart.controller;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.redheart.comm.authorization.TokenCheck;
import com.sinothk.redheart.domain.VisitorRecordAO;
import com.sinothk.redheart.domain.VisitorRecordEntity;
import com.sinothk.redheart.service.VisitRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Api(tags = "访问记录")
@RestController
@RequestMapping("/visit")
public class VisitRecordController {

    @Resource(name = "visitRecordService")
    private VisitRecordService visitRecordService;

    @ApiOperation(value = "访问记录：新增", notes = "新增访问记录")
    @PostMapping("/add")
    @ResponseBody
    @TokenCheck
    public ResultData<Boolean> add(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "访问记录实体信息", required = true) @RequestBody VisitorRecordEntity visitorRecord) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }
        if (StringUtil.isEmpty(visitorRecord.getAccount())) {
            return ResultData.error("被访问者账号为空");
        }
        if (StringUtil.isEmpty(visitorRecord.getVisitor())) {
            return ResultData.error("访问者账号为空");
        }

        return visitRecordService.add(visitorRecord);
    }

    @ApiOperation(value = "访问记录：获取当前用户的所有访问者", notes = "获取当前用户的所有访问者")
    @GetMapping("/getVisitRecordPageList")
    @TokenCheck
    public ResultData<PageData<VisitorRecordAO>> getVisitRecordPageList(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页号大小") @RequestParam("pageSize") int pageSize) {

        String account = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(account)) {
            return ResultData.error("Token解析失败");
        }
        return visitRecordService.getVisitRecordList(account, pageNum, pageSize);
    }

//    @ApiOperation(value = "通知：获取所有分页通知", notes = "通知：获取所有分页通知")
//    @GetMapping("/getAllNoticeList")
//    @TokenCheck
//    public ResultData<PageData<NoticeAo>> getAllNoticeList(
//            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
//            @ApiParam(value = "查询页号") @RequestParam("pageNum") int pageNum,
//            @ApiParam(value = "页大小") @RequestParam("pageSize") int pageSize) {
//
//        String account = TokenUtil.getTokenValue(token, "account");
//        if (StringUtil.isEmpty(account)) {
//            return ResultData.error("Token解析失败");
//        }
//
//        return noticeService.getAllNoticeList(Long.valueOf(account), pageNum, pageSize);
//    }
//
//    @ApiOperation(value = "通知：新增阅读信息", notes = "阅读：新增阅读信息")
//    @PostMapping("/addNoticeReader")
//    @ResponseBody
//    @TokenCheck
//    public ResultData<Boolean> addNoticeReader(
//            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
//            @ApiParam(value = "通知Id", required = true) @RequestParam("noticeId") String noticeId) {
//
//        String account = TokenUtil.getTokenValue(token, "account");
//        if (StringUtil.isEmpty(account)) {
//            return ResultData.error("Token解析失败");
//        }
//        if (StringUtil.isEmpty(noticeId)) {
//            return ResultData.error("通知ID不能为空");
//        }
//
//        return noticeService.addRead(Long.valueOf(noticeId), Long.valueOf(account));
//    }
//

}
