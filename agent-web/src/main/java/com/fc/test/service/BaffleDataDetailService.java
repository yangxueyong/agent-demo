package com.fc.test.service;

import com.fc.test.common.base.BaseService;
import com.fc.test.common.support.ConvertUtil;
import com.fc.test.mapper.auto.TPBaffleDataDetailMapper;
import com.fc.test.mapper.auto.TPBaffleDataMethodMapper;
import com.fc.test.model.auto.*;
import com.fc.test.model.auto.TPBaffleDataDetailInfo;
import com.fc.test.model.auto.TPBaffleDataDetailInfoExample;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.shiro.util.ShiroUtils;
import com.fc.test.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 字典类型表Service
* @Title: TPBaffleDataDetailInfoService.java 
* @Package com.fc.test.service 
* @author 一休
* @email 438081243@qq.com
* @date 2019-09-05 12:34:25  
 */
@Service
public class BaffleDataDetailService implements BaseService<TPBaffleDataDetailInfo, TPBaffleDataDetailInfoExample>{

	@Autowired
	private TPBaffleDataDetailMapper baffleDataDetailMapper;

	@Autowired
	private TPBaffleDataMethodMapper baffleDataMethodMapper;

	@Autowired
	private BaffleDataMethodService baffleDataMethodService;

	/**
	 * 分页查询
	 * @return
	 */
	 public PageInfo<TPBaffleDataDetailInfo> list(Tablepar tablepar,String name,Long methodId){
		 TPBaffleDataDetailInfoExample testExample=new TPBaffleDataDetailInfoExample();
		 testExample.setName(name);
		 testExample.setMethodId(methodId);

		 PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
		 List<TPBaffleDataDetailInfo> list= baffleDataDetailMapper.selectByExample(testExample);
		 PageInfo<TPBaffleDataDetailInfo> pageInfo = new PageInfo<TPBaffleDataDetailInfo>(list);
		 return  pageInfo;
	 }

	@Override
	@Transactional
	public int deleteByPrimaryKey(String ids) {
//		//查询type数据得data中DictType有哪些
		List<String> lista= ConvertUtil.toListStrArray(ids);
		return baffleDataDetailMapper.deleteByIds(lista);
	}
	
	
	@Override
	public TPBaffleDataDetailInfo selectByPrimaryKey(String id) {
		return baffleDataDetailMapper.selectByPrimaryKey(Long.valueOf(id));
	}

	
	@Override
	public int updateByPrimaryKeySelective(TPBaffleDataDetailInfo record) {
		record.setMaintenanceTime(new Date());
		record.setMaintenanceUserNo(ShiroUtils.getUser().getUsername());
		return baffleDataDetailMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(TPBaffleDataDetailInfo record) {
		//添加雪花主键id
//		record.setId(Long.valueOf(SnowflakeIdWorker.getUUID()));
		record.setCreateTime(new Date());
		record.setCreateUserNo(ShiroUtils.getUser().getUsername());
//
		return baffleDataDetailMapper.insertSelective(record);
	}

	
	@Override
	public int updateByExampleSelective(TPBaffleDataDetailInfo record, TPBaffleDataDetailInfoExample example) {
		
//		return TPBaffleDataDetailInfoMapper.updateByExampleSelective(record, example);
		return 0;
	}

	
	@Override
	public int updateByExample(TPBaffleDataDetailInfo record, TPBaffleDataDetailInfoExample example) {
		
//		return TPBaffleDataDetailInfoMapper.updateByExample(record, example);
		return 0;
	}

	@Override
	public List<TPBaffleDataDetailInfo> selectByExample(TPBaffleDataDetailInfoExample example) {
		
//		return TPBaffleDataDetailInfoMapper.selectByExample(example);
		return null;
	}

	public TPBaffleDataDetailInfo selectByInClass(TPBaffleDataDetailInfoByClassNameExample example) {
		TPBaffleDataDetailInfo tpBaffleDataDetailInfo = baffleDataDetailMapper.selectByInClass(example);
		if(tpBaffleDataDetailInfo != null){
			return tpBaffleDataDetailInfo;
		}
		TPBaffleDataMethodInfo tpBaffleDataMethodInfo = baffleDataMethodMapper.selectByInClass(example);
		if(tpBaffleDataMethodInfo == null){
			TPBaffleDataMethodInfo record = new TPBaffleDataMethodInfo();
			record.setMethodName(example.getMethodName());
			record.setSystemCode(example.getSystemCode());
			baffleDataMethodService.insertSelective(record);
		}
		return null;
	}

	
	@Override
	public long countByExample(TPBaffleDataDetailInfoExample example) {
		
//		return TPBaffleDataDetailInfoMapper.countByExample(example);
		return 0;
	}

	
	@Override
	public int deleteByExample(TPBaffleDataDetailInfoExample example) {
		
//		return TPBaffleDataDetailInfoMapper.deleteByExample(example);
		return 0;
	}

	/**
	 * 检查name
	 * @param TPBaffleDataDetailInfo
	 * @return
	 */
	public int checkNameUnique(TPBaffleDataDetailInfo TPBaffleDataDetailInfo){
//		TPBaffleDataDetailInfoExample example=new TPBaffleDataDetailInfoExample();
//		example.createCriteria().andDictNameEqualTo(TPBaffleDataDetailInfo.getDictName());
//		List<TPBaffleDataDetailInfo> list=TPBaffleDataDetailInfoMapper.selectByExample(example);
//		return list.size();
		return 0;
	}
}
