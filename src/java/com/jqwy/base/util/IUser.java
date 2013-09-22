package com.jqwy.base.util;

import java.util.List;

public interface IUser {
	public User checkUser(User u);

	public List<Menu> queryMenuListByUser(User u);

	public List<Menu> queryMenuListForPublic();
}
