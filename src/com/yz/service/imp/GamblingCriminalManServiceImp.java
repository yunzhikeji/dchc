package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IGamblingCriminalManDao;
import com.yz.model.GamblingCriminalMan;
import com.yz.model.UserRole;
import com.yz.service.IGamblingCriminalManService;

@Component("gamblingCriminalManService")
public class GamblingCriminalManServiceImp implements IGamblingCriminalManService {
	private IGamblingCriminalManDao gamblingCriminalManDao;
	public IGamblingCriminalManDao getGamblingCriminalManDao() {
		return gamblingCriminalManDao;
	}
	@Resource
	public void setGamblingCriminalManDao(IGamblingCriminalManDao gamblingCriminalManDao) {
		this.gamblingCriminalManDao = gamblingCriminalManDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGamblingCriminalManServiceImp#add(com.yz.model.GamblingCriminalMan)
	 */
	public void add(GamblingCriminalMan gamblingCriminalMan) throws Exception {
		gamblingCriminalManDao.save(gamblingCriminalMan);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGamblingCriminalManServiceImp#delete(com.yz.model.GamblingCriminalMan)
	 */
	public void delete(GamblingCriminalMan gamblingCriminalMan) {
		gamblingCriminalManDao.delete(gamblingCriminalMan);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGamblingCriminalManServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		gamblingCriminalManDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGamblingCriminalManServiceImp#update(com.yz.model.GamblingCriminalMan)
	 */
	public void update(GamblingCriminalMan gamblingCriminalMan) {
		gamblingCriminalManDao.update(gamblingCriminalMan);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGamblingCriminalManServiceImp#getGamblingCriminalMans()
	 */
	public List<GamblingCriminalMan> getGamblingCriminalMans() {
		return gamblingCriminalManDao.getGamblingCriminalMans();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGamblingCriminalManServiceImp#loadById(int)
	 */
	public GamblingCriminalMan loadById(int id) {
		return gamblingCriminalManDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGamblingCriminalManServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGamblingCriminalManServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole,int type, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from GamblingCriminalMan mo where 1=1 ";
		Object[] p = null;
		
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.person.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.person.number like ? "; 
			}
			if(con==3){
				queryString += "and mo.person.idcard like ? "; 
			}
			if(con==4){
				queryString += "and mo.person.userRole.realname like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		if(type!=0){
			queryString += " and mo.type ="+type;
		}
		if(queryState!=0){
			queryString += " and mo.person.handleState ="+queryState;
		}
		if(starttime!=null&&!starttime.equals("")){
			queryString += " and mo.person.joinDate>='"+starttime+"'";
		}
		if(endtime!=null&&!endtime.equals("")){
			queryString += " and mo.person.joinDate<='"+endtime+"'";
		}
		return gamblingCriminalManDao.getUniqueResult(queryString,p);
	}
	public GamblingCriminalMan getGamblingCriminalManByGamblingCriminalManname(String gamblingCriminalManname) {
		String queryString="from GamblingCriminalMan mo where mo.name=:gamblingCriminalManname";
		String[] paramNames=new String[]{"gamblingCriminalManname"};
		Object[] values=new Object[]{gamblingCriminalManname};
		return gamblingCriminalManDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IGamblingCriminalManServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<GamblingCriminalMan> queryList(int con, String convalue, UserRole userRole, int page, int size,int type, int queryState, String starttime, String endtime) {
		String queryString = "from GamblingCriminalMan mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.person.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.person.number like ? "; 
			}
			if(con==3){
				queryString += "and mo.person.idcard like ? "; 
			}
			if(con==4){
				queryString += "and mo.person.userRole.realname like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		if(type!=0){
			queryString += " and mo.type ="+type;
		}
		if(queryState!=0){
			queryString += " and mo.person.handleState ="+queryState;
		}
		if(starttime!=null&&!starttime.equals("")){
			queryString += " and mo.person.joinDate>='"+starttime+"'";
		}
		if(endtime!=null&&!endtime.equals("")){
			queryString += " and mo.person.joinDate<='"+endtime+"'";
		}
		return gamblingCriminalManDao.pageList(queryString,p,page,size);
	}

	public GamblingCriminalMan getGamblingCriminalManById(Integer upgamblingCriminalManid) {
		// TODO Auto-generated method stub
		return gamblingCriminalManDao.getGamblingCriminalManById(upgamblingCriminalManid);
	}
}
