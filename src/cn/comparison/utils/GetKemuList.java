package cn.comparison.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.comparison.entity.ComparisonAbstract;
import cn.comparison.entity.ComparisonAccount;

public class GetKemuList {

	public static Connection conn = null;
	public static PreparedStatement ps = null;
	public static ResultSet rs = null;

	public static Integer getLastId(String fullName) throws Exception {
		String sql = "select id from comparison_account where full_name='" + fullName + "'";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				int i = rs.getInt("id");
				return i;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return 0;
	}

	/**
	 * parentId从数据库中获取
	 * 
	 * @param accountCode
	 * @param fullName
	 * @param accountName
	 * @return
	 * @throws Exception
	 */
	public static Integer getParentId(String fullName) throws Exception {
		try {
			String[] split = fullName.split("/");
			if (split.length > 2) {
				int lastIndexOf = fullName.lastIndexOf("/");
				String lastFullName = fullName.substring(0, lastIndexOf);
				return getLastId(lastFullName);
			} else {
				String lfullName = split[0];
				return getLastId(lfullName);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return 0;
	}

	/**
	 * 去除小数点和统一位数
	 * 
	 * @param accountCode
	 * @param fullName
	 * @return
	 */
	public static String getCode(String accountCode, String fullName) {

		ComparisonAccount account = new ComparisonAccount();
		// 去除特殊符号
		account.setAccountCode(accountCode);
		// 分割全目录名称看分多少级
		String[] split = fullName.split("/");
		// 全目录级数
		int fullNamelength = split.length;
		// 去掉小数点的科目编码
		if (account.getAccountCode().contains(".")) {
			String replaceCode = accountCode.replaceAll("[.]", "");
			account.setAccountCode(replaceCode);
		}
		// 判断如果是2级的和科目编码等于6位数，变成8位数
		if (fullNamelength == 2 && account.getAccountCode().length() == 6) {

			StringBuffer sb = new StringBuffer(account.getAccountCode());
			StringBuffer insert1 = sb.insert(4, new char[] { '0', '0' });
			String newAccountCode = new String(insert1);
			account.setAccountCode(newAccountCode);
			// 判断如果是2级的和科目编码等于7位数，变成8位数
		} else if (fullNamelength == 2 && account.getAccountCode().length() == 7) {

			StringBuffer sb = new StringBuffer(account.getAccountCode());
			StringBuffer insert1 = sb.insert(4, '0');
			String newAccountCode = new String(insert1);
			account.setAccountCode(newAccountCode);
		}
		// 判断如果是3级的和科目编码等于8位数，变成12位数
		else if (fullNamelength == 3 && account.getAccountCode().length() == 8) {
			StringBuffer sb = new StringBuffer(account.getAccountCode());
			StringBuffer insert1 = sb.insert(4, new char[] { '0', '0' });
			StringBuffer insert2 = insert1.insert(8, new char[] { '0', '0' });
			String newAccountCode = new String(insert2);
			account.setAccountCode(newAccountCode);
			// 判断如果是3级的和科目编码等于9位数，变成12位数
		} else if (fullNamelength == 3 && account.getAccountCode().length() == 9) {
			StringBuffer sb = new StringBuffer(account.getAccountCode());
			StringBuffer insert1 = sb.insert(4, new char[] { '0', '0' });
			StringBuffer insert2 = insert1.insert(8, '0');
			String newAccountCode = new String(insert2);
			account.setAccountCode(newAccountCode);
			// 判断如果是3级的和科目编码等于10位数，变成12位数
		} else if (fullNamelength == 3 && account.getAccountCode().length() == 10) {

			StringBuffer sb = new StringBuffer(account.getAccountCode());
			StringBuffer insert1 = sb.insert(4, '0');
			StringBuffer insert2 = insert1.insert(8, '0');
			String newAccountCode = new String(insert2);
			account.setAccountCode(newAccountCode);
			// 判断如果是4级的和科目编码等于10位数，变成16位数
		} else if (fullNamelength == 4 && account.getAccountCode().length() == 10) {
			StringBuffer sb = new StringBuffer(account.getAccountCode());
			StringBuffer insert1 = sb.insert(4, new char[] { '0', '0' });
			StringBuffer insert2 = insert1.insert(8, new char[] { '0', '0' });
			StringBuffer insert3 = insert2.insert(12, new char[] { '0', '0' });
			String newAccountCode = new String(insert3);
			account.setAccountCode(newAccountCode);
			// 判断如果是4级的和科目编码等于11位数，变成16位数
		} else if (fullNamelength == 4 && account.getAccountCode().length() == 11) {
			StringBuffer sb = new StringBuffer(account.getAccountCode());
			StringBuffer insert1 = sb.insert(4, new char[] { '0', '0' });
			StringBuffer insert2 = insert1.insert(8, '0');
			StringBuffer insert3 = insert2.insert(12, new char[] { '0', '0' });
			String newAccountCode = new String(insert3);
			account.setAccountCode(newAccountCode);
			// 判断如果是4级的和科目编码等于13位数，变成16位数
		} else if (fullNamelength == 4 && account.getAccountCode().length() == 13) {

			StringBuffer sb = new StringBuffer(account.getAccountCode());
			StringBuffer insert1 = sb.insert(4, '0');
			StringBuffer insert2 = insert1.insert(8, '0');
			StringBuffer insert3 = insert2.insert(12, '0');
			String newAccountCode = new String(insert3);
			account.setAccountCode(newAccountCode);
			// 判断如果是5级的和科目编码等于13位数，变成20位数
		} else if (fullNamelength == 5 && account.getAccountCode().length() == 13) {
			StringBuffer sb = new StringBuffer(account.getAccountCode());
			StringBuffer insert1 = sb.insert(4, new char[] { '0', '0' });
			StringBuffer insert2 = insert1.insert(8, '0');
			StringBuffer insert3 = insert2.insert(12, new char[] { '0', '0' });
			StringBuffer insert4 = insert3.insert(16, new char[] { '0', '0' });
			String newAccountCode = new String(insert4);
			account.setAccountCode(newAccountCode);
		}
		return account.getAccountCode();

	}

	/**
	 * AccountCode去重并不同科目的编码进行递增
	 * 
	 * @param accountCode
	 * @return
	 * @throws Exception
	 */
	public static String getAccountCode(String code, String fullName) {

		String sql = "select count(1) from comparison_account where full_name='" + fullName + "'and account_code='"
				+ code + "'";

		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				int i = rs.getInt("count(1)");
				if (i < 0) {

					return code;
				} else {
					return null;
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return null;

	}

	/**
	 * 去除重复的摘要
	 * 
	 * @param voucherAbstract
	 * @param debitAccount
	 * @param creditAccount
	 * @return
	 * @throws Exception
	 */
	public static int getRepeat(String voucherAbstract, String voucherCode, String debitAccount, String creditAccount)
			throws Exception {

		int i = 0;
		/**
		 * 从数据库判断是否有相同的摘要+借贷关系
		 */
		String sql = "select voucher_code from comparison_abstract WHERE voucher_abstract='" + voucherAbstract + ""
				+ "' and debit_account='" + debitAccount + "'and credit_account='" + creditAccount + "'";

		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				String v = rs.getString("voucher_code");
				if (!(v.equals(voucherCode))) {
					i = 1;
					return i;
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return 0;

	}

	/**
	 * 去除小数点及其统一编码
	 * 
	 * @param accountCode
	 * @return
	 */
	public static String getAccountCode(String accountCode) {
		ComparisonAccount account = new ComparisonAccount();
		account.setAccountCode(accountCode.replaceAll("[p{P}+~$`^=|<>～｀＄＾＋＝｜—＜＞（）-￥×]", ""));
		// 去掉小数点的科目编码
		if (account.getAccountCode().contains(".")) {
			String replaceCode = account.getAccountCode().replaceAll("[.]", "");
			account.setAccountCode(replaceCode);
		}
		// 判断如果是2级的和科目编码等于6位数，变成8位数
		if (account.getAccountCode().length() == 6) {

			StringBuffer sb = new StringBuffer(account.getAccountCode());
			StringBuffer insert1 = sb.insert(4, new char[] { '0', '0' });
			String newAccountCode = new String(insert1);
			account.setAccountCode(newAccountCode);
			// 判断如果是2级的和科目编码等于7位数，变成8位数
		} else if (account.getAccountCode().length() == 7) {

			StringBuffer sb = new StringBuffer(account.getAccountCode());
			StringBuffer insert1 = sb.insert(4, '0');
			String newAccountCode = new String(insert1);
			account.setAccountCode(newAccountCode);
			// 判断如果是3级的和科目编码等于8位数，变成12位数
		} else if (account.getAccountCode().length() == 8) {
			if (account.getAccountCode().substring(4, 6).equals("00")) {
				return accountCode;
			} else {
				StringBuffer sb = new StringBuffer(account.getAccountCode());
				StringBuffer insert1 = sb.insert(4, new char[] { '0', '0' });
				StringBuffer insert2 = insert1.insert(8, new char[] { '0', '0' });
				String newAccountCode = new String(insert2);
				account.setAccountCode(newAccountCode);
			}
			// 判断如果是3级的和科目编码等于9位数，变成12位数
		} else if (account.getAccountCode().length() == 9) {
			if (account.getAccountCode().substring(4, 5).equals("0")
					&& account.getAccountCode().substring(6, 7).equals("0")) {

				StringBuffer sb = new StringBuffer(account.getAccountCode());
				StringBuffer insert1 = sb.insert(4, new char[] { '0', '0' });
				StringBuffer insert2 = insert1.insert(8, '0');
				String newAccountCode = new String(insert2);
				account.setAccountCode(newAccountCode);
			}
			// 判断如果是3级的和科目编码等于10位数，变成12位数
		} else if (account.getAccountCode().length() == 10) {
			if (account.getAccountCode().substring(4, 5).equals("0")
					&& account.getAccountCode().substring(7, 8).equals("0")) {
				StringBuffer sb = new StringBuffer(account.getAccountCode());
				StringBuffer insert1 = sb.insert(4, '0');
				StringBuffer insert2 = insert1.insert(8, '0');
				String newAccountCode = new String(insert2);
				account.setAccountCode(newAccountCode);
			} else {
				StringBuffer sb = new StringBuffer(account.getAccountCode());
				StringBuffer insert1 = sb.insert(4, new char[] { '0', '0' });
				StringBuffer insert2 = insert1.insert(8, new char[] { '0', '0' });
				StringBuffer insert3 = insert2.insert(12, new char[] { '0', '0' });
				String newAccountCode = new String(insert3);
				account.setAccountCode(newAccountCode);
			}

			// 判断如果是4级的和科目编码等于10位数，变成16位数
		}
		// 判断如果是4级的和科目编码等于13位数，变成16位数
		else if (account.getAccountCode().length() == 13) {

			StringBuffer sb = new StringBuffer(account.getAccountCode());
			StringBuffer insert1 = sb.insert(4, '0');
			StringBuffer insert2 = insert1.insert(8, '0');
			StringBuffer insert3 = insert2.insert(12, '0');
			String newAccountCode = new String(insert3);
			account.setAccountCode(newAccountCode);

		}
		return account.getAccountCode();

	}

	/**
	 * 获取comparison_abstract表voucher_code的最大值
	 * 
	 * @return
	 */
	public static int getVoucherCode() {

		String sql = "SELECT MAX(CAST(voucher_code as UNSIGNED INTEGER)) FROM comparison_abstract";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			int i = 0;
			while (rs.next()) {
				i = rs.getInt("MAX(CAST(voucher_code as UNSIGNED INTEGER))");
				return i;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}

		return 0;

	}

	/**
	 * 取得当前detail里的最大值
	 * 
	 * @return
	 */
	public static int getMaxCode() {

		String sql = "SELECT MAX(CAST(voucher_code as UNSIGNED INTEGER)) FROM comparison_abstract_detail";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			int i = 0;
			while (rs.next()) {
				i = rs.getInt("MAX(CAST(voucher_code as UNSIGNED INTEGER))");
				return i;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}

		return 0;

	}

	/**
	 * 根据凭证号删除数据
	 * 
	 * @param code
	 * @return
	 */
	public static int delectAbstractByCode(String code) {

		String sql = "DELETE from comparison_abstract WHERE voucher_code='" + code + "'";
		int i = 0;
		try {
			i = JdbcUtil.getConn().prepareStatement(sql).executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}

		return i;

	}

	/**
	 * 根据凭证号删除凭证表的号数据
	 * 
	 * @param code
	 * @return
	 */
	public static int delectDetailByCode(String code) {

		String sql = "DELETE from comparison_abstract_detail WHERE voucher_code='" + code + "'";
		int i = 0;
		try {
			i = JdbcUtil.getConn().prepareStatement(sql).executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}

		return i;
	}

	/**
	 * 获取同一个凭证的数据
	 * 
	 * @param code
	 * @return
	 */
	public static List<ComparisonAbstract> getRepeatData(String code) {
		String sql = "select voucher_abstract,voucher_code,debit_account,credit_account "
				+ "from comparison_abstract WHERE voucher_code='" + code + "'";
		List<ComparisonAbstract> list = new ArrayList<ComparisonAbstract>();
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				ComparisonAbstract ca = new ComparisonAbstract();
				ca.setVoucherAbstract(rs.getString("voucher_abstract"));
				ca.setVoucherCode(rs.getString("voucher_code"));
				ca.setDebitAccount(rs.getString("debit_account"));
				ca.setCreditAccount("credit_account");
				list.add(ca);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return list;
	}

	/**
	 * 查询abstract_detail表voucher_code重复
	 * 
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	public static int getVoucherCode(String code) {
		String sql = "select count(1) from comparison_abstract_detail where voucher_code='" + code + "'";

		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			int i = 0;
			while (rs.next()) {
				i = rs.getInt("count(1)");
				return i;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return 0;

	}

	/**
	 * 根据全路径名称获取code
	 * 
	 * @param parentId
	 * @return
	 */

	public static String getFirstCode(String fullName) {
		String sql = "select account_code from comparison_account where full_name='" + fullName + "'";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				String code = rs.getString("account_code");
				return code;
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return null;
	}

	/**
	 * 查看是否库里面有重复的code
	 * 
	 * @param code
	 * @return
	 */
	public static boolean getpCode(String code) {
		String sql = "select count(1) from comparison_account where account_code='" + code + "'";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			int i = 0;
			while (rs.next()) {
				i = rs.getInt("count(1)");
				if (i > 0) {
					return true;
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}

		return false;

	}

	/**
	 * 查看全路径名称是否重复
	 * 
	 * @param fullName
	 * @return
	 */
	public static boolean getFull(String fullName) {
		String sql = "select count(1) from comparison_account where full_name='" + fullName + "'";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			int i = 0;
			while (rs.next()) {
				i = rs.getInt("count(1)");
				if (i > 0) {
					return true;
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return false;

	}

	/**
	 * 根据全路径名称查找是否包含重复的科目
	 * 
	 * @param fullName
	 * @return
	 */
	public static boolean getFullName(String fullName) {
		String sql = "select count(1) from comparison_account where full_name='" + fullName + "'";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				int i = rs.getInt("count(1)");
				if (i > 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return true;

	}

	/**
	 * 根据全路径名称模糊查询code
	 * 
	 * @param fullName
	 * @return
	 */
	public static String getfName(String fullName) {
		try {
			if (fullName.contains("/")) {
				String[] split = fullName.split("/");
				String sql = "select account_code from comparison_account where full_name like '" + fullName
						+ "%'and length(account_code)<=" + (split.length * 4) + "";
				rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
				while (rs.next()) {
					String code = rs.getString("account_code");
					return code;
				}

			} else {
				String sql = "select account_code from comparison_account where full_name like '" + fullName
						+ "%'and length(account_code)<=4";
				rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
				while (rs.next()) {
					String code = rs.getString("account_code");
					return code;
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return null;

	}

	/**
	 * 根据fullName查询code
	 * 
	 * @param fullName
	 * @return
	 */
	public static String getbName(String fullName) {
		try {
			String sql = "select account_code from comparison_account where full_name = '" + fullName + "'";
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				String code = rs.getString("account_code");
				return code;
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return null;

	}

	/**
	 * 根据全路径名称判断是否重复
	 * 
	 * @param fullName
	 * @return
	 */
	public static boolean getName(String fullName) {
		String sql = "select count(1) from comparison_account where full_name='" + fullName + "'";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				int i = rs.getInt("count(1)");
				if (i == 0) {
					return true;
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return false;

	}

	/**
	 * 根据全路径名称查找id
	 * 
	 * @param fullName
	 * @return
	 */
	public static int getId(String fullName) {
		String sql = "select id from comparison_account where full_name='" + fullName + "'";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				int i = rs.getInt("id");
				return i;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return 0;

	}

	/**
	 * 根据id查找code
	 * 
	 * @param id
	 * @return
	 */
	public static String getCodeById(int id) {
		String sql = "select account_code from comparison_account where id=" + id + "";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				String code = rs.getString("account_code");
				return code;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return null;

	}

	/**
	 * 根据id查找最大code
	 * 
	 * @param id
	 * @return
	 */
	public static String getMaxCode(int id, String fullName) {
		String[] split = fullName.split("/");
		String sql = "select MAX(account_code) from comparison_account where account_parentId='"
				+ id + "'and length(account_code)<=" + split.length * 4 + "";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				String code = rs.getString("MAX(account_code)");
				return code;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return null;

	}

	/**
	 * 根据全路径名称获取ComparisonAccount对象
	 * 
	 * @param fullName
	 * @return
	 */
	public static ComparisonAccount getFirstAccount(String fullName) {
		String sql = "select * from comparison_account where full_name='" + fullName + "'";
		ComparisonAccount ca = new ComparisonAccount();
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();

			while (rs.next()) {
				ca.setId(rs.getInt("id"));
				ca.setAccountCode(rs.getString("account_code"));
				ca.setAccountName(rs.getString("account_name"));
				ca.setFullName(rs.getString("full_name"));
				ca.setAccountType(rs.getString("account_type"));
				ca.setAccountDirect(rs.getString("account_direct"));
				ca.setAccountParentId(rs.getInt("account_parentId"));
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return ca;

	}

	/**
	 * 根据code获取ComparisonAccount对象
	 * 
	 * @param fullName
	 * @return
	 */
	public static ComparisonAccount getAccountByCode(String code) {
		String sql = "select * from comparison_account where account_code='" + code + "'";
		ComparisonAccount ca = new ComparisonAccount();
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();

			while (rs.next()) {
				ca.setId(rs.getInt("id"));
				ca.setAccountCode(rs.getString("account_code"));
				ca.setAccountName(rs.getString("account_name"));
				ca.setFullName(rs.getString("full_name"));
				ca.setAccountType(rs.getString("account_type"));
				ca.setAccountDirect(rs.getString("account_direct"));
				ca.setAccountParentId(rs.getInt("account_parentId"));
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return ca;

	}

	/**
	 * 根据全路径名称获取accountCode
	 * 
	 * @param fullName
	 * @return
	 */
	public static String getvCode(String fullName) {
		String[] split = fullName.split("/");
		String[] uselessCode = { "银行存款", "存放中央银行款项", "其他货币资金", "应收账款", "预付账款", "其他应收款", "原材料", "库存商品", "发出商品", "长期股权投资",
				"无形资产", "长期待摊费用", "固定资产", "累计折旧", "委托加工物资", "应收股利", "应付账款", "其他应付款", "主营业务成本", "应付股利", "长期借款", "实收资本",
				"其他综合收益", "主营业务收入", "专项应付款", "在建工程", "应收票据", "短期借款", "应付票据", "预收账款", "投资收益", "本年利润" };
		// 是否包含无用一级科目，如果有就跳出
		if (Arrays.asList(uselessCode).contains(split[0])) {
			return null;
		} else if (getName(split[0])) {
			return null;
		}
		// 查找存在2级以上的全路径名称下的最大值
		if (split.length > 2) {
			try {
				int lastIndexOf = fullName.lastIndexOf("/");
				String lastFullName = fullName.substring(0, lastIndexOf);
				// 获取上级科目的code
				String lastCode = getLastCode(lastFullName);
				int mCode = getMCode(lastCode);
				if (mCode > 1) {
					String sql = "select MAX(account_code) from comparison_account where full_name like'" + lastFullName
							+ "%'and LENGTH(account_code)<='" + (split.length * 4) + "'";
					rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
					while (rs.next()) {
						String max = rs.getString("MAX(account_code)");
						DecimalFormat df = new DecimalFormat("#");
						return df.format(new BigDecimal(max).add(new BigDecimal("1")));
					}
				} else {
					return lastCode + "0001";
				}

			} catch (Exception e) {

				e.printStackTrace();
			} finally {
				JdbcUtil.closeAll(rs, ps, conn);
			}
		} else {
			int id = getId(split[0]);
			String maxCode = getMaxCode(id, fullName);
			String codeById = getCodeById(id);
			if (Integer.parseInt(maxCode) == 0 || maxCode == null) {
				return (codeById + "0001");
			} else {
				return String.valueOf(maxCode + 1);
			}

		}

		return null;
	}

	/**
	 * 根据code模糊查询此code相似的全部编码的总数
	 * 
	 * @param code
	 * @return
	 */
	public static int getMCode(String code) {
		String sql = "select count(1) from comparison_account where account_code like'" + code + "%'";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				int count = rs.getInt("count(1)");
				return count;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return 0;

	}

	/**
	 * 根据全路径-1名称获取上级全路径-1的accountCode
	 * 
	 * @param fullName
	 * @return
	 */
	public static String getLastCode(String fullName) {
		String sql = "select account_code from com parison_account where full_name='" + fullName + "'";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				String code = rs.getString("account_code");
				return code;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return null;

	}

	/**
	 * 获取含null的code的集合
	 * 
	 * @param code
	 * @return
	 */

	public static List<String> getCodeList() {
		List<String> list = new ArrayList<>();
		String sql = "SELECT voucher_code from comparison_abstract WHERE debit_account='null' or credit_account='null'";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				String code = rs.getString("voucher_code");
				if (!list.contains(code)) {
					list.add(code);
				} else {
					continue;
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return list;

	}

	/**
	 * 查找借贷方向只有一个的code
	 * 
	 * @return
	 */
	public static List<String> getCodeListByCode() {
		List<String> list = new ArrayList<>();
		String sql = "SELECT voucher_code from comparison_abstract GROUP BY voucher_code HAVING COUNT(voucher_code) < 2";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				String code = rs.getString("voucher_code");
				if (!list.contains(code)) {
					list.add(code);
				} else {
					continue;
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return list;

	}

	/**
	 * 查询+1000后的code集合
	 * 
	 * @return
	 */
	public static List<String> getCodeList1() {
		List<String> list = new ArrayList<>();
		String sql = "SELECT voucher_code FROM comparison_abstract where id>1";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				String code = rs.getString("voucher_code");
				list.add(code);

			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return list;

	}

	/**
	 * 更新+1000的数据
	 * 
	 * @param code
	 * @return
	 */
	public static int updateCode(String code) {
		int i = 0;
		String sql = "UPDATE comparison_abstract SET voucher_code='" + code + "'";
		try {
			i = JdbcUtil.getConn().prepareStatement(sql).executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return i;

	}

	/**
	 * 获取原有的编码
	 * 
	 * @return
	 */
	public static List<String> getOldList() {
		List<String> list = new ArrayList<>();
		String sql = "SELECT voucher_code FROM comparison_abstract";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				String code = rs.getString("voucher_code");

				list.add(code);

			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return list;

	}

	/**
	 * +1000的新编码
	 * 
	 * @return
	 */
	public static List<String> getNewList() {
		List<String> list = new ArrayList<>();
		String sql = "SELECT voucher_code+1000 FROM comparison_abstract";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				String code = rs.getString("voucher_code");

				list.add(code);

			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return list;

	}

	/**
	 *
	 * 
	 * @return
	 */
	public static List<String> getOldCodeList() {
		List<String> list = new ArrayList<>();
		String sql = "SELECT voucher_code from comparison_abstract_detail";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				String code = rs.getString("voucher_code");
				if (!list.contains(code)) {
					list.add(code);
				} else {
					continue;
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return list;

	}

	/**
	 *
	 * 根据科目名称查询科目编码
	 * 
	 * @return
	 */
	public static String getCode(String fullName) {
		String sql = "SELECT account_code from comparison_account where full_name='" + fullName + "'";
		try {
			rs = JdbcUtil.getConn().prepareStatement(sql).executeQuery();
			while (rs.next()) {
				String code = rs.getString("account_code");
				return code;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return null;

	}

	/**
	 * 根据全路径名称获取accountCode,并判断是否包含无用的一级科目，并对分2级以下的科目进行编码
	 * 
	 * @param fullName
	 * @return
	 */
	public static String getCodeByFullName(String fullName) {
		String[] split = fullName.split("/");
		String[] uselessCode = { "银行存款", "存放中央银行款项", "其他货币资金", "应收账款", "预付账款", "其他应收款", "原材料", "库存商品", "发出商品", "长期股权投资",
				"无形资产", "长期待摊费用", "固定资产", "累计折旧", "委托加工物资", "应收股利", "应付账款", "其他应付款", "主营业务成本", "应付股利", "长期借款", "实收资本",
				"其他综合收益", "主营业务收入", "专项应付款", "在建工程", "应收票据", "短期借款", "应付票据", "预收账款", "投资收益", "本年利润" };

		// 是否包含无用一级科目，如果有直接返回一级科目的编号
		if (Arrays.asList(uselessCode).contains(split[0])) {
			return getCode(split[0]);
		}
		try {
			// 查找存在2级以下的全路径名称下的最大值
			if (split.length < 3) {
				int id = getId(split[0]);
				String maxCode = getMaxCode(id, fullName);
				String codeById = getCodeById(id);
				if (maxCode == null) {
					return (codeById + "0001");
				} else {
					return String.valueOf(maxCode + 1);
				}

			} else {
				return null;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return null;
	}

	/**
	 *
	 * 三级编码
	 * 
	 * @return
	 * @throws IOException
	 */
	public static String getThreeAccount(String fullName) throws IOException {
		ComparisonAccount cc = null;
		// 分割全路径
		String[] split = fullName.split("/");
		// 二级全名称
		String substring = fullName.substring(0, fullName.lastIndexOf("/"));
		// 二级对象
		ComparisonAccount f = GetKemuList.getFirstAccount(substring);
		// 二级编码
		String code = GetKemuList.getCode(substring);
		// 一级对象
		ComparisonAccount cac = GetKemuList.getFirstAccount(split[0]);
		if (code == null) {
			String maxCode2 = GetKemuList.getMaxCode(GetKemuList.getId(split[0]), substring);
			if (Integer.parseInt(maxCode2) == 0 || maxCode2 == null) {
				cc = new ComparisonAccount(cac.getAccountCode() + "0001", split[split.length - 2], substring,
						cac.getAccountType(), cac.getAccountDirect(), cac.getId());
				int insertAccount = Test.Update.insertAccount(cc);
				if (insertAccount > 0) {
					System.out.println("添加新的二级科目成功" + cc.toString());
				}
				cc = new ComparisonAccount(cc.getAccountCode() + "0001", split[split.length - 1], fullName,
						cc.getAccountType(), cc.getAccountDirect(), cc.getId());
				System.out.println("三级科目"+cc.toString());
			} else {
				cc = new ComparisonAccount(String.valueOf(Integer.parseInt(maxCode2) + 1) + "0001", split[split.length - 1], fullName,
						f.getAccountType(), f.getAccountDirect(), f.getId());
				System.out.println("三级科目"+cc.toString());
			}
		} else {
			cc = new ComparisonAccount(code + "0001", split[split.length - 1], fullName, f.getAccountType(),
					f.getAccountDirect(), f.getId());
			System.out.println("三级科目"+cc.toString());
		}
		int i = Test.Update.insertAccount(cc);
		if (i > 0) {
			System.out.println("添加新的成功" + cc.toString());
		} else {
			System.out.println("添加新的二级三级科目失败");
		}
		return getCode(fullName);
	}

	/**
	 *
	 * 四级编码
	 * 
	 * @return
	 * @throws IOException
	 */
	public static String getFourAccount(String fullName) throws IOException {
		ComparisonAccount cc = null;
		// 分割全路径
		String[] split = fullName.split("/");
		// 一级对象
		// ComparisonAccount cac = GetKemuList.getFirstAccount(split[0]);
		// 四级科目的前三级名称
		String substring3 = fullName.substring(0, fullName.lastIndexOf("/"));
		// 四级科目的前二级名称
		String substring2 = substring3.substring(0, substring3.lastIndexOf("/"));
		// 四级科目的前三级对象
		ComparisonAccount f3 = GetKemuList.getFirstAccount(substring3);
		// 四级科目的前二级对象
		ComparisonAccount f2 = GetKemuList.getFirstAccount(substring2);
		// 四级科目的前三级的编码
		String code3 = GetKemuList.getCode(substring3);
		// 四级科目的前二级的编码
		// String code2 = GetKemuList.getCode(substring2);
		String maxCode2 = GetKemuList.getMaxCode(GetKemuList.getId(substring2), substring3);
		if (code3 == null) {
			ComparisonAccount ab = getAccountByCode(getThreeAccount(substring3));
			cc = new ComparisonAccount(ab.getAccountCode() + "0001", split[split.length - 1], fullName,
					ab.getAccountType(), ab.getAccountDirect(), ab.getId());
			System.out.println("四级科目，+000100010001");
		} else {
			// 三级最大值
			if (Long.parseLong(maxCode2) == 0 || maxCode2 == null) {
				cc = new ComparisonAccount(code3 + "0001", split[split.length - 1], fullName, f3.getAccountType(),
						f3.getAccountDirect(), f3.getId());
				System.out.println("四级科目，不含末级，+0001");
			} else {
				cc = new ComparisonAccount(String.valueOf(Long.parseLong(maxCode2) + 1) + "0001", split[split.length - 2], substring3,
						f2.getAccountType(), f2.getAccountDirect(), f2.getId());
				System.out.println("四级科目，不含三级四级，+max+10001");
			}
		}

		int i = Test.Update.insertAccount(cc);
		if (i > 0) {
			System.out.println("添加新的二级三级科目成功" + cc.toString());
		} else {
			System.out.println("添加新的二级三级科目失败");
		}
		return getCode(fullName);
	}
	/**
	 *
	 * 五级编码
	 * 
	 * @return
	 * @throws IOException
	 */
	public static String getFiveAccount(String fullName) throws IOException {
		ComparisonAccount cc = null;
		// 分割全路径
		String[] split = fullName.split("/");	

		String substring4 = fullName.substring(0, fullName.lastIndexOf("/"));
		
		String substring3 = substring4.substring(0, substring4.lastIndexOf("/"));
		
		//ComparisonAccount f4 = GetKemuList.getFirstAccount(substring4);
		
		ComparisonAccount f3 = GetKemuList.getFirstAccount(substring3);
		
		String code4 = GetKemuList.getCode(substring4);
		
		String maxCode2 = GetKemuList.getMaxCode(GetKemuList.getId(substring3), substring4);
		if (code4 == null) {
			ComparisonAccount ab = getAccountByCode(getFourAccount(substring4));
			cc = new ComparisonAccount(ab.getAccountCode() + "0001", split[split.length - 1], fullName,
					ab.getAccountType(), ab.getAccountDirect(), ab.getId());
			System.out.println("五级科目，+0001000100010001");
		} else {
			// 三级最大值
			if (Long.parseLong(maxCode2) == 0 || maxCode2 == null) {
				cc = new ComparisonAccount(code4 + "0001", split[split.length - 1], fullName, f3.getAccountType(),
						f3.getAccountDirect(), f3.getId());
				System.out.println("五级科目，不含末级，+0001");
			} else {
				cc = new ComparisonAccount(String.valueOf(Long.parseLong(maxCode2) + 1) + "0001", split[split.length - 2], substring4,
						f3.getAccountType(), f3.getAccountDirect(), f3.getId());
				System.out.println("五级科目，不含四级五级，+max+10001");
			}
		}

		int i = Test.Update.insertAccount(cc);
		if (i > 0) {
			System.out.println("添加新的二级三级四级五级科目成功" + cc.toString());
		} else {
			System.out.println("添加新的二级三级四级五级科目失败");
		}
		return getCode(fullName);
	}
	

	public static void main(String[] args) throws IOException {
		String fourAccount = getFiveAccount("所得税费用/当期所得税费用/当期/大连建设公司本部/本金");
		System.out.println(fourAccount);
	}
}
