package com.yz.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.yz.model.Person;
import com.yz.model.UserRole;

public interface IPersonService {

	// 添加对象
	public abstract void add(Person person) throws Exception;

	// 删除对象
	public abstract void delete(Person person);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(Person person);

	// 获取所有对象
	public abstract List<Person> getPersons();
	
	
	//根据身份证号获取person
	public Person getPersonByIdcard(String idcard) ;

	// 加载一个id的对象
	public abstract Person loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, UserRole user,
			int type, int queryState, String starttime, String endtime);

	// 后台管理-获取符合条件的记录
	public abstract List<Person> queryList(int con, String convalue,
			UserRole user, int page, int size, int type, int queryState,
			String starttime, String endtime);

	public abstract Person getPersonById(Integer personid);

	public abstract List<Person> getPersonsByTypeAndHandleState(int con,String convalue,String starttime,String endtime,int type,
			int handleState, UserRole userRole);

	public abstract int savereturn(Person person);

	public abstract void saveSocialManWithExcel(File fileTest, UserRole userRole,int type);
	
	//获取excel的标题数据集
	public ArrayList getExcelFieldNameList(int type);
	
	public ArrayList getExcelFieldDataList(int type);

	public abstract List<Person> getPersonsByHandleState(int con,String convalue,String starttime,String endtime,int i,
			UserRole userRole);

	public abstract List<Person> getOutOfTimePersons(int con,String convalue,String starttime,String endtime,UserRole userRole);

	public abstract List<Person> getOutOfTimePersonsByType(int con,String convalue,String starttime,String endtime,int i,
			UserRole userRole);

	public abstract List<Person> getPersonsByType(int con,String convalue,String starttime,String endtime,int i, UserRole userRole);

	public abstract List<Person> getPersonsByUserRole(int con,String convalue,String starttime,String endtime,UserRole userRole);

	public abstract List<Person> getNewPersonsByUserRole(UserRole userRole);

	public abstract List<Person> getPersonsByOption(int personOption,
			String convalue, UserRole userRole);

}