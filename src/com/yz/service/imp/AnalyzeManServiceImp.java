package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IAnalyzeManDao;
import com.yz.model.AnalyzeMan;
import com.yz.model.UserRole;
import com.yz.service.IAnalyzeManService;

@Component("analyzeManService")
public class AnalyzeManServiceImp implements IAnalyzeManService {
	private IAnalyzeManDao analyzeManDao;

	public IAnalyzeManDao getAnalyzeManDao() {
		return analyzeManDao;
	}

	@Resource
	public void setAnalyzeManDao(IAnalyzeManDao analyzeManDao) {
		this.analyzeManDao = analyzeManDao;
	}

	// 添加对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#add(com.yz.model.Person)
	 */
	public void add(AnalyzeMan analyzeMan) throws Exception {
		analyzeManDao.save(analyzeMan);
	}

	// 删除对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#delete(com.yz.model.Person)
	 */
	public void delete(AnalyzeMan analyzeMan) {
		analyzeManDao.delete(analyzeMan);
	}

	// 删除某个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		analyzeManDao.deleteById(id);
	}

	// 修改对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#update(com.yz.model.Person)
	 */
	public void update(AnalyzeMan analyzeMan) {
		analyzeManDao.update(analyzeMan);
	}

	// 获取所有对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#getPersons()
	 */
	public List<AnalyzeMan> getDisappearMen() {
		return analyzeManDao.getDisappearMen();
	}

	// 加载一个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#loadById(int)
	 */
	public AnalyzeMan loadById(int id) {
		return analyzeManDao.loadById(id);
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
		String queryString = "select count(*) from AnalyzeMan mo where 1=1 ";
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
		return analyzeManDao.getUniqueResult(queryString, p);
	}

	// public AnalyzeMan getAnalyzeManByAnalyzeManname(String disappearname) {
	// String queryString="from Person mo where mo.name=:disappearname";
	// String[] paramNames=new String[]{"personname"};
	// Object[] values=new Object[]{disappearname};
	// //return analyzeManDao.queryByNamedParam(queryString,paramNames,values);
	// }
	// 后台管理-获取符合条件的记录
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#queryList(int,
	 *      java.lang.String, int, int)
	 */
	public List<AnalyzeMan> queryList(int con, String convalue,
			UserRole userRole, int page, int size, int type, int queryState,
			String starttime, String endtime) {
		String queryString = "from AnalyzeMan mo where 1=1 ";
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
		return analyzeManDao.pageList(queryString, p, page, size);
	}

	public AnalyzeMan getAnalyzeManById(Integer analyzeManid) {
		// TODO Auto-generated method stub
		return analyzeManDao.getAnalyzeManById(analyzeManid);
	}
}
