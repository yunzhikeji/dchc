package com.yz.service;

import com.yz.model.Troubleshooting;
import com.yz.model.UserRole;

import java.util.List;

public interface TroubleshootingService {

	//添加对象
	void add(Troubleshooting troubleshooting) throws Exception;

	//删除对象
	void delete(Troubleshooting troubleshooting);

	//删除某个id的对象
	void deleteById(int id);

	//修改对象
	void update(Troubleshooting troubleshooting);

	//获取所有对象
	List<Troubleshooting> getTroubleshootings();

	//加载一个id的对象
	Troubleshooting loadById(int id);

	//后台管理-页数获取
	int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	int getTotalCount(int con, String convalue, UserRole user);

	//后台管理-获取符合条件的记录
	List<Troubleshooting> queryList(int con, String convalue,UserRole user, int page,
			int size);

}