/**
 * 
 */
package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IInjurycaseDao;
import com.yz.model.Injurycase;
import com.yz.model.Person;
import com.yz.model.UserRole;
import com.yz.service.IInjurycaseService;

/**
 * @author jiang
 *
 */
@Component("injurycaseService")
public class InjurycaseServiceImp implements IInjurycaseService {
	private IInjurycaseDao injurycaseDao;
	public IInjurycaseDao getInjurycaseDao() {
		return injurycaseDao;
	}
	@Resource
	public void setInjurycaseDao(IInjurycaseDao injurycaseDao) {
		this.injurycaseDao = injurycaseDao;
	}

	/* (non-Javadoc)
	 * @see com.yz.service.IInjurycaseService#add(com.yz.model.Injurycase)
	 */
	public void add(Injurycase injurycase) throws Exception {
		injurycaseDao.save(injurycase);
	}

	/* (non-Javadoc)
	 * @see com.yz.service.IInjurycaseService#delete(com.yz.model.Injurycase)
	 */
	public void delete(Injurycase injurycase) {
		injurycaseDao.delete(injurycase);
	}

	/* (non-Javadoc)
	 * @see com.yz.service.IInjurycaseService#deleteById(int)
	 */
	public void deleteById(int id) {
		injurycaseDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.yz.service.IInjurycaseService#getInjurycaseById(java.lang.Integer)
	 */
	public Injurycase getInjurycaseById(Integer upinjurycaseid) {
		return injurycaseDao.getInjurycaseById(upinjurycaseid);
	}

	/* (non-Javadoc)
	 * @see com.yz.service.IInjurycaseService#getInjurycases()
	 */
	public List<Injurycase> getInjurycases() {
		return injurycaseDao.getInjurycases();
	}

	/* (non-Javadoc)
	 * @see com.yz.service.IInjurycaseService#getPageCount(int, int)
	 */
	public int getPageCount(int totalCount, int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}

	/* (non-Javadoc)
	 * @see com.yz.service.IInjurycaseService#getTotalCount(int, java.lang.String, com.yz.model.UserRole, int, int, java.lang.String, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole user,
			int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from Injurycase mo where 1=1 ";
		Object[] p = null;
		
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.casenumber like ? "; 
			}
			if(con==2){
				queryString += "and mo.casename like ? "; 
			}
			if(con==3){
				queryString += "and mo.fillunit like ? "; 
			}
			if(con==4){
				queryString += "and mo.userRole.realname like ? "; 
			}
			if(con==5){
				queryString += "and mo.appraiser like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
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
		return injurycaseDao.getUniqueResult(queryString,p);
	}
	public Injurycase getInjurycaseByInjurycasename(String injurycasename) {
		String queryString="from Injurycase mo where mo.casename=:injurycasename";
		String[] paramNames=new String[]{"injurycasename"};
		Object[] values=new Object[]{injurycasename};
		return injurycaseDao.queryByNamedParam(queryString,paramNames,values);
	}

	/* (non-Javadoc)
	 * @see com.yz.service.IInjurycaseService#loadById(int)
	 */
	public Injurycase loadById(int id) {
		return injurycaseDao.loadById(id);
	}

	/* (non-Javadoc)
	 * @see com.yz.service.IInjurycaseService#queryInjurycaseById(int)
	 */
	public Injurycase queryInjurycaseById(int id) {
		String queryString="from Injurycase mo where mo.id=:id";
		String[] paramNames=new String[]{"id"};
		Object[] values=new Object[]{id};
		return injurycaseDao.queryByNamedParam(queryString,paramNames,values);
	}

	/* (non-Javadoc)
	 * @see com.yz.service.IInjurycaseService#queryList(int, java.lang.String, com.yz.model.UserRole, int, int, int, int, java.lang.String, java.lang.String)
	 */
	public List<Injurycase> queryList(int con, String convalue, UserRole user,
			int page, int size, int queryState, String starttime,
			String endtime) {
		String queryString = "from Injurycase mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.casenumber like ? "; 
			}
			if(con==2){
				queryString += "and mo.casename like ? "; 
			}
			if(con==3){
				queryString += "and mo.fillunit like ? "; 
			}
			if(con==4){
				queryString += "and mo.userRole.realname like ? "; 
			}
			if(con==5){
				queryString += "and mo.appraiser like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
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
		return injurycaseDao.pageList(queryString,p,page,size);
	}

	/* (non-Javadoc)
	 * @see com.yz.service.IInjurycaseService#update(com.yz.model.Injurycase)
	 */
	public void update(Injurycase injurycase) {
		injurycaseDao.update(injurycase);
	}

}
