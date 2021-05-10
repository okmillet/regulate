package com.okmillet.regulate.sys.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.okmillet.regulate.common.utils.ResultCode;
import com.okmillet.regulate.framework.BaseAction;
import com.okmillet.regulate.framework.Page;
import com.okmillet.regulate.sys.model.SysUser;
import com.okmillet.regulate.sys.query.SysUserQuery;
import com.okmillet.regulate.sys.service.SysUserService;

@Controller
@RequestMapping("/acs")
public class SysUserAct extends BaseAction {

	@Resource
	private SysUserService sysUserService;

	/**
	 * 根据id获取系统用户信息
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sysUser/detail.htm", method = RequestMethod.GET)
	public String get(Integer id,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		if (id != null) {
			model.put("entity", sysUserService.getById(id));
		}
		return result("sys/sysUser/addOrEdit.html");
	}

	/**
	 * 获取系统用户列表
	 * @param q
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sysUser/page.htm")
	public String page(SysUserQuery q,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		model.put("q", q);
		try {
			model.put("page", sysUserService.findPage(q));
		} catch (Exception e) {
			e.printStackTrace();
			model.put("page", new Page(1,20,0));
		}
		return result("sys/sysUser/list.html");
	}

	/**
	 * 添加、修改
	 * @param entity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sysUser/persist.htm", method = RequestMethod.POST)
	public String modify(SysUser entity,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		try {
			if(entity.getId() == null) {
				sysUserService.save(entity);
			}else {
				sysUserService.update(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.clear();
			model.put("entity", entity);
			return result("sys/sysUser/addOrEdit.html");
		}
		return redirect("/acs/sysUser/page.htm");
	}

	/**
	 * 删除
	 * @param ids
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sysUser/del.htm", method = RequestMethod.POST)
	public String del(String ids,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		try {
			String[] ida = ids.split(",");
			for (int i = 0; i < ida.length; i++) {
				sysUserService.removeById(Integer.valueOf(ida[i]));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(response, ResultCode.EXCEPTION_OCCURE);
		}
		return ajax(response, ResultCode.SUCCESS);
	}
}