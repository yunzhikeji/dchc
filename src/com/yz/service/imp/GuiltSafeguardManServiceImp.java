package com.yz.service.imp;

import com.yz.dao.GuiltSafeguardManDao;
import com.yz.model.GuiltSafeguardMan;
import com.yz.model.UserRole;
import com.yz.service.GuiltSafeguardManService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("guiltSafeguardManService")
public class GuiltSafeguardManServiceImp implements GuiltSafeguardManService {

	@Resource
	private GuiltSafeguardManDao guiltSafeguardManDao;

	public void add(GuiltSafeguardMan guiltSafeguardMan) throws Exception {
		guiltSafeguardManDao.save(guiltSafeguardMan);
	}

	public void delete(GuiltSafeguardMan guiltSafeguardMan) {
		guiltSafeguardManDao.delete(guiltSafeguardMan);
	}

	public void deleteById(int id) {
		guiltSafeguardManDao.deleteById(id);
	}

	public void update(GuiltSafeguardMan guiltSafeguardMan) {
		guiltSafeguardManDao.update(guiltSafeguardMan);
	}

	public List<GuiltSafeguardMan> getGuiltSafeguardMans() {
		return guiltSafeguardManDao.getGuiltSafeguardMans();
	}

	public GuiltSafeguardMan loadById(int id) {
		return guiltSafeguardManDao.loadById(id);
	}

	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size : (totalCount / size + 1);
	}

	public int getTotalCount(int con, String convalue, UserRole userRole, int type, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from GuiltSafeguardMan mo where 1=1 ";
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
		return guiltSafeguardManDao.getUniqueResult(queryString, p);
	}

	public GuiltSafeguardMan getGuiltSafeguardManByGuiltSafeguardManname(String guiltSafeguardManname) {
		String queryString = "from GuiltSafeguardMan mo where mo.name=:guiltSafeguardManname";
		String[] paramNames = new String[]{"guiltSafeguardManname"};
		Object[] values = new Object[]{guiltSafeguardManname};
		return guiltSafeguardManDao.queryByNamedParam(queryString, paramNames, values);
	}

	public List<GuiltSafeguardMan> queryList(int con, String convalue, UserRole userRole, int page, int size, int type, int queryState, String starttime, String endtime) {
		String queryString = "from GuiltSafeguardMan mo where 1=1 ";
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
		return guiltSafeguardManDao.pageList(queryString, p, page, size);
	}

}
