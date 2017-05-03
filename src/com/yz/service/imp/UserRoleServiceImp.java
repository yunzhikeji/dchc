package com.yz.service.imp;

import com.yz.dao.UserRoleDao;
import com.yz.model.UserRole;
import com.yz.service.UserRoleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("userRoleService")
public class UserRoleServiceImp implements UserRoleService {

	@Resource
	private UserRoleDao userRoleDao;


	public void add(UserRole userRole) throws Exception {
		userRoleDao.save(userRole);
	}

	public void delete(UserRole userRole) {
		userRoleDao.delete(userRole);
	}

	public void deleteById(int id) {
		userRoleDao.deleteById(id);
	}

	public void update(UserRole userRole) {
		userRoleDao.update(userRole);
	}

	public List<UserRole> getUserRoles() {
		return userRoleDao.getUserRoles();
	}

	public UserRole loadById(int id) {
		return userRoleDao.loadById(id);
	}

	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size : (totalCount / size + 1);
	}

	public int getTotalCount(int con, String convalue, UserRole userRole) {
		String queryString = "select count(*) from UserRole mo where 1=1 and mo.id!=" + userRole.getId();
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.unit.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.realname like ? ";

			}
			if (con == 3) {
				queryString += "and mo.number like ? ";
			}
			p = new Object[]{'%' + convalue + '%'};
		}
		return userRoleDao.getUniqueResult(queryString, p);
	}

	public UserRole getUserRoleByUsername(String username) {
		String queryString = "from UserRole mo where mo.username=:username";
		String[] paramNames = new String[]{"username"};
		Object[] values = new Object[]{username};
		return userRoleDao.queryByNamedParam(queryString, paramNames, values);
	}

	public List<UserRole> queryList(int con, String convalue, UserRole userRole, int page, int size) {
		String queryString = "from UserRole mo where 1=1 and mo.id!=" + userRole.getId();
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.unit.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.realname like ? ";

			}
			if (con == 3) {
				queryString += "and mo.number like ? ";
			}
			p = new Object[]{'%' + convalue + '%'};
		}
		return userRoleDao.pageList(queryString, p, page, size);
	}

	//用户登录
	public UserRole getUserRoleByUsernameAndPassword(
			String username, String
			password)

	{
		String queryString = "from UserRole mo where mo.username=:username and mo.password=:password";
		String[] paramNames = new String[]{"username", "password"};
		Object[] values = new Object[]{username, password};
		return userRoleDao.queryByNamedParam(queryString, paramNames, values);
	}

	public UserRole getUserRoleById(Integer id) {
		String queryString = "from UserRole mo where mo.id=:id";
		String[] paramNames = new String[]{"id"};
		Object[] values = new Object[]{id};
		return userRoleDao.queryByNamedParam(queryString, paramNames, values);
	}

	public UserRole getUserRoleByCardid(String cardid) {
		String queryString = "from UserRole mo where mo.cardid=:cardid";
		String[] paramNames = new String[]{"cardid"};
		Object[] values = new Object[]{cardid};
		return userRoleDao.queryByNamedParam(queryString, paramNames, values);
	}

}
