package com.yz.service;

import java.util.List;

import com.yz.model.Lawcase;
import com.yz.model.UserRole;

public interface ILawcaseService {

	//添加对象
	public abstract void add(Lawcase lawcase) throws Exception;

	//删除对象
	public abstract void delete(Lawcase lawcase);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Lawcase lawcase);

	//获取所有对象
	public abstract List<Lawcase> getLawcases();

	//加载一个id的对象
	public abstract Lawcase loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, UserRole user);

	//后台管理-获取符合条件的记录
	public abstract List<Lawcase> queryList(int con, String convalue,UserRole user, int page,
			int size);


	public abstract Lawcase getLawcaseById(Integer uplawcaseid);


}