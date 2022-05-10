package com.fc.test.controller.admin;

import com.fc.test.common.base.BaseController;
import com.fc.test.common.domain.AjaxResult;
import com.fc.test.model.auto.TPBaffleDataDetailInfo;
import com.fc.test.model.auto.TPBaffleDataMethodInfo;
import org.springframework.ui.Model;
import com.fc.test.model.auto.TSysDictType;
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
@RequestMapping("/CreateDataDetailController")
public class CreateDataDetailController extends BaseController{
	
	private String prefix = "admin/create_data_detail";
	@Autowired
	private BaffleDataDetailService baffleDataDetailService;
	
	/**
	 * 分页list页面
	 * @param model
	 * @param dictId
	 * @return
	 */
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/view")
	@RequiresPermissions("db:pft:view")
    public String view(ModelMap model,String methodId)
    {	
		String str="挡板详情表";
		model.put("methodId",methodId);
		setTitle(model, new TitleVo("列表", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/list";
    }
	
	/**
	 * 字典类型表集合查询
	 * @param tablepar
	 * @param searchText
	 * @return
	 */
	//@Log(title = "字典类型表集合查询", action = "111")
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@PostMapping("/list")
	@RequiresPermissions("db:pft:view")
	@ResponseBody
	public Object list(Tablepar tablepar,String searchText,Long methodId){
		PageInfo<TPBaffleDataDetailInfo> page=baffleDataDetailService.list(tablepar,searchText,methodId) ;
		TableSplitResult<TPBaffleDataDetailInfo> result=new TableSplitResult<>(page.getPageNum(), page.getTotal(), page.getList());
		return  result;
	}

	
    /**
     * 新增保存
     * @param baffleDataDetailInfo
     * @param model
     * @return
     */
	//@Log(title = "字典类型表新增", action = "111")
	@ApiOperation(value = "新增", notes = "新增")
	@PostMapping("/add")
	@RequiresPermissions("db:pft:view")
	@ResponseBody
	public AjaxResult add(TPBaffleDataDetailInfo baffleDataDetailInfo,Model model){
		int b=baffleDataDetailService.insertSelective(baffleDataDetailInfo);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}

	/**
	 * 新增跳转
	 * @param modelMap
	 * @return
	 */
	@ApiOperation(value = "新增跳转", notes = "新增跳转")
	@GetMapping("/add")
	public String add(ModelMap modelMap,String methodId){
		modelMap.put("methodId",methodId);
		return prefix + "/add";
	}
//
	/**
	 * 删除字典类型
	 * @param ids
	 * @return
	 */
	//@Log(title = "字典类型表删除", action = "111")
	@ApiOperation(value = "删除", notes = "删除")
	@PostMapping("/remove")
	@RequiresPermissions("db:pft:view")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b=baffleDataDetailService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
//
//	/**
//	 * 检查字典类型名字相同
//	 * @param tsysUser
//	 * @return
//	 */
//	@ApiOperation(value = "检查Name唯一", notes = "检查Name唯一")
//	@PostMapping("/checkNameUnique")
//	@ResponseBody
//	public int checkNameUnique(TSysDictType tSysDictType){
//		int b=tSysDictTypeService.checkNameUnique(tSysDictType);
//		if(b>0){
//			return 1;
//		}else{
//			return 0;
//		}
//	}
//
//
	/**
	 * 修改跳转
	 * @param id
	 * @param mmap
	 * @return
	 */
	@ApiOperation(value = "修改跳转", notes = "修改跳转")
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap){
        mmap.put("dataDetail", baffleDataDetailService.selectByPrimaryKey(id));
        return prefix + "/edit";
    }


	/**
     * 修改保存
     */
    //@Log(title = "字典类型表修改", action = "111")
	@ApiOperation(value = "修改保存", notes = "修改保存")
    @RequiresPermissions("db:pft:view")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TPBaffleDataDetailInfo record){
        return toAjax(baffleDataDetailService.updateByPrimaryKeySelective(record));
    }

	
}
