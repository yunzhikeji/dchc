package com.yz.service.imp;

import com.yz.dao.SuccessexampleDao;
import com.yz.model.Successexample;
import com.yz.model.UserRole;
import com.yz.service.SuccessexampleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("successexampleService")
public class SuccessexampleServiceImp implements SuccessexampleService {


	@Resource
	private SuccessexampleDao successexampleDao;

	public void add(Successexample successexample) throws Exception {
		successexampleDao.save(successexample);
	}

	public void delete(Successexample successexample) {
		successexampleDao.delete(successexample);
	}

	public void deleteById(int id) {
		successexampleDao.deleteById(id);
	}

	public void update(Successexample successexample) {
		successexampleDao.update(successexample);
	}

	public List<Successexample> getSuccessexamples() {
		return successexampleDao.getSuccessexamples();
	}

	public Successexample loadById(int id) {
		return successexampleDao.loadById(id);
	}

	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size : (totalCount / size + 1);
	}

	public int getTotalCount(int con, String convalue, UserRole userRole) {
		String queryString = "select count(*) from Successexample mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.number like ? ";
			}
			p = new Object[]{'%' + convalue + '%'};
		}

		return successexampleDao.getUniqueResult(queryString, p);
	}


	public List<Successexample> queryList(int con, String convalue, UserRole userRole, int page, int size) {
		String queryString = "from Successexample mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";

			}
			if (con == 2) {
				queryString += "and mo.number like ? ";
			}
			p = new Object[]{'%' + convalue + '%'};
		}
		return successexampleDao.pageList(queryString, p, page, size);
	}

	public int getTotalCount(int con, String convalue, UserRole currentUserRole,
							 String starttime, String endtime) {
		String queryString = "select count(*) from Successexample mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.title like ? ";
			}
			if (con == 2) {
				queryString += "and mo.releaseName like ? ";
			}
			p = new Object[]{'%' + convalue + '%'};
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.releaseTime>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.releaseTime<='" + endtime + "'";
		}
		return successexampleDao.getUniqueResult(queryString, p);
	}

	public List<Successexample> queryList(int con, String convalue,
										  UserRole currentUserRole, int page, int size, String starttime,
										  String endtime) {
		String queryString = "from Successexample mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.title like ? ";
			}
			if (con == 2) {
				queryString += "and mo.releaseName like ? ";
			}
			p = new Object[]{'%' + convalue + '%'};
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.releaseTime>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.releaseTime<='" + endtime + "'";
		}
		return successexampleDao.pageList(queryString, p, page, size);
	}
}
