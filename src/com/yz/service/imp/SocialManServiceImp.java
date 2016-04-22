package com.yz.service.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.ISocialManDao;
import com.yz.model.SocialMan;
import com.yz.model.UserRole;
import com.yz.service.ISocialManService;
import com.yz.service.ISocialManService;
import com.yz.util.GenerateSqlFromExcel;
import com.yz.vo.SocialManForm;

@Component("SocialManService")
public class SocialManServiceImp implements ISocialManService {
	private ISocialManDao socialManDao;
	public ISocialManDao getSocialManDao() {
		return socialManDao;
	}
	@Resource
	public void setSocialManDao(ISocialManDao SocialManDao) {
		this.socialManDao = SocialManDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISocialManServiceImp#add(com.yz.model.SocialMan)
	 */
	public void add(SocialMan SocialMan) throws Exception {
		socialManDao.save(SocialMan);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISocialManServiceImp#delete(com.yz.model.SocialMan)
	 */
	public void delete(SocialMan SocialMan) {
		socialManDao.delete(SocialMan);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISocialManServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		socialManDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISocialManServiceImp#update(com.yz.model.SocialMan)
	 */
	public void update(SocialMan SocialMan) {
		socialManDao.update(SocialMan);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISocialManServiceImp#getSocialMans()
	 */
	public List<SocialMan> getSocialMans() {
		return socialManDao.getSocialMans();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISocialManServiceImp#loadById(int)
	 */
	public SocialMan loadById(int id) {
		return socialManDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISocialManServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISocialManServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole,int type, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from SocialMan mo where 1=1 ";
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
		//用户所在机构不为空
		if(userRole.getUnit()!=null&&userRole.getUnit().getPids()!=null&&userRole.getUnit().getPids().replace(" ", "")!="")
		{
			String pids = userRole.getUnit().getPids();
			String lastChar = pids.substring(pids.length()-1, pids.length());
			if(lastChar.equals(","))
			{
				pids = pids.substring(0, pids.length()-1);
			}
			queryString += " and mo.id in ("+pids+")";
		}else
		{
			queryString += " and mo.id in (0)";
		}
		System.out.println(queryString);
		
		
		return socialManDao.getUniqueResult(queryString,p);
	}
	public SocialMan getSocialManBySocialManname(String SocialManname) {
		String queryString="from SocialMan mo where mo.name=:SocialManname";
		String[] paramNames=new String[]{"SocialManname"};
		Object[] values=new Object[]{SocialManname};
		return socialManDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISocialManServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<SocialMan> queryList(int con, String convalue, UserRole userRole, int page, int size,int type, int queryState, String starttime, String endtime) {
		String queryString = "from SocialMan mo where 1=1 ";
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
		//用户所在机构不为空
		if(userRole.getUnit()!=null&&userRole.getUnit().getPids()!=null&&userRole.getUnit().getPids().replace(" ", "")!="")
		{
			String pids = userRole.getUnit().getPids();
			String lastChar = pids.substring(pids.length()-1, pids.length());
			if(lastChar.equals(","))
			{
				pids = pids.substring(0, pids.length()-1);
			}
			queryString += " and mo.id in ("+pids+")";
		}else
		{
			queryString += " and mo.id in (0)";
		}
		System.out.println(queryString);
		return socialManDao.pageList(queryString,p,page,size);
	}

	public SocialMan getSocialManById(Integer upSocialManid) {
		// TODO Auto-generated method stub
		return socialManDao.getSocialManById(upSocialManid);
	}
	public SocialMan querySocialManById(int id) {
		String queryString="from SocialMan mo where mo.id=:id";
		String[] paramNames=new String[]{"id"};
		Object[] values=new Object[]{id};
		return socialManDao.queryByNamedParam(queryString,paramNames,values);
	}
	public List<SocialMan> getSocialMansByTypeAndHandleState(int type, int handleState,UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString="from SocialMan mo where mo.type="+type+" and mo.handleState="+handleState;
		//用户所在机构不为空
		if(userRole.getUnit()!=null&&userRole.getUnit().getPids()!=null&&userRole.getUnit().getPids().replace(" ", "")!="")
		{
			String pids = userRole.getUnit().getPids();
			String lastChar = pids.substring(pids.length()-1, pids.length());
			if(lastChar.equals(","))
			{
				pids = pids.substring(0, pids.length()-1);
			}
			queryString += " and mo.id in ("+pids+")";
		}else
		{
			queryString += " and mo.id in (0)";
		}
		return socialManDao.queryList(queryString);
	}
	public int savereturn(SocialMan SocialMan) {
		// TODO Auto-generated method stub
		return socialManDao.savereturn(SocialMan);
	}

		
	
	public void saveSocialManWithExcel(SocialManForm socialManForm) {
		
		try {
			File file = socialManForm.getFile();
			GenerateSqlFromExcel generate = new GenerateSqlFromExcel();
			ArrayList<String []> arrayList = generate.generateStationBugSql(file);
			
			for(int i=0;arrayList!=null && i<arrayList.size();i++) {
				String[] data = arrayList.get(i);
				//实例化PO对象，用PO对象进行保存
				SocialMan socialMan = new SocialMan();
				//人员编号	姓名	性别	出生日期	QQ	微信号	身份证号	手机号码	户籍地址	户籍区域

				socialManDao.save(socialMan);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}