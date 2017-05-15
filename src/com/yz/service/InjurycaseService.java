package com.yz.service;

import com.yz.model.Injurycase;
import com.yz.model.UserRole;

import java.io.File;
import java.util.List;

public interface InjurycaseService {

	// 添加对象
	void add(Injurycase injurycase) throws Exception;

	// 删除对象
	void delete(Injurycase injurycase);

	// 删除某个id的对象
	void deleteById(int id);

	// 修改对象
	void update(Injurycase injurycase);

	// 加载一个id的对象
	Injurycase loadById(int id);

	// 后台管理-页数获取
	int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	int getTotalCount(int con, String convalue, UserRole user,
			int queryState, String starttime, String endtime);

	// 后台管理-获取符合条件的记录
	List<Injurycase> queryList(int con, String convalue,
			UserRole user, int page, int size, int queryState,
			String starttime, String endtime);

	Injurycase queryInjurycaseById(int id);


	//保留
	int getTotalCount(int con, String convalue,
			UserRole currentUserRole, int itype, int queryState, String starttime,
			String endtime);

	List<Injurycase> queryList(int con, String convalue,
			UserRole currentUserRole, int page, int size, int itype, int queryState,
			String starttime, String endtime);

	List<Injurycase> queryInjurycaseByKeyword(String keyword,
			int id);

	List<Injurycase> getInjurycaseByTypeAndHandleState(int con,
			String convalue, String starttime, String endtime, int itype,
			int state, UserRole userRole);

	List<Injurycase> getInjurycasesByHandleState(int con,
			String convalue, String starttime, String endtime, int state,
			UserRole userRole);

	List<Injurycase> getInOutOfTimejurycasesByUserRole(int con,
			String convalue, String starttime, String endtime, UserRole userRole);

	List<Injurycase> getOutOfTimeInjurycasesByType(int con,
			String convalue, String starttime, String endtime, int itype,
			UserRole userRole);

	List<Injurycase> getInjurycasesByType(int con,
			String convalue, String starttime, String endtime, int itype,
			UserRole userRole);

	List<Injurycase> getInjurycasesByUserRole(int con,
			String convalue, String starttime, String endtime, UserRole userRole);

	List<Injurycase> getNewInjurycaseByUserRole(
			UserRole userRole);

	List<Injurycase> getInjurycasesByOption(
			int injurycaseOption, String convalue, UserRole userRole);

	List<Injurycase> queryList(int con, String convalue,
			UserRole userRole, int itype, int queryState, String starttime,
			String endtime);

	boolean saveInjurycaseWithExcel(File injurycase_file,
			UserRole userRole, int itype);



}
