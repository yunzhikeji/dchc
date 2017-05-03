package com.yz.service.imp;

import com.yz.dao.TroubleshootingDao;
import com.yz.model.Troubleshooting;
import com.yz.model.UserRole;
import com.yz.service.TroubleshootingService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("troubleshootingService")
public class TroubleshootingServiceImp implements TroubleshootingService {

	@Resource
	private TroubleshootingDao troubleshootingDao;

	public void add(Troubleshooting troubleshooting) throws Exception {
		troubleshootingDao.save(troubleshooting);
	}

	public void delete(Troubleshooting troubleshooting) {
		troubleshootingDao.delete(troubleshooting);
	}

	public void deleteById(int id) {
		troubleshootingDao.deleteById(id);
	}

	public void update(Troubleshooting troubleshooting) {
		troubleshootingDao.update(troubleshooting);
	}

	public List<Troubleshooting> getTroubleshootings() {
		return troubleshootingDao.getTroubleshootings();
	}

	public Troubleshooting loadById(int id) {
		return troubleshootingDao.loadById(id);
	}

	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size : (totalCount / size + 1);
	}

	public int getTotalCount(int con, String convalue, UserRole userRole) {
		String queryString = "select count(*) from Troubleshooting mo where 1=1 ";
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

		return troubleshootingDao.getUniqueResult(queryString, p);
	}


	public List<Troubleshooting> queryList(int con, String convalue, UserRole userRole, int page, int size) {
		String queryString = "from Troubleshooting mo where 1=1 ";
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
		return troubleshootingDao.pageList(queryString, p, page, size);
	}

}
