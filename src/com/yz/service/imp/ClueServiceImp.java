package com.yz.service.imp;

import com.yz.dao.ClueDao;
import com.yz.dao.UnitDao;
import com.yz.model.Clue;
import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.ClueService;
import com.yz.util.IdsOperator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("clueService")
public class ClueServiceImp extends RoleServiceImp implements ClueService {


	@Resource
	private ClueDao clueDao;
	@Resource
	private UnitDao unitDao;


	public void add(Clue clue) throws Exception {

		changeUnitByUserRoleAndIdsOperator(clue.getUserRole(), new IdsOperator(clue.getId() + "", 1));
		clueDao.save(clue);
	}

	public void delete(Clue clue) {


		changeUnitByUserRoleAndIdsOperator(clue.getUserRole(), new IdsOperator(clue.getId() + "", -1));
		clueDao.delete(clue);
	}


	public void deleteById(int id) {

		clueDao.deleteById(id);
	}

	public void update(Clue clue) {

		clueDao.update(clue);

	}

	public List<Clue> getClues() {
		return clueDao.getClues();
	}

	public Clue loadById(int id) {
		return clueDao.loadById(id);
	}

	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}

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
			p = new Object[]{'%' + convalue + '%'};
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
		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		return clueDao.getUniqueResult(queryString, p);
	}

	public Clue getClueByCluename(String cluename) {
		String queryString = "from Clue mo where mo.name=:cluename";
		String[] paramNames = new String[]{"cluename"};
		Object[] values = new Object[]{cluename};
		return clueDao.queryByNamedParam(queryString, paramNames, values);
	}

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
			p = new Object[]{'%' + convalue + '%'};
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
		queryString = assembleLimitSqlByUserRole(queryString, userRole);
		return clueDao.pageList(queryString, p, page, size);
	}


	public List<Clue> getCluesByType(int con, String convalue,
									 String starttime, String endtime, int ctype, UserRole userRole) {
		String queryString = "from Clue mo where  mo.ctype=" + ctype;

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);
		return clueDao.queryList(queryString);
	}

	public List<Clue> getCluesByTypeAndHandleState(int con, String convalue,
												   String starttime, String endtime, int ctype, int state,
												   UserRole userRole) {
		String queryString = "from Clue mo where mo.ctype=" + ctype
				+ " and mo.handleState=" + state;

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);
		return clueDao.queryList(queryString);
	}


	public List<Clue> getOutOfTimeCluesByType(int con, String convalue,
											  String starttime, String endtime, int ctype, UserRole userRole) {
		String queryString = "from Clue mo where mo.isOutOfTime=1 and mo.ctype="
				+ ctype;

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);
		return clueDao.queryList(queryString);
	}


	public List<Clue> getNewClueByUserRole(UserRole userRole) {
		String queryString = "from Clue mo where  mo.isNew=1  and mo.handleState=1 ";

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		return clueDao.queryList(queryString);
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
			if (con == 3) {
				queryString += " and mo.userRole.unit.name like  '%" + convalue
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

	protected String getObjectIds(UserRole userRole) {
		return userRole.getUnit().getCids();
	}

	protected void changeUnitIds(Unit unit) {
		unitDao.update(unit);
	}

}
