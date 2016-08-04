package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IClueDao;
import com.yz.model.Clue;
import com.yz.model.Person;
import com.yz.model.UserRole;
import com.yz.service.IClueService;

@Component("clueService")
public class ClueServiceImp implements IClueService {
	private IClueDao clueDao;

	public IClueDao getClueDao() {
		return clueDao;
	}

	@Resource
	public void setClueDao(IClueDao clueDao) {
		this.clueDao = clueDao;
	}

	// 添加对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#add(com.yz.model.Person)
	 */
	public void add(Clue clue) throws Exception {
		clueDao.save(clue);
	}

	// 删除对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#delete(com.yz.model.Person)
	 */
	public void delete(Clue clue) {
		clueDao.delete(clue);
	}

	// 删除某个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		clueDao.deleteById(id);
	}

	// 修改对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#update(com.yz.model.Person)
	 */
	public void update(Clue clue) {
		clueDao.update(clue);
	}

	// 获取所有对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#getPersons()
	 */
	public List<Clue> getClues() {
		return clueDao.getClues();
	}

	// 加载一个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#loadById(int)
	 */
	public Clue loadById(int id) {
		return clueDao.loadById(id);
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
			int ctype, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from Clue mo where 1=1 ";
		Object[] p = null;

		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.number like ? ";
			}
			if (con == 2) {
				queryString += "and mo.securityClassification like ? ";
			}
			if (con == 3) {
				queryString += "and mo.intelligenceType like ? ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.name like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (ctype != 0) {
			queryString += " and mo.ctype =" + ctype;
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
		queryString = setSqlLimit(queryString, userRole);

		return clueDao.getUniqueResult(queryString, p);
	}

	public Clue getClueByCluename(String cluename) {
		String queryString = "from Clue mo where mo.name=:cluename";
		String[] paramNames = new String[] { "cluename" };
		Object[] values = new Object[] { cluename };
		return clueDao.queryByNamedParam(queryString, paramNames, values);
	}

	// 后台管理-获取符合条件的记录
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#queryList(int,
	 *      java.lang.String, int, int)
	 */
	public List<Clue> queryList(int con, String convalue, UserRole userRole,
			int page, int size, int ctype, int queryState, String starttime,
			String endtime) {
		String queryString = "from Clue mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.number like ? ";
			}
			if (con == 2) {
				queryString += "and mo.securityClassification like ? ";
			}
			if (con == 3) {
				queryString += "and mo.intelligenceType like ? ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.name like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (ctype != 0) {
			queryString += " and mo.ctype =" + ctype;
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
		queryString = setSqlLimit(queryString, userRole);
		return clueDao.pageList(queryString, p, page, size);
	}

	public Clue getClueById(Integer cid) {
		// TODO Auto-generated method stub
		return clueDao.getClueById(cid);
	}

	public List<Clue> getCluesByTypeAndHandleState(int ctype, int handleState,
			UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Clue mo where mo.ctype=" + ctype
				+ " and mo.handleState=" + handleState;

		queryString = setSqlLimit(queryString, userRole);

		return clueDao.queryList(queryString);
	}

	public List<Clue> getCluesByHandleState(int con, String convalue,
			String starttime, String endtime, int state, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Clue mo where  mo.handleState=" + state;

		queryString = setSqlLimit(queryString, userRole);

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);
		return clueDao.queryList(queryString);
	}

	public List<Clue> getCluesByType(int con, String convalue,
			String starttime, String endtime, int ctype, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Clue mo where  mo.ctype=" + ctype;

		queryString = setSqlLimit(queryString, userRole);

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);
		return clueDao.queryList(queryString);
	}

	public List<Clue> getCluesByTypeAndHandleState(int con, String convalue,
			String starttime, String endtime, int ctype, int state,
			UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Clue mo where mo.ctype=" + ctype
		+ " and mo.handleState=" + state;

		queryString = setSqlLimit(queryString, userRole);

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);
		return clueDao.queryList(queryString);
	}

	public List<Clue> getCluesByUserRole(int con, String convalue,
			String starttime, String endtime, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Clue mo where  1=1 ";

		queryString = setSqlLimit(queryString, userRole);

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);
		return clueDao.queryList(queryString);
	}

	public List<Clue> getOutOfTimeCluesByType(int con, String convalue,
			String starttime, String endtime, int ctype, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Clue mo where mo.isOutOfTime=1 and mo.ctype=" + ctype;

		queryString = setSqlLimit(queryString, userRole);

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);
		return clueDao.queryList(queryString);
	}

	public List<Clue> getOutOfTimeCluesByUserRole(int con, String convalue,
			String starttime, String endtime, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Clue mo where  mo.isOutOfTime=1 ";

		queryString = setSqlLimit(queryString, userRole);

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);
		return clueDao.queryList(queryString);
	}
	
	
	public List<Clue> getNewClueByUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Clue mo where  mo.isNew=1  and mo.handleState=1 ";

		queryString = setSqlLimit(queryString, userRole);

		return clueDao.queryList(queryString);
	}

	// 设置sql语句 关于权限分配
	private String setSqlLimit(String queryString, UserRole userRole) {

		if (userRole.getUserLimit() != 2) {
			// 用户所在机构不为空
			String cids = "";
			if (userRole != null && userRole.getUnit() != null
					&& userRole.getUnit().getCids() != null) {
				cids = userRole.getUnit().getPids().replace(" ", "");
				queryString = setSqlCids(queryString, cids);
			} else {
				queryString += " and mo.id in (0)";
			}
		}
		return queryString;

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

	private String setSqlCids(String queryString, String cids) {
		// TODO Auto-generated method stub
		// 用户所在机构不为空
		if (cids != "" && !cids.equals(",")) {
			String lastChar = cids.substring(cids.length() - 1, cids.length());
			if (lastChar.equals(",")) {
				cids = cids.substring(0, cids.length() - 1);
			}
			queryString += " and mo.id in (" + cids + ")";
		} else {
			queryString += " and mo.id in (0)";
		}
		return queryString;
	}

}
