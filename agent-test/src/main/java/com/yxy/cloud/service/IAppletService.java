package com.yxy.cloud.service;

import com.yxy.cloud.entity.req.Q;
import com.yxy.cloud.entity.res.R;
import com.yxy.cloud.entity.wx.io.CustomerIO;
import com.yxy.cloud.entity.wx.io.WxV2IO;
import com.yxy.cloud.entity.wx.vo.CustomerVO;
import com.yxy.cloud.entity.wx.vo.WxUserVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface IAppletService {
    public R<WxUserVO> getUser2(Q<WxV2IO> q) ;

//    public int calc(int a,int b) {
//        return a + b;
//    }

    public List<CustomerVO> getCustomer(CustomerIO customerIO,int num) ;

//    public double calc2(int i, int i1) {
//        return i + i1 + 0.12f;
//    }
//
//    public double[] calc3(int i, int i1) {
//        double[] doubles = new double[2];
//        doubles[0] = i;
//        doubles[1] = i1;
//        return doubles;
//    }

//    public void testVoid(){
//        System.out.println("空的-->");
//    }
}
