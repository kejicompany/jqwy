package com.jqwy.publish.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/notice")
@Controller
public class NoticeController {

	@RequestMapping(value = "/queryNoticeList.do", method = RequestMethod.POST)
	public ModelAndView queryNoticeList() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/list");
		return mav;
	}
}
