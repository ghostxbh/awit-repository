package main.comparison.web;

import java.util.Arrays;
import java.util.List;

import main.comparison.utils.SqlList;

public class VoucherController {
	/**
	 * 以凭证类型判断
	 */
	/*
	 * public boolean checkVoucher(String serviceName, String[] voucherAbstract,
	 * String[] accounts, Double[] voucherDebits, Double[] voucherCredits,
	 * String companyName, String departmentName, String ticketType) { //
	 * jinxiang,xiaoxiang,bankbill,expense,otherticket switch (ticketType) {
	 * case "jinxiang":
	 * 
	 * break; case "xiaoxiang": break; case "bankbill": break; case "expense":
	 * break; case "otherticket": break; default: break; }
	 * 
	 * return false;
	 * 
	 * }
	 */
	/**
	 * 接口方式进参判断
	 * 
	 * @param serviceName
	 * @param voucherAbstract
	 * @param accounts
	 * @param voucherDebits
	 * @param voucherCredits
	 * @param companyName
	 * @param departmentName
	 * @param ticketType
	 * @return
	 * @throws Exception
	 */
	/*
	 * public boolean checkVoucher(String serviceName, String[] voucherAbstract,
	 * String[] accounts, Double[] voucherDebits, Double[] voucherCredits,
	 * String companyName, String departmentName, String ticketType) {
	 * 
	 * 
	 * return false;
	 * 
	 * }
	 */

	/*public boolean checkVoucher() throws Exception {
		SqlList sl = new SqlList();
		List<String> list = sl.getList();
		List<String[]> list2 = new ArrayList<>();
		for (String string : list) {
			String[] accountName = sl.getAccountName(sl.getAccount(string));
			list2.add(accountName);
		}
		List<String> code = sl.getCodeListToDetail();
		boolean flag = false;
		for (String s : code) {
			String[] accountName = sl.getAccountName(sl.getAccount(s));
			flag = Arrays.asList(list).contains(accountName);
		}
		return flag;
	}
*/
	
	public static void main(String[] agrs) throws Exception {
		SqlList sl = new SqlList();
		List<String> list = sl.getList();	
		
		for (String string : list) {
			List<String> accountName = sl.getAccountName(sl.getAccount(string));
			System.out.println(accountName.toArray().toString());
		}		
		List<String> code = sl.getCodeListToDetail();		
		for (String s : code) {
			List<String> accountName = sl.getAccountName(sl.getAccount(s));
	
			if (Arrays.asList(list).contains(accountName)) {
				System.out.println("便准库中含有此凭证类型");
			}
		}		
	}

}
