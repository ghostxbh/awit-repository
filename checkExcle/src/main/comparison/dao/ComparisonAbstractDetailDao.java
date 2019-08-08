package main.comparison.dao;


import main.comparison.entity.ComparisonAbstractDetail;


public interface ComparisonAbstractDetailDao {
	/**
	 * 添加凭证信息
	 * @param abstractDetail
	 * @return
	 */
	 public int addDetail(ComparisonAbstractDetail abstractDetail);
	 /**
	  * 删除凭证信息
	  * @param abstractDetail
	  * @return
	  */
	 public int delDetail(ComparisonAbstractDetail abstractDetail);
	 /**
	  * 修改凭证信息
	  * @param abstractDetail
	  * @return
	  */
	 public int updateDetail(ComparisonAbstractDetail abstractDetail);
	 /**
	  * 查询凭证信息
	  * @param abstractDetail
	  * @return
	  */
	 public ComparisonAbstractDetail queryDetail(ComparisonAbstractDetail abstractDetail);
	 
}
