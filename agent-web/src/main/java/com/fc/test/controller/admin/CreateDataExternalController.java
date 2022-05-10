package com.fc.test.controller.admin;

import com.alibaba.fastjson.JSON;
import com.fc.test.common.base.BaseController;
import com.fc.test.common.domain.AjaxResult;
import com.fc.test.model.auto.TPBaffleDataDetailInfo;
import com.fc.test.model.auto.TPBaffleDataDetailInfoByClassNameExample;
import com.fc.test.model.auto.TPBaffleDataMethodInfo;
import com.fc.test.model.custom.TableSplitResult;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.TitleVo;
import com.fc.test.service.BaffleDataDetailService;
import com.fc.test.service.BaffleDataMethodService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 字典类型Controller
 * @ClassName: DictTypeController
 * @author fuce
 * @date 2019-11-20 22:45
 */
@Api(value = "字典类型表")
@Controller
@RequestMapping("/CreateDataExternalController")
public class CreateDataExternalController extends BaseController{

	@Autowired
	private BaffleDataDetailService baffleDataDetailService;

	@ApiOperation(value = "测试方法", notes = "测试方法")
	@RequestMapping("/findParam")
	@ResponseBody
	public String findParam(String param,String methodName,String systemCode) {
		TPBaffleDataDetailInfoByClassNameExample example = new TPBaffleDataDetailInfoByClassNameExample();
		example.setInParam(param);
		example.setMethodName(methodName);
		example.setSystemCode(systemCode);
		TPBaffleDataDetailInfo tpBaffleDataDetailInfo = baffleDataDetailService.selectByInClass(example);
		if(tpBaffleDataDetailInfo!=null) {
			return tpBaffleDataDetailInfo.getOutParam();
		}
		return "";
	}


	@ApiOperation(value = "测试方法", notes = "测试方法")
	@RequestMapping(value="/findParam2",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String findParam2(@RequestBody TPBaffleDataDetailInfoByClassNameExample example) {
		TPBaffleDataDetailInfo tpBaffleDataDetailInfo = baffleDataDetailService.selectByInClass(example);
		if(tpBaffleDataDetailInfo!=null) {
			return tpBaffleDataDetailInfo.getOutParam();
		}
		return "";
	}

}
