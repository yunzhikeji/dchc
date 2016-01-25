package com.yz.service;

import java.util.List;

import com.yz.model.Troubleshooting;
import com.yz.model.UserRole;

public interface ITroubleshootingService {

	//添加对象
	public abstract void add(Troubleshooting troubleshooting) throws Exception;

	//删除对象
	public abstract void delete(Troubleshooting troubleshooting);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Troubleshooting troubleshooting);

	//获取所有对象
	public abstract List<Troubleshooting> getTroubleshootings();

	//加载一个id的对象
	public abstract Troubleshooting loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, UserRole user);

	//后台管理-获取符合条件的记录
	public abstract List<Troubleshooting> queryList(int con, String convalue,UserRole user, int page,
			int size);


	public abstract Troubleshooting getTroubleshootingById(Integer uptroubleshootingid);


}