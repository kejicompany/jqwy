package com.jqwy.base.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jqwy.base.dao.UserDaoIbatis;
import com.jqwy.base.util.Menu;
import com.jqwy.base.util.User;

@Service
public class UserService {

	@Autowired
	private UserDaoIbatis userDaoIbatis;

	public User checkUser(User u) {
		return userDaoIbatis.checkUser(u);
	}

	public List<Menu> queryMenuListByUser(User u) {
		return userDaoIbatis.queryMenuListByUser(u);
	}

	public List<Menu> queryMenuListForPublic() {
		return userDaoIbatis.queryMenuListForPublic();
	}
}
