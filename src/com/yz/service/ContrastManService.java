package com.yz.service;

import com.yz.model.ContrastMan;
import com.yz.model.UserRole;

import java.util.List;

public interface ContrastManService {

	//添加对象
	void add(ContrastMan contrastMan) throws Exception;

	//删除对象
	void delete(ContrastMan contrastMan);

	//删除某个id的对象
	void deleteById(int id);

	//修改对象
	void update(ContrastMan contrastMan);

	//获取所有对象
	List<ContrastMan> getContrastMans();

	//加载一个id的对象
	ContrastMan loadById(int id);

	//后台管理-页数获取
	int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	int getTotalCount(int con, String convalue, UserRole user, int type, int queryState, String starttime, String endtime);

	//后台管理-获取符合条件的记录
	List<ContrastMan> queryList(int con, String convalue,UserRole user, int page,
			int size, int type, int queryState, String starttime, String endtime);
}