package com.yxy.agent.entity.wx.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVO implements Serializable {
    /**
     */
    private String name;
    /**
     */
    private Integer sex;

    private String remark;

}
