package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.ICommonClueDao;
import com.yz.model.CommonClue;
import com.yz.model.UserRole;
import com.yz.service.ICommonClueService;

@Component("commonClueService")
public class CommonClueServiceImp implements ICommonClueService {
	private ICommonClueDao commonClueDao;
	public ICommonClueDao getCommonClueDao() {
		return commonClueDao;
	}
	@Resource
	public void setCommonClueDao(ICommonClueDao commonClueDao) {
		this.commonClueDao = commonClueDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICommonClueServiceImp#add(com.yz.model.CommonClue)
	 */
	public void add(CommonClue commonClue) throws Exception {
		commonClueDao.save(commonClue);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICommonClueServiceImp#delete(com.yz.model.CommonClue)
	 */
	public void delete(CommonClue commonClue) {
		commonClueDao.delete(commonClue);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICommonClueServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		commonClueDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICommonClueServiceImp#update(com.yz.model.CommonClue)
	 */
	public void update(CommonClue commonClue) {
		commonClueDao.update(commonClue);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICommonClueServiceImp#getCommonClues()
	 */
	public List<CommonClue> getCommonClues() {
		return commonClueDao.getCommonClues();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICommonClueServiceImp#loadById(int)
	 */
	public CommonClue loadById(int id) {
		return commonClueDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICommonClueServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICommonClueServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole,int type, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from CommonClue mo where 1=1 ";
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
		return commonClueDao.getUniqueResult(queryString,p);
	}
	public CommonClue getCommonClueByCommonCluename(String commonCluename) {
		String queryString="from CommonClue mo where mo.name=:commonCluename";
		String[] paramNames=new String[]{"commonCluename"};
		Object[] values=new Object[]{commonCluename};
		return commonClueDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ICommonClueServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<CommonClue> queryList(int con, String convalue, UserRole userRole, int page, int size,int type, int queryState, String starttime, String endtime) {
		String queryString = "from CommonClue mo where 1=1 ";
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
		return commonClueDao.pageList(queryString,p,page,size);
	}

	public CommonClue getCommonClueById(Integer upcommonClueid) {
		// TODO Auto-generated method stub
		return commonClueDao.getCommonClueById(upcommonClueid);
	}
}
