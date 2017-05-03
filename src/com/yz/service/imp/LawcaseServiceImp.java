package com.yz.service.imp;

import com.yz.dao.LawcaseDao;
import com.yz.model.Lawcase;
import com.yz.model.UserRole;
import com.yz.service.LawcaseService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("lawcaseService")
public class LawcaseServiceImp implements LawcaseService {

	@Resource
	private LawcaseDao lawcaseDao;

	public void add(Lawcase lawcase) throws Exception {
		lawcaseDao.save(lawcase);
	}

	public void delete(Lawcase lawcase) {
		lawcaseDao.delete(lawcase);
	}

	public void deleteById(int id) {
		lawcaseDao.deleteById(id);
	}

	public void update(Lawcase lawcase) {
		lawcaseDao.update(lawcase);
	}

	public List<Lawcase> getLawcases() {
		return lawcaseDao.getLawcases();
	}

	public Lawcase loadById(int id) {
		return lawcaseDao.loadById(id);
	}

	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size : (totalCount / size + 1);
	}

	public int getTotalCount(int con, String convalue, UserRole userRole) {
		String queryString = "select count(*) from Lawcase mo where 1=1 ";
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

		return lawcaseDao.getUniqueResult(queryString, p);
	}


	public List<Lawcase> queryList(int con, String convalue, UserRole userRole, int page, int size) {
		String queryString = "from Lawcase mo where 1=1 ";
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
		return lawcaseDao.pageList(queryString, p, page, size);
	}

}
