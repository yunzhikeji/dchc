package com.yz.service;

import java.util.List;

import com.yz.model.UserRole;

public interface IUserRoleService {

	//添加对象
	public abstract void add(UserRole user) throws Exception;

	//删除对象
	public abstract void delete(UserRole user);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(UserRole user);

	//获取所有对象
	public abstract List<UserRole> getUserRoles();

	//加载一个id的对象
	public abstract UserRole loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, UserRole user);

	//后台管理-获取符合条件的记录
	public abstract List<UserRole> queryList(int con, String convalue,UserRole user, int page,
			int size);

	//用户登录
	public abstract  UserRole userRolelogin(String username, String password) ;
	
	public abstract UserRole getUserRoleByUserRolename(String username);

}