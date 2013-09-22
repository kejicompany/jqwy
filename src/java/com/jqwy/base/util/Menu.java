package com.jqwy.base.util;

import java.io.Serializable;

public class Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8659523973665398542L;
	private String supMenu;
	private String menuName;
	private String resouce;
	private int menuOrder;

	public String getSupMenu() {
		return supMenu;
	}

	public void setSupMenu(String supMenu) {
		this.supMenu = supMenu;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getResouce() {
		return resouce;
	}

	public void setResouce(String resouce) {
		this.resouce = resouce;
	}

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}
}
