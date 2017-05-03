package com.yz.service.imp;

import com.yz.dao.ContrastManDao;
import com.yz.model.ContrastMan;
import com.yz.model.UserRole;
import com.yz.service.ContrastManService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("contrastManService")
public class ContrastManServiceImp implements ContrastManService {

	@Resource
	private ContrastManDao contrastManDao;

	public void add(ContrastMan contrastMan) throws Exception {
		contrastManDao.save(contrastMan);
	}

	public void delete(ContrastMan contrastMan) {
		contrastManDao.delete(contrastMan);
	}

	public void deleteById(int id) {
		contrastManDao.deleteById(id);
	}

	public void update(ContrastMan contrastMan) {
		contrastManDao.update(contrastMan);
	}

	public List<ContrastMan> getContrastMans() {
		return contrastManDao.getContrastMans();
	}

	public ContrastMan loadById(int id) {
		return contrastManDao.loadById(id);
	}

	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size : (totalCount / size + 1);
	}

	public int getTotalCount(int con, String convalue, UserRole userRole, int type, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from ContrastMan mo where 1=1 ";
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
		return contrastManDao.getUniqueResult(queryString, p);
	}

	public ContrastMan getContrastManByContrastManname(String contrastManname) {
		String queryString = "from ContrastMan mo where mo.name=:contrastManname";
		String[] paramNames = new String[]{"contrastManname"};
		Object[] values = new Object[]{contrastManname};
		return contrastManDao.queryByNamedParam(queryString, paramNames, values);
	}

	public List<ContrastMan> queryList(int con, String convalue, UserRole userRole, int page, int size, int type, int queryState, String starttime, String endtime) {
		String queryString = "from ContrastMan mo where 1=1 ";
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
		return contrastManDao.pageList(queryString, p, page, size);
	}

}
