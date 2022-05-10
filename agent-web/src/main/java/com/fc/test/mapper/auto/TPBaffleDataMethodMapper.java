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
public interface TPBaffleDataMethodMapper {

    List<TPBaffleDataMethodInfo> selectByExample(TPBaffleDataMethodInfoExample example);

    TPBaffleDataMethodInfo selectByInClass(TPBaffleDataDetailInfoByClassNameExample example);

    TPBaffleDataMethodInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPBaffleDataMethodInfo record);

    int insertSelective(TPBaffleDataMethodInfo record);
}