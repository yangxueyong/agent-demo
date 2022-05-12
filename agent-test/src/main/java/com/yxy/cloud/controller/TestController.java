package com.yxy.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.yxy.cloud.entity.req.Q;
import com.yxy.cloud.entity.res.R;
import com.yxy.cloud.entity.wx.io.CustomerIO;
import com.yxy.cloud.entity.wx.io.WxV2IO;
import com.yxy.cloud.entity.wx.vo.CustomerVO;
import com.yxy.cloud.entity.wx.vo.WxUserVO;
import com.yxy.cloud.service.AppletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AppletService appletService;

    @PostMapping("/v2/getUser2")
    public R getUser2(@RequestBody Q<WxV2IO> q) {
        R<WxUserVO> user2 = appletService.getUser2(q);
        return user2;
    }

    @PostMapping("/v2/calc")
    public R calc() {
        int c = appletService.calc(1,2);
        return R.data(c);
    }

    @PostMapping("/v2/calc2")
    public R calc2() {
        double c = appletService.calc2(1,2);
        return R.data(c);
    }

    @PostMapping("/v2/calc3")
    public R calc3() {
        double[] c = appletService.calc3(1,2);
        return R.data(c);
    }

    @PostMapping("/v2/getCustomer/{num}")
    public List<CustomerVO> getCustomer(@RequestBody Q<CustomerIO> q,@PathVariable("num") int num) {
        List<CustomerVO> datas = appletService.getCustomer(q.getBody(),num);
        return datas;
    }

    @PostMapping("/v2/testVoid")
    public R testVoid() {
        appletService.testVoid();
        return R.data(true);
    }
}
