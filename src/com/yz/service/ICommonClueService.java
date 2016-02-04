package com.yz.service;

import java.util.List;

import com.yz.model.CommonClue;
import com.yz.model.UserRole;

public interface ICommonClueService {

	//添加对象
	public abstract void add(CommonClue commonClue) throws Exception;

	//删除对象
	public abstract void delete(CommonClue commonClue);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(CommonClue commonClue);

	//获取所有对象
	public abstract List<CommonClue> getCommonClues();

	//加载一个id的对象
	public abstract CommonClue loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, UserRole user, int type, int queryState, String starttime, String endtime);

	//后台管理-获取符合条件的记录
	public abstract List<CommonClue> queryList(int con, String convalue,UserRole user, int page,
			int size, int type, int queryState, String starttime, String endtime);


	public abstract CommonClue getCommonClueById(Integer upcommonClueid);


}