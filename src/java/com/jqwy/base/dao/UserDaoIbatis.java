package com.jqwy.base.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import com.jqwy.base.util.Menu;
import com.jqwy.base.util.User;

@Component
public class UserDaoIbatis extends SqlMapClientDaoSupport {
	public User checkUser(User u) {
		User user = (User) this.getSqlMapClientTemplate().queryForObject(
				"queryUserInfo", u);
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<Menu> queryMenuListByUser(User u) {
		List<Menu> list = this.getSqlMapClientTemplate().queryForList(
				"queryMenuListByUser", u);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Menu> queryMenuListForPublic() {
		List<Menu> list = this.getSqlMapClientTemplate().queryForList(
				"queryMenuListForPublic");
		return list;
	}
}