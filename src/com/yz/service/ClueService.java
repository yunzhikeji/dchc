package com.yz.service;

import com.yz.model.Clue;
import com.yz.model.UserRole;

import java.util.List;

public interface ClueService {

	//添加对象
	void add(Clue clue) throws Exception;

	//删除对象
	void delete(Clue clue);

	//删除某个id的对象
	void deleteById(int id);

	//修改对象
	void update(Clue clue);

	//获取所有对象
	List<Clue> getClues();

	//加载一个id的对象
	Clue loadById(int id);

	//后台管理-页数获取
	int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	int getTotalCount(int con, String convalue, UserRole user, int type, int queryState, String starttime, String endtime);

	//后台管理-获取符合条件的记录
	List<Clue> queryList(int con, String convalue, UserRole user, int page,
						 int size, int type, int queryState, String starttime, String endtime);


	List<Clue> getCluesByTypeAndHandleState(int con, String convalue,
											String starttime, String endtime, int ctype, int state, UserRole userRole);


	List<Clue> getOutOfTimeCluesByType(int con, String convalue,
									   String starttime, String endtime, int ctype, UserRole userRole);


	List<Clue> getCluesByType(int con, String convalue,
							  String starttime, String endtime, int ctype, UserRole userRole);


	List<Clue> getNewClueByUserRole(UserRole userRole);


}