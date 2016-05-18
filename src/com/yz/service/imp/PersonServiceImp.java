package com.yz.service.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IPersonDao;
import com.yz.dao.ISocialManDao;
import com.yz.dao.IUnitDao;
import com.yz.model.Person;
import com.yz.model.SocialMan;
import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.IPersonService;
import com.yz.service.IUnitService;
import com.yz.util.GenerateSqlFromExcel;

@Component("personService")
public class PersonServiceImp implements IPersonService {
	private IPersonDao personDao;
	private IUnitService unitService;
	private ISocialManDao socialManDao;

	public IPersonDao getPersonDao() {
		return personDao;
	}

	@Resource
	public void setPersonDao(IPersonDao personDao) {
		this.personDao = personDao;
	}

	// 添加对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#add(com.yz.model.Person)
	 */
	public void add(Person person) throws Exception {
		personDao.save(person);
	}

	// 删除对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#delete(com.yz.model.Person)
	 */
	public void delete(Person person) {
		personDao.delete(person);
	}

	// 删除某个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		personDao.deleteById(id);
	}

	// 修改对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#update(com.yz.model.Person)
	 */
	public void update(Person person) {
		personDao.update(person);
	}

	// 获取所有对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#getPersons()
	 */
	public List<Person> getPersons() {
		return personDao.getPersons();
	}

	// 加载一个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#loadById(int)
	 */
	public Person loadById(int id) {
		return personDao.loadById(id);
	}

	// 后台管理-页数获取
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#getPageCount(int,
	 * java.lang.String, int)
	 */
	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}

	// 后台管理-获取总记录数
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#getTotalCount(int,
	 * java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole,
			int type, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from Person mo where 1=1 ";
		Object[] p = null;

		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.number like ? ";
			}
			if (con == 3) {
				queryString += "and mo.idcard like ? ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (type != 0) {
			queryString += " and mo.type =" + type;
		}
		if (queryState != 0) {
			queryString += " and mo.handleState =" + queryState;
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.joinDate>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.joinDate<='" + endtime + "'";
		}
		// 用户所在机构不为空
		String pids = "";
		if(userRole!=null&&userRole.getUnit()!=null&&userRole.getUnit().getPids()!=null)
		{
			pids = userRole.getUnit().getPids().replace(" ", "");
			queryString = setStringIds(queryString,pids);
		}else
		{
			queryString += " and mo.id in (0)";
		}
		return personDao.getUniqueResult(queryString, p);
	}

	public Person getPersonByPersonname(String personname) {
		String queryString = "from Person mo where mo.name=:personname";
		String[] paramNames = new String[] { "personname" };
		Object[] values = new Object[] { personname };
		return personDao.queryByNamedParam(queryString, paramNames, values);
	}

	// 后台管理-获取符合条件的记录
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#queryList(int,
	 * java.lang.String, int, int)
	 */
	public List<Person> queryList(int con, String convalue, UserRole userRole,
			int page, int size, int type, int queryState, String starttime,
			String endtime) {
		String queryString = "from Person mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.number like ? ";
			}
			if (con == 3) {
				queryString += "and mo.idcard like ? ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (type != 0) {
			queryString += " and mo.type =" + type;
		}
		if (queryState != 0) {
			queryString += " and mo.handleState =" + queryState;
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.joinDate>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.joinDate<='" + endtime + "'";
		}
		
		// 用户所在机构不为空
		String pids = "";
		if(userRole!=null&&userRole.getUnit()!=null&&userRole.getUnit().getPids()!=null)
		{
			pids = userRole.getUnit().getPids().replace(" ", "");
			queryString = setStringIds(queryString,pids);
		}else
		{
			queryString += " and mo.id in (0)";
		}
		return personDao.pageList(queryString, p, page, size);
	}

	public Person getPersonById(Integer uppersonid) {
		// TODO Auto-generated method stub
		return personDao.getPersonById(uppersonid);
	}

	public Person queryPersonById(int id) {
		String queryString = "from Person mo where mo.id=:id";
		String[] paramNames = new String[] { "id" };
		Object[] values = new Object[] { id };
		return personDao.queryByNamedParam(queryString, paramNames, values);
	}

	public List<Person> getPersonsByTypeAndHandleState(int type,
			int handleState, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Person mo where mo.type=" + type
				+ " and mo.handleState=" + handleState;
		
		// 用户所在机构不为空
		String pids = "";
		if(userRole!=null&&userRole.getUnit()!=null&&userRole.getUnit().getPids()!=null)
		{
			pids = userRole.getUnit().getPids().replace(" ", "");
			queryString = setStringIds(queryString,pids);
		}else
		{
			queryString += " and mo.id in (0)";
		}
		
		return personDao.queryList(queryString);
	}

	private String setStringIds(String queryString,String pids) {
		// TODO Auto-generated method stub
		//用户所在机构不为空
		if(pids!=""&&!pids.equals(","))
		{
			String lastChar = pids.substring(pids.length()-1, pids.length());
			if(lastChar.equals(","))
			{
				pids = pids.substring(0, pids.length()-1);
			}
			queryString += " and mo.id in ("+pids+")";
		}else
		{
			queryString += " and mo.id in (0)";
		}
		return queryString;
	}

	public int savereturn(Person person) {
		// TODO Auto-generated method stub
		return personDao.savereturn(person);
	}

	public void saveSocialManWithExcel(Person person, File file,
			UserRole userRole) {
		try {
			GenerateSqlFromExcel generate = new GenerateSqlFromExcel();
			ArrayList<String[]> arrayList = generate
					.generateStationBugSql(file);

			for (int i = 0; arrayList != null && i < arrayList.size(); i++) {
				String[] data = arrayList.get(i);
				// 实例化PO对象，用PO对象进行保存
				SocialMan socialMan = new SocialMan();
				// 人员编号 姓名 性别 出生日期 QQ 微信号 身份证号 手机号码 户籍地址 户籍区域
				person.setNumber(data[0].toString());
				person.setName(data[1].toString());
				person.setBirthday(data[2].toString());
				person.setQq(data[3].toString());
				person.setWechat(data[4].toString());
				person.setIdcard(data[5].toString());
				person.setRegisterAddress(data[6].toString());
				person.setRegisterAddressArea(data[7].toString());
				person.setType(15);
				person.setSocialMan(socialMan);
				socialManDao.save(socialMan);
				int pid = personDao.savereturn(person);
				if (userRole.getUnit() != null) {
					int uid = userRole.getUnit().getId();
					Unit un = unitService.queryByUid(uid);

					if (un.getPids() != null && un.getPids() != "") {
						un.setPids(handleIDs(un.getPids(), pid + ""));
					} else {
						un.setPids(pid + ",");
					}
					System.out.println(un.getPids());
					unitService.update(un);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 处理ids
	private String handleIDs(String objIDs, String objID) {
		Set<String> ids = new HashSet<String>();
		String newIDs = "";
		String[] arrayIDs = objIDs.split(",");
		for (int i = 0; i < arrayIDs.length; i++) {
			ids.add(arrayIDs[i]);
		}
		ids.add(objID);

		for (String id : ids) {
			newIDs = newIDs + id + ",";
		}
		System.out.println(newIDs);
		return newIDs;
	}

	public ISocialManDao getSocialManDao() {
		return socialManDao;
	}

	@Resource
	public void setSocialManDao(ISocialManDao socialManDao) {
		this.socialManDao = socialManDao;
	}


	public IUnitService getUnitService() {
		return unitService;
	}

	@Resource
	public void setUnitService(IUnitService unitService) {
		this.unitService = unitService;
	}
	
	

}
