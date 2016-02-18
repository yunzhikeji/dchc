package com.yz.service;

import java.util.List;

import com.yz.model.Injurycase;
import com.yz.model.UserRole;

public interface IInjurycaseService {


	//添加对象
	public abstract void add(Injurycase injurycase) throws Exception;

	//删除对象
	public abstract void delete(Injurycase injurycase);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Injurycase injurycase);

	//获取所有对象
	public abstract List<Injurycase> getInjurycases();

	//加载一个id的对象
	public abstract Injurycase loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, UserRole user, int queryState, String starttime, String endtime);

	//后台管理-获取符合条件的记录
	public abstract List<Injurycase> queryList(int con, String convalue,UserRole user, int page,
			int size, int queryState, String starttime, String endtime);


	public abstract Injurycase getInjurycaseById(Integer upinjurycaseid);


	public abstract Injurycase queryInjurycaseById(int id);

	public abstract int getTotalCount(int con, String convalue,
			UserRole userRoleo, int itype, int queryState, String starttime,
			String endtime);

	public abstract List<Injurycase> queryList(int con, String convalue,
			UserRole userRoleo, int page, int size, int itype, int queryState,
			String starttime, String endtime);

	public abstract List<Injurycase> getInjurycaseByTypeAndHandleState(
			int itype, int handleState, UserRole userRole);
}
