package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IDisappearManDao;
import com.yz.model.DisappearMan;
import com.yz.model.UserRole;
import com.yz.service.IDisappearManService;
@Component("disappearmanService")
public class DisappearManServiceImp implements IDisappearManService {
	private IDisappearManDao disappearmanDao;
	public IDisappearManDao getDisappearManDao() {
		return disappearmanDao;
	}
	@Resource
	public void setDisappearManDao(IDisappearManDao disappearmanDao) {
		this.disappearmanDao = disappearmanDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#add(com.yz.model.Person)
	 */
	public void add(DisappearMan disappearman) throws Exception {
		disappearmanDao.save(disappearman);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#delete(com.yz.model.Person)
	 */
	public void delete(DisappearMan disappearman) {
		disappearmanDao.delete(disappearman);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		disappearmanDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#update(com.yz.model.Person)
	 */
	public void update(DisappearMan disappearman) {
		disappearmanDao.update(disappearman);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#getPersons()
	 */
	public List<DisappearMan> getDisappearMen() {
		return disappearmanDao.getDisappearMen();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#loadById(int)
	 */
	public DisappearMan loadById(int id) {
		return disappearmanDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole,int type, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from DisappearMan mo where 1=1 ";
		Object[] p = null;
		
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.number like ? "; 
			}
			if(con==3){
				queryString += "and mo.idcard like ? "; 
			}
			if(con==4){
				queryString += "and mo.userRole.realname like ? "; 
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
		return disappearmanDao.getUniqueResult(queryString,p);
	}
//	public DisappearMan getDisappearManByDisappearManname(String disappearname) {
//		String queryString="from Person mo where mo.name=:disappearname";
//		String[] paramNames=new String[]{"personname"};
//		Object[] values=new Object[]{disappearname};
//		//return disappearmanDao.queryByNamedParam(queryString,paramNames,values);
//	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<DisappearMan> queryList(int con, String convalue, UserRole userRole, int page, int size,int type, int queryState, String starttime, String endtime) {
		String queryString = "from DisappearMan mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.number like ? "; 
			}
			if(con==3){
				queryString += "and mo.idcard like ? "; 
			}
			if(con==4){
				queryString += "and mo.userRole.realname like ? "; 
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
		return disappearmanDao.pageList(queryString,p,page,size);
	}

	public DisappearMan getDisappearManById(Integer updisappearmanid) {
		// TODO Auto-generated method stub
		return disappearmanDao.getDisappearManById(updisappearmanid);
	}
}