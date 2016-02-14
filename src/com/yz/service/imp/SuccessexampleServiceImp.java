package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.ISuccessexampleDao;
import com.yz.model.Successexample;
import com.yz.model.UserRole;
import com.yz.service.ISuccessexampleService;
@Component("successexampleService")
public class SuccessexampleServiceImp implements ISuccessexampleService {
	private ISuccessexampleDao successexampleDao;
	public ISuccessexampleDao getSuccessexampleDao() {
		return successexampleDao;
	}
	@Resource
	public void setSuccessexampleDao(ISuccessexampleDao successexampleDao) {
		this.successexampleDao = successexampleDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISuccessexampleServiceImp#add(com.yz.model.Successexample)
	 */
	public void add(Successexample successexample) throws Exception {
		successexampleDao.save(successexample);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISuccessexampleServiceImp#delete(com.yz.model.Successexample)
	 */
	public void delete(Successexample successexample) {
		successexampleDao.delete(successexample);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISuccessexampleServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		successexampleDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISuccessexampleServiceImp#update(com.yz.model.Successexample)
	 */
	public void update(Successexample successexample) {
		successexampleDao.update(successexample);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISuccessexampleServiceImp#getSuccessexamples()
	 */
	public List<Successexample> getSuccessexamples() {
		return successexampleDao.getSuccessexamples();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISuccessexampleServiceImp#loadById(int)
	 */
	public Successexample loadById(int id) {
		return successexampleDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISuccessexampleServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISuccessexampleServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole) {
		String queryString = "select count(*) from Successexample mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		
		return successexampleDao.getUniqueResult(queryString,p);
	}
	public Successexample getSuccessexampleBySuccessexamplename(String successexamplename) {
		String queryString="from Successexample mo where mo.name=:successexamplename";
		String[] paramNames=new String[]{"successexamplename"};
		Object[] values=new Object[]{successexamplename};
		return successexampleDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISuccessexampleServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Successexample> queryList(int con, String convalue, UserRole userRole, int page, int size) {
		String queryString = "from Successexample mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
				
			}
			if(con==2){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return successexampleDao.pageList(queryString,p,page,size);
	}

	public Successexample getSuccessexampleById(Integer upsuccessexampleid) {
		// TODO Auto-generated method stub
		return successexampleDao.getSuccessexampleById(upsuccessexampleid);
	}
	public int getTotalCount(int con, String convalue, UserRole userRoleo,
			String starttime, String endtime) {
		// TODO Auto-generated method stub
		String queryString = "select count(*) from Successexample mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.title like ? "; 
			}
			if(con==2){
				queryString += "and mo.releaseName like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		if(starttime!=null&&!starttime.equals("")){
			queryString += " and mo.releaseTime>='"+starttime+"'";
		}
		if(endtime!=null&&!endtime.equals("")){
			queryString += " and mo.releaseTime<='"+endtime+"'";
		}
		return successexampleDao.getUniqueResult(queryString,p);
	}
	public List<Successexample> queryList(int con, String convalue,
			UserRole userRoleo, int page, int size, String starttime,
			String endtime) {
		String queryString = "from Successexample mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.title like ? "; 
			}
			if(con==2){
				queryString += "and mo.releaseName like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		if(starttime!=null&&!starttime.equals("")){
			queryString += " and mo.releaseTime>='"+starttime+"'";
		}
		if(endtime!=null&&!endtime.equals("")){
			queryString += " and mo.releaseTime<='"+endtime+"'";
		}
		return successexampleDao.pageList(queryString,p,page,size);
	}
}
