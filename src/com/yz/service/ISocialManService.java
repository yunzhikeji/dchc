package com.yz.service;

import java.util.List;

import com.yz.model.SocialMan;
import com.yz.model.UserRole;
import com.yz.vo.SocialManForm;

public interface ISocialManService {

	// 添加对象
	public abstract void add(SocialMan SocialMan) throws Exception;

	// 删除对象
	public abstract void delete(SocialMan SocialMan);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(SocialMan SocialMan);

	// 获取所有对象
	public abstract List<SocialMan> getSocialMans();

	// 加载一个id的对象
	public abstract SocialMan loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, UserRole user,
			int type, int queryState, String starttime, String endtime);

	// 后台管理-获取符合条件的记录
	public abstract List<SocialMan> queryList(int con, String convalue,
			UserRole user, int page, int size, int type, int queryState,
			String starttime, String endtime);

	public abstract SocialMan getSocialManById(Integer upSocialManid);

	public abstract SocialMan querySocialManById(int id);

	public abstract List<SocialMan> getSocialMansByTypeAndHandleState(int type,
			int handleState, UserRole userRole);

	public abstract int savereturn(SocialMan SocialMan);

	public abstract void saveSocialManWithExcel(SocialManForm socialManForm);

}