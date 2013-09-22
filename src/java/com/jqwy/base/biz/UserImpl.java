package com.jqwy.base.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jqwy.base.biz.UserService;
import com.jqwy.base.util.IUser;
import com.jqwy.base.util.Menu;
import com.jqwy.base.util.User;

@Service
public class UserImpl implements IUser {
	@Autowired
	private UserService userService;

	@Override
	public User checkUser(User u) {
		return userService.checkUser(u);
	}

	@Override
	public List<Menu> queryMenuListByUser(User u) {
		return userService.queryMenuListByUser(u);
	}

	@Override
	public List<Menu> queryMenuListForPublic() {
		return userService.queryMenuListForPublic();
	}

}
