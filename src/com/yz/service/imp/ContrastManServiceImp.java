package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IContrastManDao;
import com.yz.model.ContrastMan;
import com.yz.model.UserRole;
import com.yz.service.IContrastManService;

@Component("contrastManService")
public class ContrastManServiceImp implements IContrastManService {
	private IContrastManDao contrastManDao;
	public IContrastManDao getContrastManDao() {
		return contrastManDao;
	}
	@Resource
	public void setContrastManDao(IContrastManDao contrastManDao) {
		this.contrastManDao = contrastManDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IContrastManServiceImp#add(com.yz.model.ContrastMan)
	 */
	public void add(ContrastMan contrastMan) throws Exception {
		contrastManDao.save(contrastMan);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IContrastManServiceImp#delete(com.yz.model.ContrastMan)
	 */
	public void delete(ContrastMan contrastMan) {
		contrastManDao.delete(contrastMan);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IContrastManServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		contrastManDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IContrastManServiceImp#update(com.yz.model.ContrastMan)
	 */
	public void update(ContrastMan contrastMan) {
		contrastManDao.update(contrastMan);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IContrastManServiceImp#getContrastMans()
	 */
	public List<ContrastMan> getContrastMans() {
		return contrastManDao.getContrastMans();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IContrastManServiceImp#loadById(int)
	 */
	public ContrastMan loadById(int id) {
		return contrastManDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IContrastManServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IContrastManServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole,int type, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from ContrastMan mo where 1=1 ";
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
		return contrastManDao.getUniqueResult(queryString,p);
	}
	public ContrastMan getContrastManByContrastManname(String contrastManname) {
		String queryString="from ContrastMan mo where mo.name=:contrastManname";
		String[] paramNames=new String[]{"contrastManname"};
		Object[] values=new Object[]{contrastManname};
		return contrastManDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IContrastManServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<ContrastMan> queryList(int con, String convalue, UserRole userRole, int page, int size,int type, int queryState, String starttime, String endtime) {
		String queryString = "from ContrastMan mo where 1=1 ";
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
		return contrastManDao.pageList(queryString,p,page,size);
	}

	public ContrastMan getContrastManById(Integer upcontrastManid) {
		// TODO Auto-generated method stub
		return contrastManDao.getContrastManById(upcontrastManid);
	}
}
