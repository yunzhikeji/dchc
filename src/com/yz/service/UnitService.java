package com.yz.service;

import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.util.InfoType;

import java.util.List;

public interface UnitService {

	//添加对象
	void add(Unit unit) throws Exception;

	//删除对象
	void delete(Unit unit);

	//删除某个id的对象
	void deleteById(int id);

	//修改对象
	void update(Unit unit);

	//获取所有对象
	List<Unit> getUnits();

	//加载一个id的对象
	Unit loadById(int id);

	//后台管理-页数获取
	int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	int getTotalCount(int con, String convalue, UserRole user);

	//后台管理-获取符合条件的记录
	List<Unit> queryList(int con, String convalue,UserRole user, int page,
			int size);


	Unit getUnitById(Integer upunitid);

	Unit getUnitByName(String name);
	
	Unit getUnitByNumber(String number);

	Unit getUnitByUid(int uid);

	//根据当前部门的ids(pids,inids,cids) OperationType:1增加 -1删除
	void updateUnitByUserRoleAndInfoType(Unit unit,String id,
			InfoType infoType,int OperationType);
	
	//发起研判时，设置ids
	void updateJudgeUnit(String id, InfoType infoType, int operationType);



}