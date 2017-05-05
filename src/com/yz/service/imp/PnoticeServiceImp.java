package com.yz.service.imp;

import com.yz.dao.PnoticeDao;
import com.yz.model.Pnotice;
import com.yz.model.UserRole;
import com.yz.service.PnoticeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pnoticeService")
public class PnoticeServiceImp implements PnoticeService {

	@Resource
	private PnoticeDao pnoticeDao;

	public void add(Pnotice pnotice) throws Exception {
		pnoticeDao.save(pnotice);
	}

	public void delete(Pnotice pnotice) {
		pnoticeDao.delete(pnotice);
	}

	public void deleteById(int id) {
		pnoticeDao.deleteById(id);
	}

	public void update(Pnotice pnotice) {
		pnoticeDao.update(pnotice);
	}

	public List<Pnotice> getPnotices() {
		return pnoticeDao.getPnotices();
	}

	public Pnotice loadById(int id) {
		return pnoticeDao.loadById(id);
	}

	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size : (totalCount / size + 1);
	}

	public int getTotalCount(int con, String convalue, UserRole userRole, int type, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from Pnotice mo where 1=1 ";
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
				queryString += "and mo.userRole.name like ? ";
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
		return pnoticeDao.getUniqueResult(queryString, p);
	}

	public List<Pnotice> queryList(int con, String convalue, UserRole userRole, int page, int size, int type, int queryState, String starttime, String endtime) {
		String queryString = "from Pnotice mo where 1=1 ";
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
				queryString += "and mo.userRole.name like ? ";
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
		return pnoticeDao.pageList(queryString, p, page, size);
	}

	public int getTotalCount(int con, String convalue, UserRole currentUserRole,
							 String starttime, String endtime) {
		String queryString = "select count(*) from Pnotice mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.title like ? ";
			}
			if (con == 2) {
				queryString += "and mo.author like ? ";
			}
			p = new Object[]{'%' + convalue + '%'};
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.releaseTime>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.releaseTime<='" + endtime + "'";
		}
		return pnoticeDao.getUniqueResult(queryString, p);
	}

	public List<Pnotice> queryList(int con, String convalue,
								   UserRole currentUserRole, int page, int size, String starttime,
								   String endtime) {
		String queryString = "from Pnotice mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.title like ? ";
			}
			if (con == 2) {
				queryString += "and mo.author like ? ";
			}
			p = new Object[]{'%' + convalue + '%'};
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.releaseTime>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.releaseTime<='" + endtime + "'";
		}
		return pnoticeDao.pageList(queryString, p, page, size);
	}
}
