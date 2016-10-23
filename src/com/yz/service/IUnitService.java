package com.yz.service;

import java.util.List;

import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.util.InfoType;

public interface IUnitService {

	//添加对象
	public abstract void add(Unit unit) throws Exception;

	//删除对象
	public abstract void delete(Unit unit);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Unit unit);

	//获取所有对象
	public abstract List<Unit> getUnits();

	//加载一个id的对象
	public abstract Unit loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, UserRole user);

	//后台管理-获取符合条件的记录
	public abstract List<Unit> queryList(int con, String convalue,UserRole user, int page,
			int size);


	public abstract Unit getUnitById(Integer upunitid);

	public abstract Unit getUnitByName(String name);
	
	public abstract Unit getUnitByNumber(String number);

	public abstract Unit queryByUid(int uid);

	//根据当前部门的ids(pids,inids,cids) OperationType:1增加 -1删除
	public abstract void updateUnitByUserRoleAndInfoType(Unit unit,String id,
			InfoType infoType,int OperationType);
	
	//发起研判时，设置ids
	public void updateJudgeUnit(String id, InfoType infoType, int operationType);



}