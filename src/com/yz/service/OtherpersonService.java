package com.yz.service;

import com.yz.model.Otherperson;
import com.yz.model.UserRole;

import java.util.List;

public interface OtherpersonService {

	//添加对象
	void add(Otherperson otherperson) throws Exception;

	//删除对象
	void delete(Otherperson otherperson);

	//删除某个id的对象
	void deleteById(int id);

	//修改对象
	void update(Otherperson otherperson);

	//获取所有对象
	List<Otherperson> getOtherpersons();

	//加载一个id的对象
	Otherperson loadById(int id);

	//后台管理-页数获取
	int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	int getTotalCount(int con, String convalue, UserRole user);

	//后台管理-获取符合条件的记录
	List<Otherperson> queryList(int con, String convalue,UserRole user, int page,
			int size);


	List<Otherperson> getOtherpersonByOtype(int i, Integer integer);

	List<Otherperson> getInjurycaseOtherpersonByOtype(int i,
			int id);


}