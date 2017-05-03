package com.yz.service.imp;

import com.yz.dao.OtherpersonDao;
import com.yz.model.Otherperson;
import com.yz.model.UserRole;
import com.yz.service.OtherpersonService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("otherpersonService")
public class OtherpersonServiceImp implements OtherpersonService {

	@Resource
	private OtherpersonDao otherpersonDao;

	public void add(Otherperson otherperson) throws Exception {
		otherpersonDao.save(otherperson);
	}

	public void delete(Otherperson otherperson) {
		otherpersonDao.delete(otherperson);
	}

	public void deleteById(int id) {
		otherpersonDao.deleteById(id);
	}

	public void update(Otherperson otherperson) {
		otherpersonDao.update(otherperson);
	}

	public List<Otherperson> getOtherpersons() {
		return otherpersonDao.getOtherpersons();
	}

	public Otherperson loadById(int id) {
		return otherpersonDao.loadById(id);
	}

	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size : (totalCount / size + 1);
	}

	public int getTotalCount(int con, String convalue, UserRole userRole) {
		String queryString = "select count(*) from Otherperson mo where 1=1 ";
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

		return otherpersonDao.getUniqueResult(queryString, p);
	}

	public Otherperson getOtherpersonByOtherpersonname(String otherpersonname) {
		String queryString = "from Otherperson mo where mo.name=:otherpersonname";
		String[] paramNames = new String[]{"otherpersonname"};
		Object[] values = new Object[]{otherpersonname};
		return otherpersonDao.queryByNamedParam(queryString, paramNames, values);
	}

	public List<Otherperson> queryList(int con, String convalue, UserRole userRole, int page, int size) {
		String queryString = "from Otherperson mo where 1=1 ";
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
		return otherpersonDao.pageList(queryString, p, page, size);
	}

	public List<Otherperson> getOtherpersonByOtype(int otype, Integer pid) {
		String queryString = "from Otherperson mo where 1=1 and mo.otype=? and mo.person.id=? ";
		Object[] p = new Object[]{otype, pid};
		return otherpersonDao.getObjectsByCondition(queryString, p);
	}

	public List<Otherperson> getInjurycaseOtherpersonByOtype(int otype, int inid) {
		String queryString = "from Otherperson mo where 1=1 and mo.otype=? and mo.injurycase.id=? ";
		Object[] p = new Object[]{otype, inid};
		return otherpersonDao.getObjectsByCondition(queryString, p);
	}
}
