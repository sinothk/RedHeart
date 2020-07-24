package com.sinothk.redheart.controller;

import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.jpush.pushbyjpush.im.JMessageHelper;
import com.sinothk.base.authorization.TokenCheck;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(tags = "即时消息管理")
@RestController
@RequestMapping("/im")
public class IMController {

    @ApiOperation(value = "管理员：发送文本信息", notes = "发送文本信息")
    @GetMapping("/admin/sendText")
    @TokenCheck
    public ResultData<String> sendAdminText(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token,
            @ApiParam(value = "文本消息") @RequestParam("txtMsg") String txtMsg
    ) {

        String currAccount = TokenUtil.getTokenValue(token, "account");
        if (StringUtil.isEmpty(currAccount)) {
            return ResultData.error("Token解析失败");
        }

        try { // "欢迎加入异趣，很高心在这里遇见您！"
            new Thread(() -> {
                JMessageHelper jMessageHelper = new JMessageHelper();
                jMessageHelper.sendTxtMsg(currAccount, jMessageHelper.USER_ADMIN, txtMsg);
            }).start();
            return ResultData.success("发送成功");
        } catch (Exception e) {
            return ResultData.error("暂无数据");
        }
    }

}
