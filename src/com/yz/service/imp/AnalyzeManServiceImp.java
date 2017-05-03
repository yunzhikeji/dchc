package com.yz.service.imp;

import com.yz.dao.AnalyzeManDao;
import com.yz.model.AnalyzeMan;
import com.yz.model.UserRole;
import com.yz.service.AnalyzeManService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("analyzeManService")
public class AnalyzeManServiceImp implements AnalyzeManService {

	@Resource
	private AnalyzeManDao analyzeManDao;


	public void add(AnalyzeMan analyzeMan) throws Exception {
		analyzeManDao.save(analyzeMan);
	}

	public void delete(AnalyzeMan analyzeMan) {
		analyzeManDao.delete(analyzeMan);
	}

	public void deleteById(int id) {
		analyzeManDao.deleteById(id);
	}

	public void update(AnalyzeMan analyzeMan) {
		analyzeManDao.update(analyzeMan);
	}

	public List<AnalyzeMan> getDisappearMen() {
		return analyzeManDao.getDisappearMen();
	}

	public AnalyzeMan loadById(int id) {
		return analyzeManDao.loadById(id);
	}

	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}

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
		return analyzeManDao.getUniqueResult(queryString, p);
	}

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
		return analyzeManDao.pageList(queryString, p, page, size);
	}
}
