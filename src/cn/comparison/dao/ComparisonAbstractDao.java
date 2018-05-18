package cn.comparison.dao;

import java.util.List;

import cn.comparison.entity.ComparisonAbstract;

public interface ComparisonAbstractDao {
	/**
	 * 添加摘要
	 * @param compAbstract
	 * @return
	 */
	 public int addAbstract(ComparisonAbstract compAbstract);
	 /**
	  * 删除摘要
	  * @param compAbstract
	  * @return
	  */
	 public int delAbstract(ComparisonAbstract compAbstract);
	 /**
	  * 修改摘要
	  * @param compAbstract
	  * @return
	  */
	 public int updateAbstract(ComparisonAbstract compAbstract);
	 /**
	  * 查询摘要
	  * @param compAbstract
	  * @return
	  */
	 public ComparisonAbstract queryAbstract(ComparisonAbstract compAbstract);
	 /**
	  * 根据凭证号查询凭证信息(集合)
	  * @param voucherCode
	  * @return
	  */
	 public List<ComparisonAbstract> queryAbstractByCode(String voucherCode); 
}
