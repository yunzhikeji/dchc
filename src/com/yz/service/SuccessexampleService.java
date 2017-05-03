package com.yz.service;

import com.yz.model.Successexample;
import com.yz.model.UserRole;

import java.util.List;

public interface SuccessexampleService {

	//添加对象
	void add(Successexample successexample) throws Exception;

	//删除对象
	void delete(Successexample successexample);

	//删除某个id的对象
	void deleteById(int id);

	//修改对象
	void update(Successexample successexample);

	//获取所有对象
	List<Successexample> getSuccessexamples();

	//加载一个id的对象
	Successexample loadById(int id);

	//后台管理-页数获取
	int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	int getTotalCount(int con, String convalue, UserRole user);

	//后台管理-获取符合条件的记录
	List<Successexample> queryList(int con, String convalue,UserRole user, int page,
			int size);


	int getTotalCount(int con, String convalue,
			UserRole currentUserRole, String starttime, String endtime);

	List<Successexample> queryList(int con, String convalue,
			UserRole currentUserRole, int page, int size, String starttime,
			String endtime);


}