package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.yz.dao.IUserRoleDao;
import com.yz.model.UserRole;
import com.yz.service.IUserRoleService;
@Component("userRoleService")
public class UserRoleServiceImp implements IUserRoleService {
	private IUserRoleDao userRoleDao;
	public IUserRoleDao getUserRoleDao() {
		return userRoleDao;
	}
	@Resource
	public void setUserRoleDao(IUserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUserRoleServiceImp#add(com.yz.model.UserRole)
	 */
	public void add(UserRole userRole) throws Exception {
		userRoleDao.save(userRole);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUserRoleServiceImp#delete(com.yz.model.UserRole)
	 */
	public void delete(UserRole userRole) {
		userRoleDao.delete(userRole);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUserRoleServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		userRoleDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUserRoleServiceImp#update(com.yz.model.UserRole)
	 */
	public void update(UserRole userRole) {
		userRoleDao.update(userRole);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUserRoleServiceImp#getUserRoles()
	 */
	public List<UserRole> getUserRoles() {
		return userRoleDao.getUserRoles();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUserRoleServiceImp#loadById(int)
	 */
	public UserRole loadById(int id) {
		return userRoleDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUserRoleServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUserRoleServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole) {
		String queryString = "select count(*) from UserRole mo where 1=1 and mo.id!="+userRole.getId();
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.unit.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.realname like ? "; 
				
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return userRoleDao.getUniqueResult(queryString,p);
	}
	public UserRole getUserRoleByUserRolename(String username) {
		String queryString="from UserRole mo where mo.username=:username";
		String[] paramNames=new String[]{"username"};
		Object[] values=new Object[]{username};
		return userRoleDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUserRoleServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<UserRole> queryList(int con, String convalue, UserRole userRole, int page, int size) {
		String queryString = "from UserRole mo where 1=1 and mo.id!="+userRole.getId();
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.unit.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.realname like ? "; 
				
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return userRoleDao.pageList(queryString,p,page,size);
	}
	//用户登录
	public UserRole userRolelogin(String username, String password) {
		String queryString="from UserRole mo where mo.username=:username and mo.password=:password";
		String[] paramNames=new String[]{"username","password"};
		Object[] values=new Object[]{username,password};
		return userRoleDao.queryByNamedParam(queryString,paramNames,values);
	} 
	
	//webservice部分
	/**
	 * 用户登录
	 */
/*	@GET
	@Path("/userRolelogin/{userRolename}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserRolemo> userRoleLogin(@PathParam("userRolename") String userRolename,@PathParam("password") String password){
		List<UserRolemo> userRolemos = new ArrayList<UserRolemo>();
		
		String queryString="from UserRole mo where mo.userRolename=:userRolename and mo.password=:password";
		String[] paramNames=new String[]{"userRolename","password"};
		Object[] values=new Object[]{userRolename,password};
		UserRole loginer=userRoleDao.queryByNamedParam(queryString, paramNames, values);
		if(loginer!=null){
			UserRolemo userRolemo = new UserRolemo();
			userRolemo.setUserRolename(loginer.getUserRolename());
			userRolemos.add(userRolemo);
//			islogin=true;
		}
		return userRolemos;
		
	}*/
	/**
	 * 用户修改密码
	 */
	@GET
	@Path("/updatepwd/{userRolename}/{oldpwd}/{newpwd}")
	@Produces(MediaType.APPLICATION_JSON)
	public int updatePwd(@PathParam("userRolename") String userRolename,@PathParam("oldpwd") String oldpwd,@PathParam("newpwd") String newpwd){
		int logined=0;
		String queryString="update UserRole mo set mo.password=? where mo.userRolename=? and mo.password=?";
		Object[] p=new Object[]{newpwd,userRolename,oldpwd};
		logined=userRoleDao.updateUserRoleByhql(queryString,p);
		return logined;
		
	}
}
