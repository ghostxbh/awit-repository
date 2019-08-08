package main.comparison.dao;


import main.comparison.entity.ComparisonAccount;


public interface ComparisonAccountDao {
	/**
	 * 添加科目
	 * @param account
	 * @return
	 */
	 public int addAccount(ComparisonAccount account);
	 /**
	  * 删除科目
	  * @param account
	  * @return
	  */
	 public int delAccount(ComparisonAccount account);
	 /**
	  * 修改科目
	  * @param account
	  * @return
	  */
	 public int updateAccount(ComparisonAccount account);
	 /**
	  * 查询科目
	  * @param account
	  * @return
	  */
	 public ComparisonAccount queryAccount(ComparisonAccount account);
	 
}
