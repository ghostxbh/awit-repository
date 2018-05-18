package cn.comparison.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.comparison.entity.ComparisonAbstract;
import cn.comparison.entity.ComparisonAbstractDetail;
import cn.comparison.entity.ComparisonAccount;

public class SqlList {

	public static Connection conn = null;
	public static PreparedStatement ps = null;
	public static ResultSet rs = null;

	/**
	 * 添加凭证摘要对象
	 * 
	 * @param ca
	 * @return
	 * @throws IOException
	 */
	public int insertAbstract(ComparisonAbstract ca) throws Exception {
		String sql = "insert into comparison_abstract_copy(voucher_code,voucher_abstract,debit_account,credit_account)values('"
				+ ca.getVoucherCode() + "','" + ca.getVoucherAbstract() + "','" + "" + ca.getDebitAccount() + "','"
				+ ca.getCreditAccount() + "')";
		ps = JdbcUtil.getConn().prepareStatement(sql);
		int i = ps.executeUpdate();
		JdbcUtil.closeAll(rs, ps, conn);
		return i;
	}

	/**
	 * 添加凭证公司信息对象
	 * 
	 * @param ca
	 * @return
	 * @throws IOException
	 */
	public int insertDetail(ComparisonAbstractDetail ad) throws Exception {
		String sql = "insert into comparison_abstract_detail_copy(voucher_code,company_name,priority)values('"
				+ ad.getVoucherCode() + "','" + ad.getCompanyName() + "','" + ad.getPriority() + "')";
		ps = JdbcUtil.getConn().prepareStatement(sql);
		int i = ps.executeUpdate();
		JdbcUtil.closeAll(rs, ps, conn);
		return i;
	}

	/**
	 * 添加科目对象
	 * 
	 * @param account
	 * @return
	 * @throws IOException
	 */
	public int insert(ComparisonAccount account) throws Exception {
		String sql = "insert into comparison_account(account_code,account_name,full_name,"
				+ "account_type,account_direct,account_parentId)" + "values('" + account.getAccountCode() + "','"
				+ account.getAccountName() + "'" + ",'" + account.getFullName() + "','" + account.getAccountType()
				+ "'," + "'" + account.getAccountDirect() + "','" + account.getAccountParentId() + "')";
		int i = JdbcUtil.getConn().prepareStatement(sql).executeUpdate();
		JdbcUtil.closeAll(rs, ps, conn);
		return i;
	}

	/**
	 * 获取一个凭证号大于4条的凭证编号集合
	 * 
	 * @return List<voucherCode>
	 * @throws Exception
	 */
	public List<String> getCodeList() throws Exception {
		List<String> list = new ArrayList<>();
		String sql = "SELECT voucher_code FROM comparison_abstract GROUP BY voucher_code HAVING COUNT(voucher_code)>0 ";
		rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
		while (rs.next()) {
			String i = rs.getString("voucher_code");
			list.add(i);
		}
		JdbcUtil.closeAll(rs, ps, conn);
		return list;
	}

	/**
	 * 根据凭证编号获取公司名称
	 * 
	 * @param voucherCode
	 * @return company_name
	 * @throws Exception
	 */
	public String getDetail(String voucherCode) throws Exception {
		String sql = "SELECT company_name FROM comparison_abstract_detail WHERE voucher_code = '" + voucherCode + "'";
		rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
		while (rs.next()) {
			String i = rs.getString("company_name");
			return i;
		}
		JdbcUtil.closeAll(rs, ps, conn);
		return null;
	}

	/**
	 * 根据凭证号获取凭证号去重后信息集合
	 * 
	 * @param voucherCode
	 * @return
	 * @throws Exception
	 */
	public List<ComparisonAbstract> getAbstract(String voucherCode, String voucherAbstract) {
		List<ComparisonAbstract> list = new ArrayList<>();
		String sql = "SELECT DISTINCT voucher_abstract,debit_account,credit_account FROM comparison_abstract WHERE voucher_code = '"
				+ voucherCode + "'and voucher_abstract ='" + voucherAbstract + "'";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				ComparisonAbstract ca = new ComparisonAbstract();
				ca.setVoucherAbstract(rs.getString("voucher_abstract"));
				ca.setDebitAccount(rs.getString("debit_account"));
				ca.setCreditAccount(rs.getString("credit_account"));
				list.add(ca);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return list;
	}

	/**
	 * 根据凭证号分组此凭证所包含的摘要信息
	 * 
	 * @param voucherCode
	 * @return
	 * @throws Exception
	 */
	public Map<Integer, String> getAbstractGroupByAbstract(String voucherCode) throws Exception {
		Map<Integer, String> map = new HashMap<>();
		String sql = "SELECT * FROM comparison_abstract WHERE voucher_code = '" + voucherCode
				+ "'GROUP BY voucher_abstract";
		rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
		int i = 1;
		while (rs.next()) {
			map.put(i, rs.getString("voucher_abstract"));
			i++;
		}
		JdbcUtil.closeAll(rs, ps, conn);
		return map;
	}

	/**
	 * 获取数据库中凭证号的最大值-摘要表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getMaxVoucherCodeToAbstract() throws Exception {
		String sql = "SELECT MAX(CAST(voucher_code AS UNSIGNED INTEGER)) FROM comparison_abstract_copy";
		rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
		while (rs.next()) {
			String maxCode = rs.getString("MAX(CAST(voucher_code AS UNSIGNED INTEGER))");
			return maxCode;
		}
		JdbcUtil.closeAll(rs, ps, conn);
		return null;
	}

	/**
	 * 获取数据库中凭证号的最大值-公司表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getMaxVoucherCodeToDetail() throws Exception {
		String sql = "SELECT MAX(CAST(voucher_code AS UNSIGNED INTEGER)) FROM comparison_abstract_detail";
		rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
		while (rs.next()) {
			String maxCode = rs.getString("MAX(CAST(voucher_code AS UNSIGNED INTEGER))");
			return maxCode;
		}
		JdbcUtil.closeAll(rs, ps, conn);
		return null;
	}

	/**
	 * 获取所有凭证编码（除标准的）
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> getCodeListToDetail() throws Exception {
		List<String> list = new ArrayList<>();
		String sql = "SELECT voucher_code FROM comparison_abstract_detail where id>504 ";
		rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
		while (rs.next()) {
			String i = rs.getString("voucher_code");
			list.add(i);
		}
		JdbcUtil.closeAll(rs, ps, conn);
		return list;
	}

	/**
	 * 获取所有标准的凭证编码
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> getList() throws Exception {
		List<String> list = new ArrayList<>();
		String sql = "SELECT voucher_code FROM comparison_abstract_detail where id<505 ";
		rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
		while (rs.next()) {
			String i = rs.getString("voucher_code");
			list.add(i);
		}
		JdbcUtil.closeAll(rs, ps, conn);
		return list;
	}

	/**
	 * 根据凭证号获取此凭证所有的信息
	 * 
	 * @param voucherCode
	 * @return
	 */
	public List<ComparisonAbstract> getVoucher(String voucherCode) {
		List<ComparisonAbstract> list = new ArrayList<>();
		String sql = "SELECT * FROM comparison_abstract WHERE voucher_code='" + voucherCode + "'";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				ComparisonAbstract ca = new ComparisonAbstract();
				ca.setVoucherCode(rs.getString("voucher_code"));
				ca.setVoucherAbstract(rs.getString("voucher_abstract"));
				ca.setDebitAccount(rs.getString("debit_account"));
				ca.setCreditAccount(rs.getString("credit_account"));
				list.add(ca);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return list;
	}

	/**
	 * 根据凭证号获取此凭证所有借贷科目编码数组
	 * 
	 * @param voucherCode
	 * @return
	 * @throws Exception
	 */

	public List<String> getAccount(String voucherCode) throws Exception {
		List<String> list = new ArrayList<>();
		String sql = "SELECT DISTINCT debit_account,credit_account FROM comparison_abstract WHERE voucher_code='"
				+ voucherCode + "'";
		rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
		while (rs.next()) {
			if (!rs.getString("debit_account").equals("")) {
				list.add(rs.getString("debit_account"));
			} else if (!rs.getString("credit_account").equals("")) {
				list.add(rs.getString("credit_account"));
			} else {
				continue;
			}
		}
		JdbcUtil.closeAll(rs, ps, conn);
		return removeDuplicate(list);
	}

	/**
	 * 根据凭证号数组信息获取相对应的一级科目名称数组，并去重
	 * 
	 * @param voucherCode
	 * @return
	 * @throws Exception
	 */

	public List<String> getAccountName(List<String> list) throws Exception {
		List<String> nameList = new ArrayList<>();
		for (String string : list) {
			String sql = "SELECT account_name FROM comparison_account WHERE account_code='" + string.substring(0, 4)
					+ "'";
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				nameList.add(rs.getString("account_name"));
			}
		}

		JdbcUtil.closeAll(rs, ps, conn);
		return nameList;
	}

	/**
	 * 集合去重，去null
	 * 
	 * @param list
	 */

	List<String> removeDuplicate(List<String> list) {
		LinkedHashSet<String> set = new LinkedHashSet<String>(list.size());
		set.addAll(list);
		list.clear();
		list.addAll(set);
		if (list.contains("null")) {
			list.remove("null");
		}
		return list;
	}
}
