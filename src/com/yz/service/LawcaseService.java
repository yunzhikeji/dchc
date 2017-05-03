package com.yz.service;

import com.yz.model.Lawcase;
import com.yz.model.UserRole;

import java.util.List;

public interface LawcaseService {

	//添加对象
	void add(Lawcase lawcase) throws Exception;

	//删除对象
	void delete(Lawcase lawcase);

	//删除某个id的对象
	void deleteById(int id);

	//修改对象
	void update(Lawcase lawcase);

	//获取所有对象
	List<Lawcase> getLawcases();

	//加载一个id的对象
	Lawcase loadById(int id);

	//后台管理-页数获取
	int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	int getTotalCount(int con, String convalue, UserRole user);

	//后台管理-获取符合条件的记录
	List<Lawcase> queryList(int con, String convalue,UserRole user, int page,
			int size);

}