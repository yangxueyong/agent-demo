package com.yxy.cloud.entity.wx.io;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class WxV2IO implements Serializable {
    /**
     * phoneCode
     */
    @NotNull(message="phoneCode必传")
    private String phoneCode;

    /**
     * openIdCode
     */
    @NotNull(message="openIdCode必传")
    private String openIdCode;

    /**
     * idCardSuffix
     */
    private String idCardSuffix;
}
