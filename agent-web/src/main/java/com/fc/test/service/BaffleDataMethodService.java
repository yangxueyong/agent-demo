package com.fc.test.service;

import com.fc.test.common.base.BaseService;
import com.fc.test.common.support.ConvertUtil;
import com.fc.test.mapper.auto.TPBaffleDataMethodMapper;
import com.fc.test.mapper.auto.TSysDictDataMapper;
import com.fc.test.model.auto.*;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.shiro.util.ShiroUtils;
import com.fc.test.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.velocity.app.event.implement.EscapeXmlReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 字典类型表Service
* @Title: TPBaffleDataMethodInfoService.java 
* @Package com.fc.test.service 
* @author 一休
* @email 438081243@qq.com
* @date 2019-09-05 12:34:25  
 */
@Service
public class BaffleDataMethodService implements BaseService<TPBaffleDataMethodInfo, TPBaffleDataMethodInfoExample>{

	@Autowired
	private TPBaffleDataMethodMapper baffleDataMethodMapper;
	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<TPBaffleDataMethodInfo> list(Tablepar tablepar,String name,String systemCode){
		 TPBaffleDataMethodInfoExample testExample=new TPBaffleDataMethodInfoExample();
		 testExample.setName(name);
		 testExample.setSystemCode(systemCode);

		 PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
		 List<TPBaffleDataMethodInfo> list= baffleDataMethodMapper.selectByExample(testExample);
		 PageInfo<TPBaffleDataMethodInfo> pageInfo = new PageInfo<TPBaffleDataMethodInfo>(list);
		 return  pageInfo;
	 }

	@Override
	@Transactional
	public int deleteByPrimaryKey(String ids) {
//		//查询type数据得data中DictType有哪些
//		List<String> lista=ConvertUtil.toListStrArray(ids);
//		TPBaffleDataMethodInfoExample example=new TPBaffleDataMethodInfoExample();
//		example.createCriteria().andIdIn(lista);
//		List<TPBaffleDataMethodInfo> dictTypes=TPBaffleDataMethodInfoMapper.selectByExample(example);
//		//在删除type下面得data数据
//		List<String> datatypes=new ArrayList<String>();
//		for (TPBaffleDataMethodInfo TPBaffleDataMethodInfo : dictTypes) {
//
//			datatypes.add(TPBaffleDataMethodInfo.getDictType());
//		}
//		TSysDictDataExample  dictDataExample=new TSysDictDataExample();
//		dictDataExample.createCriteria().andDictTypeIn(datatypes);
//		tSysDictDataMapper.deleteByExample(dictDataExample);
//		//在删除type数据
//		TPBaffleDataMethodInfoMapper.deleteByExample(example);
		return 1;
	}
	
	
	@Override
	public TPBaffleDataMethodInfo selectByPrimaryKey(String id) {
		return baffleDataMethodMapper.selectByPrimaryKey(Long.valueOf(id));
	}

	
	@Override
	public int updateByPrimaryKeySelective(TPBaffleDataMethodInfo record) {
		record.setMaintenanceTime(new Date());
		record.setMaintenanceUserNo(ShiroUtils.getUser().getUsername());
		return baffleDataMethodMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(TPBaffleDataMethodInfo record) {
		//添加雪花主键id
//		record.setId(Long.valueOf(SnowflakeIdWorker.getUUID()));
		record.setCreateTime(new Date());
		try {
			record.setCreateUserNo(ShiroUtils.getUser().getUsername());
		}catch (Exception e){
			record.setCreateUserNo("system");
		}

		return baffleDataMethodMapper.insertSelective(record);
	}

	
	@Override
	public int updateByExampleSelective(TPBaffleDataMethodInfo record, TPBaffleDataMethodInfoExample example) {
		
//		return TPBaffleDataMethodInfoMapper.updateByExampleSelective(record, example);
		return 0;
	}

	
	@Override
	public int updateByExample(TPBaffleDataMethodInfo record, TPBaffleDataMethodInfoExample example) {
		
//		return TPBaffleDataMethodInfoMapper.updateByExample(record, example);
		return 0;
	}

	@Override
	public List<TPBaffleDataMethodInfo> selectByExample(TPBaffleDataMethodInfoExample example) {
		
//		return TPBaffleDataMethodInfoMapper.selectByExample(example);
		return null;
	}

	
	@Override
	public long countByExample(TPBaffleDataMethodInfoExample example) {
		
//		return TPBaffleDataMethodInfoMapper.countByExample(example);
		return 0;
	}

	
	@Override
	public int deleteByExample(TPBaffleDataMethodInfoExample example) {
		
//		return TPBaffleDataMethodInfoMapper.deleteByExample(example);
		return 0;
	}

	/**
	 * 检查name
	 * @param TPBaffleDataMethodInfo
	 * @return
	 */
	public int checkNameUnique(TPBaffleDataMethodInfo TPBaffleDataMethodInfo){
//		TPBaffleDataMethodInfoExample example=new TPBaffleDataMethodInfoExample();
//		example.createCriteria().andDictNameEqualTo(TPBaffleDataMethodInfo.getDictName());
//		List<TPBaffleDataMethodInfo> list=TPBaffleDataMethodInfoMapper.selectByExample(example);
//		return list.size();
		return 0;
	}
}
