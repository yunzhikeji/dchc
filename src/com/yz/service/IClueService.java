package com.yz.service;

import java.util.List;

import com.yz.model.Clue;
import com.yz.model.UserRole;

public interface IClueService {

	//添加对象
	public abstract void add(Clue clue) throws Exception;

	//删除对象
	public abstract void delete(Clue clue);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Clue clue);

	//获取所有对象
	public abstract List<Clue> getClues();

	//加载一个id的对象
	public abstract Clue loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, UserRole user, int type, int queryState, String starttime, String endtime);

	//后台管理-获取符合条件的记录
	public abstract List<Clue> queryList(int con, String convalue,UserRole user, int page,
			int size, int type, int queryState, String starttime, String endtime);


	public abstract Clue getClueById(Integer cid);

	public abstract List<Clue> getCluesByTypeAndHandleState(int ctype,
			int handleState, UserRole userRole);

	public abstract List<Clue> getCluesByTypeAndHandleState(int con, String convalue,
			String starttime, String endtime, int ctype, int state, UserRole userRole);

	public abstract List<Clue> getCluesByHandleState(int con, String convalue,
			String starttime, String endtime, int state, UserRole userRole);

	public abstract List<Clue> getOutOfTimeCluesByType(int con, String convalue,
			String starttime, String endtime, int ctype, UserRole userRole);

	public abstract List<Clue> getOutOfTimeCluesByUserRole(int con, String convalue,
			String starttime, String endtime, UserRole userRole);

	public abstract List<Clue> getCluesByType(int con, String convalue,
			String starttime, String endtime, int ctype, UserRole userRole);

	public abstract List<Clue> getCluesByUserRole(int con, String convalue,
			String starttime, String endtime, UserRole userRole);



}