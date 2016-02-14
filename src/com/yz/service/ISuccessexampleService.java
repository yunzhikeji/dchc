package com.yz.service;

import java.util.List;

import com.yz.model.Successexample;
import com.yz.model.UserRole;

public interface ISuccessexampleService {

	//添加对象
	public abstract void add(Successexample successexample) throws Exception;

	//删除对象
	public abstract void delete(Successexample successexample);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Successexample successexample);

	//获取所有对象
	public abstract List<Successexample> getSuccessexamples();

	//加载一个id的对象
	public abstract Successexample loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, UserRole user);

	//后台管理-获取符合条件的记录
	public abstract List<Successexample> queryList(int con, String convalue,UserRole user, int page,
			int size);


	public abstract Successexample getSuccessexampleById(Integer upsuccessexampleid);

	public abstract int getTotalCount(int con, String convalue,
			UserRole userRoleo, String starttime, String endtime);

	public abstract List<Successexample> queryList(int con, String convalue,
			UserRole userRoleo, int page, int size, String starttime,
			String endtime);


}