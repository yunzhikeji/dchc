package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IPersonDao;
import com.yz.model.Person;
import com.yz.model.UserRole;
import com.yz.service.IPersonService;
@Component("personService")
public class PersonServiceImp implements IPersonService {
	private IPersonDao personDao;
	public IPersonDao getPersonDao() {
		return personDao;
	}
	@Resource
	public void setPersonDao(IPersonDao personDao) {
		this.personDao = personDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#add(com.yz.model.Person)
	 */
	public void add(Person person) throws Exception {
		personDao.save(person);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#delete(com.yz.model.Person)
	 */
	public void delete(Person person) {
		personDao.delete(person);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		personDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#update(com.yz.model.Person)
	 */
	public void update(Person person) {
		personDao.update(person);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#getPersons()
	 */
	public List<Person> getPersons() {
		return personDao.getPersons();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#loadById(int)
	 */
	public Person loadById(int id) {
		return personDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole,int type, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from Person mo where 1=1 ";
		Object[] p = null;
		
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.number like ? "; 
			}
			if(con==2){
				queryString += "and mo.idcard like ? "; 
			}
			if(con==2){
				queryString += "and mo.userRole.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		if(type!=0){
			queryString += " and mo.type ="+type;
		}
		if(queryState!=0){
			queryString += " and mo.handleState ="+queryState;
		}
		if(starttime!=null&&!starttime.equals("")){
			queryString += " and mo.joinDate>='"+starttime+"'";
		}
		if(endtime!=null&&!endtime.equals("")){
			queryString += " and mo.joinDate<='"+endtime+"'";
		}
		return personDao.getUniqueResult(queryString,p);
	}
	public Person getPersonByPersonname(String personname) {
		String queryString="from Person mo where mo.name=:personname";
		String[] paramNames=new String[]{"personname"};
		Object[] values=new Object[]{personname};
		return personDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Person> queryList(int con, String convalue, UserRole userRole, int page, int size,int type, int queryState, String starttime, String endtime) {
		String queryString = "from Person mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.number like ? "; 
			}
			if(con==2){
				queryString += "and mo.idcard like ? "; 
			}
			if(con==2){
				queryString += "and mo.userRole.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		if(type!=0){
			queryString += " and mo.type ="+type;
		}
		if(queryState!=0){
			queryString += " and mo.handleState ="+queryState;
		}
		if(starttime!=null&&!starttime.equals("")){
			queryString += " and mo.joinDate>='"+starttime+"'";
		}
		if(endtime!=null&&!endtime.equals("")){
			queryString += " and mo.joinDate<='"+endtime+"'";
		}
		return personDao.pageList(queryString,p,page,size);
	}

	public Person getPersonById(Integer uppersonid) {
		// TODO Auto-generated method stub
		return personDao.getPersonById(uppersonid);
	}
}
