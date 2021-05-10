package com.okmillet.regulate.framework;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.okmillet.regulate.common.utils.ResultCode;

public abstract class BaseAction {

	protected final Log logger = LogFactory.getLog(getClass());

	public String result(String tpl){
		return tpl;
	}

	public String redirect(String uri){//浏览器请求资源
		return "redirect:"+uri;
	}
	
	public String forward(String uri){//服务器请求资源
		return "forward:"+uri;
	}

	public String ajax(HttpServletResponse response, ResultCode code, Object obj){
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		JSONObject json = new JSONObject();
		try {
			PrintWriter out = response.getWriter();
			json.put("resultCode", code.getCode());
			json.put("resultMsg", code.getMsg());
			json.put("data", obj);
			out.print(JSONObject.toJSONString(json,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero));
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String ajaxWithoutNull(HttpServletResponse response, ResultCode code, Object obj){
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		JSONObject json = new JSONObject();
		try {
			PrintWriter out = response.getWriter();
			json.put("ret", code.getCode());
			json.put("msg", code.getMsg());
			json.put("data", obj);
			out.print(json.toJSONString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String ajax(HttpServletResponse response, ResultCode code){
		return ajax(response, code, null);
	}

	public String out(HttpServletResponse response, String msg){
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(msg);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String forbidden(HttpServletResponse response) {
		try {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String serverError(HttpServletResponse response) {
		try {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String notFound(HttpServletResponse response) {
		try {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
