package com.fc.test.mapper.auto;

import com.fc.test.model.auto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典类型表
 * 
 * @author 一休
 * @email 438081243@qq.com
 * @date 2019-09-05 12:34:25
 */
public interface TPBaffleDataDetailMapper {

    List<TPBaffleDataDetailInfo> selectByExample(TPBaffleDataDetailInfoExample example);

    TPBaffleDataDetailInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPBaffleDataDetailInfo record);

    int deleteByIds(@Param("lists") List<String> lists);

    int insertSelective(TPBaffleDataDetailInfo record);

    TPBaffleDataDetailInfo selectByInClass(TPBaffleDataDetailInfoByClassNameExample example);
}