package com.yz.service;

import com.yz.model.UserRole;

import java.util.List;

public interface UserRoleService {

	//添加对象
	void add(UserRole user) throws Exception;

	//删除对象
	void delete(UserRole user);

	//删除某个id的对象
	void deleteById(int id);

	//修改对象
	void update(UserRole user);

	//获取所有对象
	List<UserRole> getUserRoles();

	//加载一个id的对象
	UserRole loadById(int id);

	//后台管理-页数获取
	int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	int getTotalCount(int con, String convalue, UserRole user);

	//后台管理-获取符合条件的记录
	List<UserRole> queryList(int con, String convalue,UserRole user, int page,
			int size);

	//用户登录
	 UserRole getUserRoleByUsernameAndPassword(String username, String password);
	
	UserRole getUserRoleByUsername(String username);

	UserRole getUserRoleById(Integer id);

	UserRole getUserRoleByCardid(String cardid);

	List<UserRole> getUserRoleBUnitName(String unitName);
}