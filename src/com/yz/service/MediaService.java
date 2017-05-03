package com.yz.service;

import com.yz.model.Media;
import com.yz.model.UserRole;

import java.util.List;

public interface MediaService {

	//添加对象
	void add(Media media);

	//删除对象
	void delete(Media media);

	//删除某个id的对象
	void deleteById(int id);

	//修改对象
	void update(Media media);

	//获取所有对象
	List<Media> getMedias();

	//加载一个id的对象
	Media loadById(int id);

	//后台管理-页数获取
	int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	int getTotalCount(int con, String convalue, UserRole user);

	//后台管理-获取符合条件的记录
	List<Media> queryList(int con, String convalue,UserRole user, int page,
			int size);


	List<Media> loadInjurycaseByTypeAndPid(int mtype,
			Integer id);

	String setInjurycaseIdsSql(String queryString,String convalue, int mtype);

	List<Media> loadJudgeByJid(int jid);

}