package com.yz.service;

import com.yz.model.Pnotice;
import com.yz.model.UserRole;

import java.util.List;

public interface PnoticeService {

	//添加对象
	void add(Pnotice pnotice) throws Exception;

	//删除对象
	void delete(Pnotice pnotice);

	//删除某个id的对象
	void deleteById(int id);

	//修改对象
	void update(Pnotice pnotice);

	//获取所有对象
	List<Pnotice> getPnotices();

	//加载一个id的对象
	Pnotice loadById(int id);

	//后台管理-页数获取
	int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	int getTotalCount(int con, String convalue, UserRole user, int type, int queryState, String starttime, String endtime);

	//后台管理-获取符合条件的记录
	List<Pnotice> queryList(int con, String convalue,UserRole user, int page,
			int size, int type, int queryState, String starttime, String endtime);


	int getTotalCount(int con, String convalue,
			UserRole currentUserRole, String starttime, String endtime);

	List<Pnotice> queryList(int con, String convalue,
			UserRole currentUserRole, int page, int size, String starttime,
			String endtime);


}