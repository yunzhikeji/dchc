package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IPnoticeDao;
import com.yz.model.Pnotice;
import com.yz.model.UserRole;
import com.yz.service.IPnoticeService;
@Component("pnoticeService")
public class PnoticeServiceImp implements IPnoticeService {
	private IPnoticeDao pnoticeDao;
	public IPnoticeDao getPnoticeDao() {
		return pnoticeDao;
	}
	@Resource
	public void setPnoticeDao(IPnoticeDao pnoticeDao) {
		this.pnoticeDao = pnoticeDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#add(com.yz.model.Person)
	 */
	public void add(Pnotice pnotice) throws Exception {
		pnoticeDao.save(pnotice);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#delete(com.yz.model.Person)
	 */
	public void delete(Pnotice pnotice) {
		pnoticeDao.delete(pnotice);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		pnoticeDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#update(com.yz.model.Person)
	 */
	public void update(Pnotice pnotice) {
		pnoticeDao.update(pnotice);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#getPersons()
	 */
	public List<Pnotice> getPnotices() {
		return pnoticeDao.getPnotices();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#loadById(int)
	 */
	public Pnotice loadById(int id) {
		return pnoticeDao.loadById(id);
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
		String queryString = "select count(*) from Pnotice mo where 1=1 ";
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
		return pnoticeDao.getUniqueResult(queryString,p);
	}
	public Pnotice getPnoticeByPnoticename(String pnoticename) {
		String queryString="from Pnotice mo where mo.name=:pnoticename";
		String[] paramNames=new String[]{"pnoticename"};
		Object[] values=new Object[]{pnoticename};
		return pnoticeDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPersonServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Pnotice> queryList(int con, String convalue, UserRole userRole, int page, int size,int type, int queryState, String starttime, String endtime) {
		String queryString = "from Pnotice mo where 1=1 ";
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
		return pnoticeDao.pageList(queryString,p,page,size);
	}

	public Pnotice getPnoticeById(Integer uppnoticeid) {
		// TODO Auto-generated method stub
		return pnoticeDao.getPnoticeById(uppnoticeid);
	}
	public int getTotalCount(int con, String convalue, UserRole userRoleo,
			String starttime, String endtime) {
		String queryString = "select count(*) from Pnotice mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.title like ? "; 
			}
			if(con==2){
				queryString += "and mo.author like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		if(starttime!=null&&!starttime.equals("")){
			queryString += " and mo.releaseTime>='"+starttime+"'";
		}
		if(endtime!=null&&!endtime.equals("")){
			queryString += " and mo.releaseTime<='"+endtime+"'";
		}
		return pnoticeDao.getUniqueResult(queryString,p);
	}
	public List<Pnotice> queryList(int con, String convalue,
			UserRole userRoleo, int page, int size, String starttime,
			String endtime) {
		String queryString = "from Pnotice mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.title like ? "; 
			}
			if(con==2){
				queryString += "and mo.author like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		if(starttime!=null&&!starttime.equals("")){
			queryString += " and mo.releaseTime>='"+starttime+"'";
		}
		if(endtime!=null&&!endtime.equals("")){
			queryString += " and mo.releaseTime<='"+endtime+"'";
		}
		return pnoticeDao.pageList(queryString,p,page,size);
	}
}
