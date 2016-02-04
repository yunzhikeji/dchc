package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IGuiltSafeguardManDao;
import com.yz.model.GuiltSafeguardMan;
import com.yz.model.UserRole;
import com.yz.service.IGuiltSafeguardManService;

@Component("guiltSafeguardManService")
public class GuiltSafeguardManServiceImp implements IGuiltSafeguardManService {
	private IGuiltSafeguardManDao guiltSafeguardManDao;
	public IGuiltSafeguardManDao getGuiltSafeguardManDao() {
		return guiltSafeguardManDao;
	}
	@Resource
	public void setGuiltSafeguardManDao(IGuiltSafeguardManDao guiltSafeguardManDao) {
		this.guiltSafeguardManDao = guiltSafeguardManDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGuiltSafeguardManServiceImp#add(com.yz.model.GuiltSafeguardMan)
	 */
	public void add(GuiltSafeguardMan guiltSafeguardMan) throws Exception {
		guiltSafeguardManDao.save(guiltSafeguardMan);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGuiltSafeguardManServiceImp#delete(com.yz.model.GuiltSafeguardMan)
	 */
	public void delete(GuiltSafeguardMan guiltSafeguardMan) {
		guiltSafeguardManDao.delete(guiltSafeguardMan);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGuiltSafeguardManServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		guiltSafeguardManDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGuiltSafeguardManServiceImp#update(com.yz.model.GuiltSafeguardMan)
	 */
	public void update(GuiltSafeguardMan guiltSafeguardMan) {
		guiltSafeguardManDao.update(guiltSafeguardMan);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGuiltSafeguardManServiceImp#getGuiltSafeguardMans()
	 */
	public List<GuiltSafeguardMan> getGuiltSafeguardMans() {
		return guiltSafeguardManDao.getGuiltSafeguardMans();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGuiltSafeguardManServiceImp#loadById(int)
	 */
	public GuiltSafeguardMan loadById(int id) {
		return guiltSafeguardManDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGuiltSafeguardManServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGuiltSafeguardManServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole,int type, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from GuiltSafeguardMan mo where 1=1 ";
		Object[] p = null;
		
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.number like ? "; 
			}
			if(con==2){
				queryString += "and mo.idcard like ? "; 
			}
			if(con==2){
				queryString += "and mo.userRole.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		if(type!=0){
			queryString += " and mo.type ="+type;
		}
		if(queryState!=0){
			queryString += " and mo.handleState ="+queryState;
		}
		if(starttime!=null&&!starttime.equals("")){
			queryString += " and mo.joinDate>='"+starttime+"'";
		}
		if(endtime!=null&&!endtime.equals("")){
			queryString += " and mo.joinDate<='"+endtime+"'";
		}
		return guiltSafeguardManDao.getUniqueResult(queryString,p);
	}
	public GuiltSafeguardMan getGuiltSafeguardManByGuiltSafeguardManname(String guiltSafeguardManname) {
		String queryString="from GuiltSafeguardMan mo where mo.name=:guiltSafeguardManname";
		String[] paramNames=new String[]{"guiltSafeguardManname"};
		Object[] values=new Object[]{guiltSafeguardManname};
		return guiltSafeguardManDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGuiltSafeguardManServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<GuiltSafeguardMan> queryList(int con, String convalue, UserRole userRole, int page, int size,int type, int queryState, String starttime, String endtime) {
		String queryString = "from GuiltSafeguardMan mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.number like ? "; 
			}
			if(con==2){
				queryString += "and mo.idcard like ? "; 
			}
			if(con==2){
				queryString += "and mo.userRole.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		if(type!=0){
			queryString += " and mo.type ="+type;
		}
		if(queryState!=0){
			queryString += " and mo.handleState ="+queryState;
		}
		if(starttime!=null&&!starttime.equals("")){
			queryString += " and mo.joinDate>='"+starttime+"'";
		}
		if(endtime!=null&&!endtime.equals("")){
			queryString += " and mo.joinDate<='"+endtime+"'";
		}
		return guiltSafeguardManDao.pageList(queryString,p,page,size);
	}

	public GuiltSafeguardMan getGuiltSafeguardManById(Integer upguiltSafeguardManid) {
		// TODO Auto-generated method stub
		return guiltSafeguardManDao.getGuiltSafeguardManById(upguiltSafeguardManid);
	}
}
