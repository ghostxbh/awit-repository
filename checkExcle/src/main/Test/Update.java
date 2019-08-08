package main.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import main.comparison.entity.ComparisonAbstract;
import main.comparison.entity.ComparisonAbstractDetail;
import main.comparison.entity.ComparisonAccount;
import main.comparison.utils.CellType;
import main.comparison.utils.GetKemuList;
import main.comparison.utils.JdbcUtil;
import main.comparison.web.Test_import_abstract;

public class Update {
	public static Connection conn = null;
	public static PreparedStatement ps = null;
	public static ResultSet rs = null;
	public static String noCode;
	public static void main(String[] agrs) throws IOException {

		FileInputStream fis = null;
		try {
			fis = new FileInputStream("D:/02.xls");

			HSSFWorkbook hwk = new HSSFWorkbook(fis);
			HSSFSheet sheet = hwk.getSheetAt(0);

			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum = sheet.getLastRowNum();
			Row row = null;			
			for (int j = firstRowNum; j <= lastRowNum; j++) {

				if (j <= firstRowNum) {
					continue;
				}

				row = sheet.getRow(j); // 取得第i行
				// 凭证编码
				String voucherCode = CellType.getCellValue(row.getCell(1));
				// 全路径
				String fullName = CellType.getCellValue(row.getCell(3));
				// 摘要
				String voucherAbstract = CellType.getCellValue(row.getCell(4));
				// 借方
				String debitAccount = CellType.getCellValue(row.getCell(6));
				// 贷方
				String creditAccount = CellType.getCellValue(row.getCell(7));

				ComparisonAbstract ca = new ComparisonAbstract();	

				// 摘要
				ca.setVoucherAbstract(voucherAbstract);
				// 凭证
				ca.setVoucherCode(voucherCode);				

				String replaceCode = null;
				
				replaceCode = GetKemuList.getCodeByFullName(fullName);

				if (replaceCode == null) {
					//分割全路径
					String[] split = fullName.split("/");
					String code = GetKemuList.getCode(split[0]);
					if (code==null) {
						System.out.println("没有此一级科目！！");
						// 获取数据库中凭证编码最大值
						int maxCode = GetKemuList.getVoucherCode();
						// 上一行凭证编码
						String lastCode = CellType.getCellValue(sheet.getRow(j - 1).getCell(4));

						if (ca.getVoucherCode().equals(lastCode)) {
							ca.setVoucherCode(String.valueOf(maxCode));
						} else {
							ca.setVoucherCode(String.valueOf(maxCode + 1));
						}
						noCode=ca.getVoucherCode();
					}
					switch (split.length) {					
					case 3:
						replaceCode = GetKemuList.getThreeAccount(fullName);
						break;
					case 4:
						replaceCode = GetKemuList.getFourAccount(fullName);
						break;
					case 5:
						replaceCode = GetKemuList.getFiveAccount(fullName);
						break;
					case 6:
					case 7:
						System.out.println("级数太大，已超过5级");
						break;

					}					
					try {
						if (debitAccount.contains(".")) {
							String replaceAll = debitAccount.replaceAll("[,]", "");
							double parseDouble = Double.parseDouble(replaceAll);
							if (parseDouble < 0 | parseDouble > 0) {
								ca.setDebitAccount(replaceCode);
							} else {
								ca.setCreditAccount(replaceCode);
							}
						} else if (creditAccount.contains(".")) {
							String replaceAll = creditAccount.replaceAll("[,]", "");
							double parseDouble = Double.parseDouble(replaceAll);
							if (parseDouble < 0 | parseDouble > 0) {
								ca.setCreditAccount(replaceCode);
							} else {
								ca.setDebitAccount(replaceCode);
							}
						} else if (debitAccount == null | debitAccount.equals("")) {
							ca.setCreditAccount(replaceCode);
						} else if (debitAccount == null | debitAccount.equals("")) {
							ca.setDebitAccount(replaceCode);
						} else {
							String replaceAll = debitAccount.replaceAll("[,]", "");
							int parseInt = Integer.parseInt(replaceAll);
							if (parseInt < 0 | parseInt > 0) {
								ca.setDebitAccount(replaceCode);
							} else {
								ca.setCreditAccount(replaceCode);
							}
						}

					} catch (Exception e) {
						System.out.println("————————————————————————————");
					}

					if (ca.getDebitAccount() == null) {
						ca.setDebitAccount("");
					} else if (ca.getCreditAccount() == null) {
						ca.setCreditAccount("");
					}

					// 获取数据库中凭证编码最大值
					int maxCode = GetKemuList.getVoucherCode();
					// 上一行凭证编码
					String lastCode = CellType.getCellValue(sheet.getRow(j - 1).getCell(4));

					if (ca.getVoucherCode().equals(lastCode)) {
						ca.setVoucherCode(String.valueOf(maxCode));
					} else {
						ca.setVoucherCode(String.valueOf(maxCode + 1));
					}
					if (ca.getVoucherCode().equals(noCode)) {
						continue;
					}
					
					int insert = insertAbstract(ca);
					if (insert > 0) {
						System.out.println("添加成功");
						System.out.println(ca.toString());
					} else {
						System.out.println("添加失败");
					}

				}

			}
		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 添加凭证表数据
		 */
		Integer maxvCode = GetKemuList.getVoucherCode();
		int maxCode = GetKemuList.getMaxCode();
		ComparisonAbstractDetail ad = new ComparisonAbstractDetail();
		for (int k = maxCode + 1; k <= maxvCode; k++) {
			maxCode = GetKemuList.getMaxCode();
			ad.setCompanyName("中铁九局大连建设有限公司");// 设置公司名称
			ad.setVoucherCode(String.valueOf(k));
			ad.setPriority("2");
			try {
				if (GetKemuList.getVoucherCode(ad.getVoucherCode()) < 1) {
					ad.setVoucherCode(String.valueOf(k));
				} else {
					continue;
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
			int detail = Test_import_abstract.insertDetail(ad);
			if (detail != 0) {
				System.out.println("添加凭证表成功");
			} else {
				System.out.println("添加凭证表失败");
			}
			System.out.println(ad.toString());
		}
	}

	/**
	 * 添加凭证摘要表对象
	 * 
	 * @param ca
	 * @return
	 * @throws IOException
	 */
	public static int insertAbstract(ComparisonAbstract ca) throws IOException {

		int i = 0;
		String sql = "insert into comparison_abstract(voucher_code,voucher_abstract,debit_account,credit_account)values('"
				+ ca.getVoucherCode() + "','" + ca.getVoucherAbstract() + "','" + "" + ca.getDebitAccount() + "','"
				+ ca.getCreditAccount() + "')";
		try {
			ps = JdbcUtil.getConn().prepareStatement(sql);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return i;
	}

	/**
	 * 添加凭证编码对象
	 * 
	 * @param ca
	 * @return
	 * @throws IOException
	 */
	public static int insertDetail(ComparisonAbstractDetail ad) throws IOException {

		int i = 0;
		String sql = "insert into comparison_abstract_detail(voucher_code,company_name,priority)values('"
				+ ad.getVoucherCode() + "','" + ad.getCompanyName() + "','" + ad.getPriority() + "')";
		try {
			ps = JdbcUtil.getConn().prepareStatement(sql);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return i;
	}

	/**
	 * 添加account对象
	 * 
	 * @param account
	 * @return
	 * @throws IOException
	 */
	public static int insertAccount(ComparisonAccount account) throws IOException {

		int i = 0;
		String sql = "insert into comparison_account(account_code,account_name,full_name,"
				+ "account_type,account_direct,account_parentId)" + "values('" + account.getAccountCode() + "','"
				+ account.getAccountName() + "'" + ",'" + account.getFullName() + "','" + account.getAccountType()
				+ "'," + "'" + account.getAccountDirect() + "','" + account.getAccountParentId() + "')";
		try {
			i = JdbcUtil.getConn().prepareStatement(sql).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs, ps, conn);
		}
		return i;
	}

}
