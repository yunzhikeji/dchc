package com.yz.service;

import com.yz.model.GamblingCriminalMan;
import com.yz.model.UserRole;

import java.util.List;

public interface GamblingCriminalManService {

	//添加对象
	void add(GamblingCriminalMan gamblingCriminalMan) throws Exception;

	//删除对象
	void delete(GamblingCriminalMan gamblingCriminalMan);

	//删除某个id的对象
	void deleteById(int id);

	//修改对象
	void update(GamblingCriminalMan gamblingCriminalMan);

	//获取所有对象
	List<GamblingCriminalMan> getGamblingCriminalMans();

	//加载一个id的对象
	GamblingCriminalMan loadById(int id);

	//后台管理-页数获取
	int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	int getTotalCount(int con, String convalue, UserRole user, int type, int queryState, String starttime, String endtime);

	//后台管理-获取符合条件的记录
	List<GamblingCriminalMan> queryList(int con, String convalue,UserRole user, int page,
			int size, int type, int queryState, String starttime, String endtime);

}