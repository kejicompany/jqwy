package com.jqwy.base.web;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.caucho.hessian.client.HessianProxyFactory;
import com.jqwy.base.util.IUser;
import com.jqwy.base.util.JSONResult;
import com.jqwy.base.util.Menu;
import com.jqwy.base.util.User;

@Controller
public class UserController {
	private static final String MENU_INFO = "menuInfo";
	private static final String USER_INFO = "userInfo";
	private static final String TOKEN = "token";
	@Value("${app.url}")
	private String appLocation;
	private String appService = "/userService";
	HessianProxyFactory proxyFactory = new HessianProxyFactory();
	private IUser userService = null;

	private final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四",
			"星期五", "星期六" };

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String today = sdf.format(new Date());
		Calendar calendar = Calendar.getInstance();
		String week = dayNames[calendar.get(Calendar.DAY_OF_WEEK) - 1];

		ModelAndView mav = new ModelAndView();

		List<Menu> menu = (List<Menu>) request.getSession().getAttribute(
				MENU_INFO);
		if (menu == null) {
			if (userService == null)
				userService = (IUser) proxyFactory.create(IUser.class,
						appLocation + appService);
			menu = userService.queryMenuListForPublic();
		}
		mav.addObject(MENU_INFO, menu);
		mav.addObject("today", today + "   " + week);
		mav.addObject(TOKEN,
				request.getSession().getAttribute(TOKEN) == null ? "unchecked"
						: "checked");
		mav.addObject(USER_INFO, request.getSession().getAttribute(USER_INFO));
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "/user/login.do", method = RequestMethod.POST)
	@ResponseBody
	public JSONResult login(HttpServletRequest request) {
		User user = new User();
		JSONResult result = new JSONResult();
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		if (userService == null)
			try {
				userService = (IUser) proxyFactory.create(IUser.class,
						appLocation + appService);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		User currentUser = userService.checkUser(user);
		List<Menu> menuList = userService.queryMenuListByUser(user);
		if (currentUser != null) {
			request.getSession().setAttribute(TOKEN, "checked");
			request.getSession().setAttribute(USER_INFO, currentUser);
			request.getSession().removeAttribute(MENU_INFO);
			request.getSession().setAttribute(MENU_INFO, menuList);
			result.setStatus("ok");
			result.setResult("身份验证通过!");
		} else {
			result.setStatus("error");
			result.setResult("身份验证失败!");
		}
		return result;
	}

	@RequestMapping(value = "/user/logout.do", method = RequestMethod.POST)
	@ResponseBody
	public String logout(HttpSession session) {
		session.removeAttribute(TOKEN);
		session.removeAttribute(USER_INFO);
		session.removeAttribute(MENU_INFO);
		return "logout";
	}

	@RequestMapping(value = "/user/userInput.do", method = RequestMethod.POST)
	public ModelAndView regist() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/regist");
		return mav;
	}
	@RequestMapping(value = "/user/userSave.do", method = RequestMethod.POST)
	@ResponseBody
	public JSONResult registUser(@RequestBody User user){
		JSONResult  jr = new JSONResult();
		System.out.println(user);
		return  jr;
	}
}
