package com.yz.service;

import java.io.File;
import java.util.List;

import com.yz.model.Person;
import com.yz.model.UserRole;

public interface IPersonService {

	//添加对象
	public abstract void add(Person person) throws Exception;

	//删除对象
	public abstract void delete(Person person);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Person person);

	//获取所有对象
	public abstract List<Person> getPersons();

	//加载一个id的对象
	public abstract Person loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, UserRole user, int type, int queryState, String starttime, String endtime);

	//后台管理-获取符合条件的记录
	public abstract List<Person> queryList(int con, String convalue,UserRole user, int page,
			int size, int type, int queryState, String starttime, String endtime);


	public abstract Person getPersonById(Integer uppersonid);


	public abstract Person queryPersonById(int id);

	public abstract List<Person> getPersonsByTypeAndHandleState(int type,
			int handleState, UserRole userRole);

	public abstract int savereturn(Person person);

	public abstract void saveSocialManWithExcel(Person person, File fileTest,UserRole userRole);


}