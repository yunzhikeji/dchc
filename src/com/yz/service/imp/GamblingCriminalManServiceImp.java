package com.yz.service.imp;

import com.yz.dao.GamblingCriminalManDao;
import com.yz.model.GamblingCriminalMan;
import com.yz.model.UserRole;
import com.yz.service.GamblingCriminalManService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("gamblingCriminalManService")
public class GamblingCriminalManServiceImp implements GamblingCriminalManService {

	@Resource
	private GamblingCriminalManDao gamblingCriminalManDao;


	public void add(GamblingCriminalMan gamblingCriminalMan) throws Exception {
		gamblingCriminalManDao.save(gamblingCriminalMan);
	}

	public void delete(GamblingCriminalMan gamblingCriminalMan) {
		gamblingCriminalManDao.delete(gamblingCriminalMan);
	}

	public void deleteById(int id) {
		gamblingCriminalManDao.deleteById(id);
	}

	public void update(GamblingCriminalMan gamblingCriminalMan) {
		gamblingCriminalManDao.update(gamblingCriminalMan);
	}

	public List<GamblingCriminalMan> getGamblingCriminalMans() {
		return gamblingCriminalManDao.getGamblingCriminalMans();
	}

	public GamblingCriminalMan loadById(int id) {
		return gamblingCriminalManDao.loadById(id);
	}

	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size : (totalCount / size + 1);
	}

	public int getTotalCount(int con, String convalue, UserRole userRole, int type, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from GamblingCriminalMan mo where 1=1 ";
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
			p = new Object[]{'%' + convalue + '%'};
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
		return gamblingCriminalManDao.getUniqueResult(queryString, p);
	}

	public GamblingCriminalMan getGamblingCriminalManByGamblingCriminalManname(String gamblingCriminalManname) {
		String queryString = "from GamblingCriminalMan mo where mo.name=:gamblingCriminalManname";
		String[] paramNames = new String[]{"gamblingCriminalManname"};
		Object[] values = new Object[]{gamblingCriminalManname};
		return gamblingCriminalManDao.queryByNamedParam(queryString, paramNames, values);
	}

	public List<GamblingCriminalMan> queryList(int con, String convalue, UserRole userRole, int page, int size, int type, int queryState, String starttime, String endtime) {
		String queryString = "from GamblingCriminalMan mo where 1=1 ";
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
			p = new Object[]{'%' + convalue + '%'};
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
		return gamblingCriminalManDao.pageList(queryString, p, page, size);
	}

}
