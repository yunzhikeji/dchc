package com.yz.dao;

import java.util.List;

import com.yz.model.Judge;

public interface JudgeDao {

	//保存一条记录
	void save(Judge judge);

	//保存一条记录
	Integer savereturn(Judge judge);

	//删除一条记录
	void delete(Judge judge);

	//根据ID删除一条记录
	void deleteById(int id);

	//修改一条记录
	void update(Judge judge);

	//根据hql语句、条件、条件值修改某些记录
	void updateByHql(final String hql,
			final String[] paramNames, final Object[] values);

	//获得所有记录
	List<Judge> getJudges();

	//根据hql语句来查询所有记录
	List<Judge> queryList(String queryString);

	//根据hql、条件值查询某些记录
	List<Judge> getObjectsByCondition(String queryString,
			Object[] p);

	//根据hql语句、条件、条件值查询某些记录
	List<Judge> queryList(String queryString,
			String[] paramNames, Object[] values);

	//根据hql、id列表查询某些记录
	List<Judge> getObjectsByIdList(final String hql,
			final List<Integer> idList);

	//根据hql语句、条件值、分页查询某些记录
	List<Judge> pageList(final String queryString,
			final Object[] p, final Integer page, final Integer size);

	//根据hql、条件值获得一个唯一值
	int getUniqueResult(final String queryString,
			final Object[] p);

	//根据id查询一条记录
	Judge loadById(int id);

	//根据hql语句、条件、值来查询一条记录
	Judge queryByNamedParam(String queryString,
			String[] paramNames, Object[] values);

	//根据hql语句、条件值来查询是否存在该记录
	boolean checkJudgeExistsWithName(String queryString,
			Object[] p);

	//根据hql批量修改
	int updateJudgeByhql(String queryString, Object[] p);

	Judge getJudgeById(Integer upjudgeid);

}