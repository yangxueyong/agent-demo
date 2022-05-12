package com.yxy.cloud.service;

import com.yxy.cloud.entity.req.Q;
import com.yxy.cloud.entity.res.R;
import com.yxy.cloud.entity.wx.io.CustomerIO;
import com.yxy.cloud.entity.wx.io.WxV2IO;
import com.yxy.cloud.entity.wx.vo.CustomerVO;
import com.yxy.cloud.entity.wx.vo.WxUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppletService implements IAppletService {
    public R<WxUserVO> getUser2(Q<WxV2IO> q) {
        WxV2IO io = q.getBody();
        String phoneCode = io.getPhoneCode();
        if(!"123".equals(phoneCode)){
            return R.fail("非法的phoneCode");
        }
        WxUserVO wxUserVO = new WxUserVO();
        wxUserVO.setUserId("xx1");
        wxUserVO.setUserName("阿龙");
        wxUserVO.setPhoneNum("000");
        wxUserVO.setWxHeaderImg("他的头像");
        wxUserVO.setWxNickName("我在河之彼岸,守望曾经归来,归来无望");
        wxUserVO.setWxOpenId("uuuxx00ooll111");
        return R.data(wxUserVO);
    }

    public int calc(int a,int b) {
        return a + b;
    }

    public List<CustomerVO> getCustomer(CustomerIO customerIO,int num) {
        List<CustomerVO> datas = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            CustomerVO customerVO = new CustomerVO();
            customerVO.setName("黑袍"+ customerIO.getManagerId() + i);
            customerVO.setRemark("那条老狗缓慢地远去，逐渐消失在落日里" + i);
            datas.add(customerVO);
        }
        return datas;
    }

    public double calc2(int i, int i1) {
        return i + i1 + 0.12f;
    }

    public double[] calc3(int i, int i1) {
        double[] doubles = new double[2];
        doubles[0] = i;
        doubles[1] = i1;
        return doubles;
    }

    public void testVoid(){
        System.out.println("空的-->");
    }
}
