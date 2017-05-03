package com.yz.service;

import com.yz.model.Person;
import com.yz.model.UserRole;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface PersonService {

	// 添加对象
	void add(Person person) throws Exception;

	// 删除对象
	void delete(Person person);

	// 删除某个id的对象
	void deleteById(int id);

	// 修改对象
	void update(Person person);

	// 获取所有对象
	List<Person> getPersons();
	
	
	//根据身份证号获取person
	public Person getPersonByIdcard(String idcard) ;

	// 加载一个id的对象
	Person loadById(int id);

	// 后台管理-页数获取
	int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	int getTotalCount(int con, String convalue, UserRole user,
			int type, int queryState, String starttime, String endtime);

	// 后台管理-获取符合条件的记录
	List<Person> queryList(int con, String convalue,
			UserRole user, int page, int size, int type, int queryState,
			String starttime, String endtime);

	Person getPersonById(Integer personid);

	List<Person> getPersonsByTypeAndHandleState(int con,String convalue,String starttime,String endtime,int type,
			int handleState, UserRole userRole);

	int savereturn(Person person);

	void savePersonWithExcel(File fileTest, UserRole userRole,int type);
	
	//获取excel的标题数据集
	public ArrayList getExcelFieldNameList(int type);
	
	public ArrayList getExcelFieldDataList(int con, String convalue,
			UserRole user,int type, int queryState,
			String starttime, String endtime);

	List<Person> getPersonsByHandleState(int con,String convalue,String starttime,String endtime,int i,
			UserRole userRole);

	List<Person> getOutOfTimePersons(int con,String convalue,String starttime,String endtime,UserRole userRole);

	List<Person> getOutOfTimePersonsByType(int con,String convalue,String starttime,String endtime,int i,
			UserRole userRole);

	List<Person> getPersonsByType(int con,String convalue,String starttime,String endtime,int i, UserRole userRole);

	List<Person> getPersonsByUserRole(int con,String convalue,String starttime,String endtime,UserRole userRole);

	List<Person> getNewPersonsByUserRole(UserRole userRole);

	List<Person> getPersonsByOption(int personOption,
			String convalue, UserRole userRole);



}