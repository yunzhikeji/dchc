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
import com.yz.model.Person;
import com.yz.model.SocialMan;
import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.IPersonService;
import com.yz.service.IUnitService;
import com.yz.util.GenerateSqlFromExcel;
import com.yz.util.InfoType;
import com.yz.util.MyHandleUtil;

@Component("personService")
public class PersonServiceImp implements IPersonService {

	private IPersonDao personDao;
	private IUnitService unitService;
	private ISocialManDao socialManDao;

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
	 *      java.lang.String, int)
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
	 *      java.lang.String)
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
		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.PERSON);
		return personDao.getUniqueResult(queryString, p);
	}

	public Person getPersonByPersonname(String personname) {
		String queryString = "from Person mo where mo.name=:personname";
		String[] paramNames = new String[] { "personname" };
		Object[] values = new Object[] { personname };
		return personDao.queryByNamedParam(queryString, paramNames, values);
	}

	public Person getPersonByIdcard(String idcard) {
		String queryString = "from Person mo where mo.idcard=:idcard";
		String[] paramNames = new String[] { "idcard" };
		Object[] values = new Object[] { idcard };
		return personDao.queryByNamedParam(queryString, paramNames, values);
	}

	// 后台管理-获取符合条件的记录
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#queryList(int,
	 *      java.lang.String, int, int)
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

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.PERSON);

		return personDao.pageList(queryString, p, page, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IPersonService#getPersonById(java.lang.Integer)
	 */
	public Person getPersonById(Integer personid) {
		return personDao.getPersonById(personid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IPersonService#getPersonsByTypeAndHandleState(int,
	 *      java.lang.String, java.lang.String, java.lang.String, int, int,
	 *      com.yz.model.UserRole)
	 */
	public List<Person> getPersonsByTypeAndHandleState(int con,
			String convalue, String starttime, String endtime, int type,
			int handleState, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Person mo where mo.type=" + type
				+ " and mo.handleState=" + handleState;
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IPersonService#savereturn(com.yz.model.Person)
	 */
	public int savereturn(Person person) {
		return personDao.savereturn(person);
	}

	public void saveSocialManWithExcel(File file, UserRole userRole) {
		try {
			GenerateSqlFromExcel generate = new GenerateSqlFromExcel();
			ArrayList<String[]> arrayList = generate
					.generateStationBugSql(file);

			for (int i = 0; arrayList != null && i < arrayList.size(); i++) {
				String[] data = arrayList.get(i);
				Person person = new Person();
				// 实例化PO对象，用PO对象进行保存
				SocialMan socialMan = new SocialMan();
				// "人员编号","姓名","性别","出生日期","QQ","微信号","身份证号","户籍地址","现住地详址","人员分类","头像照"
				person.setNumber(data[0].toString());
				person.setName(data[1].toString());
				person.setSex(Integer.valueOf((data[2])));
				person.setBirthday(data[3].toString());
				person.setQq(data[4].toString());
				person.setWechat(data[5].toString());
				person.setIdcard(data[6].toString());
				person.setRegisterAddress(data[7].toString());
				person.setRegisterAddressArea(data[8].toString());
				person.setType(Integer.valueOf(data[9]));
				person.setPhotoImg(data[10].toString());
				person.setSocialMan(socialMan);
				socialManDao.save(socialMan);
				int pid = personDao.savereturn(person);
				
				//设置部门pids
				unitService.updateUnitByUserRoleAndInfoType(userRole.getUnit(),pid+"",InfoType.PERSON,1);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 条件查询人员列表
	 * 
	 * @see com.yz.service.IPersonService#getPersonsByHandleState(int,
	 *      java.lang.String, java.lang.String, java.lang.String, int,
	 *      com.yz.model.UserRole)
	 */
	public List<Person> getPersonsByHandleState(int con, String convalue,
			String starttime, String endtime, int handleState, UserRole userRole) {
		String queryString = "from Person mo where 1=1 and mo.handleState="
				+ handleState;
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}

	/*
	 * 条件查询超期办理人员列表
	 * 
	 * @see com.yz.service.IPersonService#getOutOfTimePersonsByType(int,
	 *      java.lang.String, java.lang.String, java.lang.String, int,
	 *      com.yz.model.UserRole)
	 */
	public List<Person> getOutOfTimePersonsByType(int con, String convalue,
			String starttime, String endtime, int type, UserRole userRole) {
		String queryString = "from Person mo where  mo.type=" + type
				+ " and mo.isOutOfTime=1";
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IPersonService#getOutOfTimePersons(int,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      com.yz.model.UserRole)
	 */
	public List<Person> getOutOfTimePersons(int con, String convalue,
			String starttime, String endtime, UserRole userRole) {
		String queryString = "from Person mo where  mo.isOutOfTime=1";
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IPersonService#getPersonsByType(int,
	 *      java.lang.String, java.lang.String, java.lang.String, int,
	 *      com.yz.model.UserRole)
	 */
	public List<Person> getPersonsByType(int con, String convalue,
			String starttime, String endtime, int type, UserRole userRole) {
		String queryString = "from Person mo where  mo.type=" + type;
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IPersonService#getPersonsByUserRole(int,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      com.yz.model.UserRole)
	 */
	public List<Person> getPersonsByUserRole(int con, String convalue,
			String starttime, String endtime, UserRole userRole) {
		String queryString = "from Person mo where  1=1";
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IPersonService#getNewPersonsByUserRole(com.yz.model.UserRole)
	 */
	public List<Person> getNewPersonsByUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Person mo where  mo.isNew=1 and mo.handleState=1 ";

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.PERSON);

		return personDao.queryList(queryString);
	}

	public List<Person> getPersonsByOption(int con, String convalue,
			UserRole userRole) {
		// TODO Auto-generated method stub
		/*
		 * 0:'选择类型',1:'人员姓名',2:'人员编号',3:'身份证号',4:'录入人员姓名',5:'DNA',6:'指纹',7:'户籍地',8:'手机号'
		 * ,9:'微信号',10:'性别',11:'QQ号',12:'出生日期'
		 * ,13:'银行卡号',14:'车牌号',15:'车架号',16:'手机串号' ,17:'发动机号'
		 */
		String queryString = "from Person mo where  1=1 ";

		if (con != 0 && convalue != null && !convalue.equals("")) {
			switch (con) {
			case 1:
				queryString += " and mo.name like  '%" + convalue + "%' ";
				break;
			case 2:
				queryString += " and mo.number like  '%" + convalue + "%' ";
				break;
			case 3:
				queryString += " and mo.idcard like  '%" + convalue + "%' ";
				break;
			case 4:
				queryString += " and mo.userRole.realname like  '%" + convalue
						+ "%' ";
				break;
			case 5:
				queryString += " and mo.gamblingCriminalMan.dnanumber like  '%"
						+ convalue + "%' ";
				break;
			case 6:
				queryString += " and mo.gamblingCriminalMan.fingerPrintNumber like  '%"
						+ convalue + "%' ";
				break;
			case 7:
				queryString += " and mo.registerAddress like  '%" + convalue
						+ "%' ";
				break;
			case 8:
				queryString += " and mo.telphone like  '%" + convalue + "%' ";
				break;
			case 9:
				queryString += " and mo.wechat like  '%" + convalue + "%' ";
				break;
			case 10:
				if (convalue.contains("男")) {
					queryString += " and mo.sex =1 ";
				} else if (convalue.contains("女")) {
					queryString += " and mo.sex =0 ";
				}
				break;
			case 11:
				queryString += " and mo.qq like  '%" + convalue + "%' ";
				break;
			case 12:
				queryString += " and mo.birthday like  '%" + convalue + "%' ";
				break;
			case 13:
				queryString += " and mo.bankCard like  '%" + convalue + "%' ";
				break;
			case 14:
				queryString += " and mo.carFrameNumber like  '%" + convalue
						+ "%' ";
				break;
			case 15:
				queryString += " and mo.gamblingCriminalMan.imei like  '%"
						+ convalue + "%' ";
				break;
			case 16:
				queryString += " and mo.gamblingCriminalMan.fingerPrintNumber like  '%"
						+ convalue + "%' ";
				break;
			case 17:
				queryString += " and mo.gamblingCriminalMan.engineNumber like  '%"
						+ convalue + "%' ";
				break;
			default:
				break;
			}
		}

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);

		return personDao.queryList(queryString);
	}

	/*
	 * 提取方法
	 */
	public List<Person> queryListBySql(int con, String convalue,
			String starttime, String endtime, UserRole userRole,
			String queryString) {

		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += " and mo.userRole.realname like  '%" + convalue
						+ "%' ";
			}
			if (con == 2) {
				queryString += " and mo.userRole.number like  '%" + convalue
						+ "%' ";
			}
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.joinDate>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.joinDate<='" + endtime + "'";
		}

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);

		return personDao.queryList(queryString);
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

	public IPersonDao getPersonDao() {
		return personDao;
	}

	@Resource
	public void setPersonDao(IPersonDao personDao) {
		this.personDao = personDao;
	}

}
