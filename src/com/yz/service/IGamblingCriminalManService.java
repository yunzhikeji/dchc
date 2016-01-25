package com.yz.service;

import java.util.List;

import com.yz.model.GamblingCriminalMan;
import com.yz.model.UserRole;

public interface IGamblingCriminalManService {

	//添加对象
	public abstract void add(GamblingCriminalMan gamblingCriminalMan) throws Exception;

	//删除对象
	public abstract void delete(GamblingCriminalMan gamblingCriminalMan);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(GamblingCriminalMan gamblingCriminalMan);

	//获取所有对象
	public abstract List<GamblingCriminalMan> getGamblingCriminalMans();

	//加载一个id的对象
	public abstract GamblingCriminalMan loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, UserRole user, int type, int queryState, String starttime, String endtime);

	//后台管理-获取符合条件的记录
	public abstract List<GamblingCriminalMan> queryList(int con, String convalue,UserRole user, int page,
			int size, int type, int queryState, String starttime, String endtime);


	public abstract GamblingCriminalMan getGamblingCriminalManById(Integer upgamblingCriminalManid);


}