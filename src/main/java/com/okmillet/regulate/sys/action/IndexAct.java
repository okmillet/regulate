package com.okmillet.regulate.sys.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.okmillet.regulate.common.utils.Encryptor;
import com.okmillet.regulate.framework.BaseAction;
import com.okmillet.regulate.sys.model.SysUser;
import com.okmillet.regulate.sys.service.SysUserService;

@Controller
public class IndexAct extends BaseAction {

	@Resource
	private SysUserService sysUserService;

	/**
	 * login
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login.htm")
	public String login(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		if(request.getMethod().equals("POST")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (StringUtils.hasText(username)&&StringUtils.hasText(password)) {
				SysUser su = sysUserService.getByUserName(username);
				if(su != null && su.getUserPwd().equals(Encryptor.MD5(password))) {
					return redirect("/index.htm");
				}
			}
		}
		return result("login.html");
	}

	/**
	 * index
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index.htm")
	public String index(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		return result("index.html");
	}

	/**
	 * welcome
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/welcome.htm")
	public String welcome(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
//		return result("welcome.html");
		return result("sys/sysUser/list.html");
	}
}