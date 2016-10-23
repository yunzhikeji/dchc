/**
 * 
 */
package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IInjurycaseDao;
import com.yz.model.Injurycase;
import com.yz.model.Person;
import com.yz.model.UserRole;
import com.yz.service.IInjurycaseService;
import com.yz.util.InfoType;
import com.yz.util.MyHandleUtil;

/**
 * @author
 * 
 */
@Component("injurycaseService")
public class InjurycaseServiceImp implements IInjurycaseService {
	private IInjurycaseDao injurycaseDao;

	public IInjurycaseDao getInjurycaseDao() {
		return injurycaseDao;
	}

	@Resource
	public void setInjurycaseDao(IInjurycaseDao injurycaseDao) {
		this.injurycaseDao = injurycaseDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#add(com.yz.model.Injurycase)
	 */
	public void add(Injurycase injurycase) throws Exception {
		injurycaseDao.save(injurycase);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#delete(com.yz.model.Injurycase)
	 */
	public void delete(Injurycase injurycase) {
		injurycaseDao.delete(injurycase);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#deleteById(int)
	 */
	public void deleteById(int id) {
		injurycaseDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#getInjurycaseById(java.lang.Integer)
	 */
	public Injurycase getInjurycaseById(Integer upinjurycaseid) {
		return injurycaseDao.getInjurycaseById(upinjurycaseid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#getInjurycases()
	 */
	public List<Injurycase> getInjurycases() {
		return injurycaseDao.getInjurycases();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#getPageCount(int, int)
	 */
	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#getTotalCount(int,
	 *      java.lang.String, com.yz.model.UserRole, int, int, java.lang.String,
	 *      java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole user,
			int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from Injurycase mo where 1=1 ";
		Object[] p = null;

		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.caseNumber like ? ";
			}
			if (con == 2) {
				queryString += "and mo.casePlace like ? ";
			}
			if (con == 3) {
				queryString += "and mo.caseName like ? ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (queryState != 0) {
			queryString += " and mo.handleState =" + queryState;
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.startTime>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.startTime<='" + endtime + "'";
		}
		return injurycaseDao.getUniqueResult(queryString, p);
	}

	public Injurycase getInjurycaseByInjurycasename(String injurycasename) {
		String queryString = "from Injurycase mo where mo.casename=:injurycasename";
		String[] paramNames = new String[] { "injurycasename" };
		Object[] values = new Object[] { injurycasename };
		return injurycaseDao.queryByNamedParam(queryString, paramNames, values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#loadById(int)
	 */
	public Injurycase loadById(int id) {
		return injurycaseDao.loadById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#queryInjurycaseById(int)
	 */
	public Injurycase queryInjurycaseById(int id) {
		String queryString = "from Injurycase mo where mo.id=:id";
		String[] paramNames = new String[] { "id" };
		Object[] values = new Object[] { id };
		return injurycaseDao.queryByNamedParam(queryString, paramNames, values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#queryList(int, java.lang.String,
	 *      com.yz.model.UserRole, int, int, int, int, java.lang.String,
	 *      java.lang.String)
	 */
	public List<Injurycase> queryList(int con, String convalue, UserRole user,
			int page, int size, int queryState, String starttime, String endtime) {
		String queryString = "from Injurycase mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.caseNumber like ? ";
			}
			if (con == 2) {
				queryString += "and mo.casePlace like ? ";
			}
			if (con == 3) {
				queryString += "and mo.caseName like ? ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}

		if (queryState != 0) {
			queryString += " and mo.handleState =" + queryState;
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.startTime>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.startTime<='" + endtime + "'";
		}
		return injurycaseDao.pageList(queryString, p, page, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#update(com.yz.model.Injurycase)
	 */
	public void update(Injurycase injurycase) {
		injurycaseDao.update(injurycase);
	}

	public int getTotalCount(int con, String convalue, UserRole userRole,
			int itype, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from Injurycase mo where 1=1 ";
		Object[] p = null;

		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.caseNumber like ? ";
			}
			if (con == 2) {
				queryString += "and mo.casePlace like ? ";
			}
			if (con == 3) {
				queryString += "and mo.caseName like ? ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like ? ";
			}

			p = new Object[] { '%' + convalue + '%' };
		}
		if (itype != 0) {
			queryString += " and mo.itype =" + itype;
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
		queryString = MyHandleUtil.setSqlLimit(queryString, userRole ,InfoType.CASE);
		return injurycaseDao.getUniqueResult(queryString, p);
	}

	public List<Injurycase> queryList(int con, String convalue,
			UserRole userRole, int page, int size, int itype, int queryState,
			String starttime, String endtime) {
		String queryString = "from Injurycase mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.caseNumber like ? ";
			}
			if (con == 2) {
				queryString += "and mo.casePlace like ? ";
			}
			if (con == 3) {
				queryString += "and mo.caseName like ? ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (itype != 0) {
			queryString += " and mo.itype =" + itype;
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
		queryString =  MyHandleUtil.setSqlLimit(queryString, userRole ,InfoType.CASE);
		return injurycaseDao.pageList(queryString, p, page, size);
	}

	public List<Injurycase> getInjurycaseByTypeAndHandleState(int itype,
			int handleState, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where mo.itype=" + itype
				+ " and mo.handleState=" + handleState;
		queryString =  MyHandleUtil.setSqlLimit(queryString, userRole ,InfoType.CASE);
		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> queryInjurycaseBySeries(String series, int id) {
		String queryString = "from Injurycase mo where mo.series=:series and mo.isRelated=1 and mo.id!="
				+ id;
		String[] paramNames = new String[] { "series" };
		Object[] values = new Object[] { series };
		return injurycaseDao.queryList(queryString, paramNames, values);
	}

	public List<Injurycase> queryInjurycaseByKeyword(String param, int id) {

		String queryString = "from Injurycase mo where  mo.id!=" + id;
		Object[] p = null;
		if (param != null && !param.equals("")) {
			queryString += "and  (mo.caseNumber like ?  or mo.caseType like ? or mo.caseName like ? ) ";
			p = new Object[] { '%' + param + '%', '%' + param + '%',
					'%' + param };
		}
		queryString += " order by mo.id desc ";
		return injurycaseDao.getObjectsByCondition(queryString, p);

	}

	public List<Injurycase> getInjurycaseByTypeAndHandleState(int con,
			String convalue, String starttime, String endtime, int itype,
			int handleState, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where mo.itype=" + itype
				+ " and mo.handleState=" + handleState;

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString =  MyHandleUtil.setSqlLimit(queryString, userRole ,InfoType.CASE);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInjurycasesByHandleState(int con,
			String convalue, String starttime, String endtime, int state,
			UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where  mo.handleState="
				+ state;

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString =  MyHandleUtil.setSqlLimit(queryString, userRole ,InfoType.CASE);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInOutOfTimejurycasesByUserRole(int con,
			String convalue, String starttime, String endtime, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where  mo.isOutOfTime=1";

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString =  MyHandleUtil.setSqlLimit(queryString, userRole ,InfoType.CASE);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInjurycasesByType(int con, String convalue,
			String starttime, String endtime, int itype, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where  mo.itype=" + itype;

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString =  MyHandleUtil.setSqlLimit(queryString, userRole ,InfoType.CASE);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInjurycasesByUserRole(int con, String convalue,
			String starttime, String endtime, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where  1=1 ";

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString =  MyHandleUtil.setSqlLimit(queryString, userRole ,InfoType.CASE);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getOutOfTimeInjurycasesByType(int con,
			String convalue, String starttime, String endtime, int itype,
			UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where   mo.itype=" + itype
				+ " and mo.isOutOfTime=1";

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString =  MyHandleUtil.setSqlLimit(queryString, userRole ,InfoType.CASE);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getNewInjurycaseByUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where   mo.isNew=1 and mo.handleState=1 ";

		queryString =  MyHandleUtil.setSqlLimit(queryString, userRole ,InfoType.CASE);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInjurycasesByOption(int con, String convalue,
			UserRole userRole) {
		// TODO Auto-generated method stub
		// 0:'选择类型',1:'案件编号',2:'案件地址',3:'案件名称',4:'录入人员姓名',5:'录入单位',6:'案发时间'
		String queryString = "from Injurycase mo where   1=1 ";

		if (con != 0 && convalue != null && !convalue.equals("")) {
			switch (con) {
			case 1:
				queryString += " and mo.caseNumber like  '%" + convalue + "%' ";
				break;
			case 2:
				queryString += " and mo.casePlace like  '%" + convalue + "%' ";
				break;
			case 3:
				queryString += " and mo.caseName like  '%" + convalue + "%' ";
				break;
			case 4:
				queryString += " and mo.userRole.realname like  '%" + convalue
						+ "%' ";
				break;
			case 5:
				queryString += " and mo.userRole.unit.name like  '%" + convalue + "%' ";
				break;
			case 6:
				queryString += " and mo.startTime like  '%" + convalue + "%' ";
				break;
			default:
				break;
			}
		}

		queryString =  MyHandleUtil.setSqlLimit(queryString, userRole ,InfoType.CASE);

		return injurycaseDao.queryList(queryString);
	}

	// 设置 sql语句 参数配置
	private String setSqlParms(int con, String convalue, String starttime,
			String endtime, String queryString) {
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

		return queryString;
	}

}
