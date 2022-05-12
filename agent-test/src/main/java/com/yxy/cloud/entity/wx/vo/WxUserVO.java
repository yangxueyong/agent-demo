package com.yxy.cloud.entity.wx.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxUserVO implements Serializable {
    /**
     */
    private String phoneNum;
    /**
     */
    private String userId;

    private String userName;

    private String wxNickName;

    private String wxHeaderImg;

    private String wxOpenId;

}
