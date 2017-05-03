package com.yz.service;

import com.yz.model.Judge;
import com.yz.model.UserRole;

import java.util.List;

public interface JudgeService {

	// 添加对象
	void addAndChangeUnit(Judge judge) throws Exception;

	// 删除对象
	void delete(Judge judge);

	// 删除某个id的对象
	void deleteById(int id);

	// 修改对象
	void update(Judge judge);

	// 获取所有对象
	List<Judge> getJudges();

	// 加载一个id的对象
	Judge loadById(int id);

	// 后台管理-页数获取
	int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	int getTotalCount(int con, String convalue, UserRole user);

	// 后台管理-获取符合条件的记录
	List<Judge> queryList(int con, String convalue,
			UserRole user, int page, int size);

	//测试工作
	void doJob();

	List<Judge> getNewJudges();

	void changeUnitByJudge(Judge judge);
}