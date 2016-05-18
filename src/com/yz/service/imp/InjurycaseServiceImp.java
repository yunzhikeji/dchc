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

/**
 * @author jiang
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
				queryString += "and mo.caseName like ? ";
			}
			if (con == 2) {
				queryString += "and mo.caseNumber like ? ";
			}
			if (con == 3) {
				queryString += "and mo.casePlace like ? ";
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
				queryString += "and mo.caseName like ? ";
			}
			if (con == 2) {
				queryString += "and mo.caseNumber like ? ";
			}
			if (con == 3) {
				queryString += "and mo.casePlace like ? ";
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
		// 用户所在机构不为空
		String inids = "";
		if(userRole!=null&&userRole.getUnit()!=null&&userRole.getUnit().getInids()!=null)
		{
			inids = userRole.getUnit().getInids().replace(" ", "");
			queryString = setStringIds(queryString,inids);
		}else
		{
			queryString += " and mo.id in (0)";
		}
		return injurycaseDao.getUniqueResult(queryString, p);
	}

	public List<Injurycase> queryList(int con, String convalue,
			UserRole userRole, int page, int size, int itype, int queryState,
			String starttime, String endtime) {
		String queryString = "from Injurycase mo where 1=1 ";
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
		// 用户所在机构不为空
		String inids = "";
		if(userRole!=null&&userRole.getUnit()!=null&&userRole.getUnit().getInids()!=null)
		{
			inids = userRole.getUnit().getInids().replace(" ", "");
			queryString = setStringIds(queryString,inids);
		}else
		{
			queryString += " and mo.id in (0)";
		}
		return injurycaseDao.pageList(queryString, p, page, size);
	}

	public List<Injurycase> getInjurycaseByTypeAndHandleState(int itype,
			int handleState, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where mo.itype=" + itype
				+ " and mo.handleState=" + handleState;
		// 用户所在机构不为空
		String inids = "";
		if(userRole!=null&&userRole.getUnit()!=null&&userRole.getUnit().getInids()!=null)
		{
			inids = userRole.getUnit().getInids().replace(" ", "");
			queryString = setStringIds(queryString,inids);
		}else
		{
			queryString += " and mo.id in (0)";
		}
		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> queryInjurycaseBySeries(String series, int id) {
		String queryString = "from Injurycase mo where mo.series=:series and mo.isRelated=1 and mo.id!="+id;
		String[] paramNames = new String[] { "series" };
		Object[] values = new Object[] { series };
		return injurycaseDao.queryList(queryString, paramNames, values);
	}

	public List<Injurycase> queryInjurycaseByKeyword(String param,int id) {

		String queryString = "from Injurycase mo where  mo.id!="+id;
		Object[] p = null;
		if (param != null && !param.equals("")) {
			queryString += "and  (mo.caseNumber like ?  or mo.caseType like ? or mo.caseName like ? ) ";
			p = new Object[] { '%' + param + '%', '%' + param + '%',
					'%' + param };
		}
		queryString += " order by mo.id desc ";
		return injurycaseDao.getObjectsByCondition(queryString, p);

	}
	
	private String setStringIds(String queryString,String inids) {
		// TODO Auto-generated method stub
		//用户所在机构不为空
		if(inids!=""&&!inids.equals(","))
		{
			String lastChar = inids.substring(inids.length()-1, inids.length());
			if(lastChar.equals(","))
			{
				inids = inids.substring(0, inids.length()-1);
			}
			queryString += " and mo.id in ("+inids+")";
		}else
		{
			queryString += " and mo.id in (0)";
		}
		return queryString;
	}

}
