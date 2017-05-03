package com.yz.dao;

import com.yz.model.AnalyzeMan;

import java.util.List;

public interface AnalyzeManDao {

	//保存一条记录
	void save(AnalyzeMan analyzeMan);

	//保存一条记录
	Integer savereturn(AnalyzeMan analyzeMan);

	void delete(AnalyzeMan analyzeMan);

	void deleteById(int id);

	void update(AnalyzeMan analyzeMan);

	void updateByHql(final String hql,
			final String[] paramNames, final Object[] values);

	//获得所有记录
	List<AnalyzeMan> getDisappearMen();

	List<AnalyzeMan> queryList(String queryString);

	List<AnalyzeMan> getObjectsByCondition(String queryString,
			Object[] p);

	List<AnalyzeMan> queryList(String queryString,
			String[] paramNames, Object[] values);

	List<AnalyzeMan> getObjectsByIdList(final String hql,
			final List<Integer> idList);

	List<AnalyzeMan> pageList(final String queryString,
			final Object[] p, final Integer page, final Integer size);

	int getUniqueResult(final String queryString,
			final Object[] p);

	AnalyzeMan loadById(int id);

	AnalyzeMan queryByNamedParam(String queryString,
			String[] paramNames, Object[] values);


}