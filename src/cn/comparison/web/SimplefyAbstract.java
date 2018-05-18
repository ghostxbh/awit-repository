package cn.comparison.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.comparison.entity.ComparisonAbstract;
import cn.comparison.entity.ComparisonAbstractDetail;
import cn.comparison.utils.SqlList;

public class SimplefyAbstract {
	
	public static void main(String[] agrs) throws Exception {
		
		SqlList sl = new SqlList();
		// 获取大于4条以上的凭证号
		List<String> codeList = sl.getCodeList();
		for (String code : codeList) {
			//获取分组后的凭证号的摘要信息
			Map<Integer, String> agb = sl.getAbstractGroupByAbstract(code);
			Iterator<Entry<Integer, String>> iterator = agb.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<Integer, String> next = iterator.next();
				String value = next.getValue();
				//获得去重后的凭证信息集合
				List<ComparisonAbstract> rc = sl.getAbstract(code, value);
				ComparisonAbstractDetail ad = new ComparisonAbstractDetail();
				ComparisonAbstract ca = new ComparisonAbstract();
				// 获取数据库中凭证编码最大值
				int maxCode = Integer.parseInt(sl.getMaxVoucherCodeToAbstract())+1;				
				ca.setVoucherCode(String.valueOf(maxCode));
				for (ComparisonAbstract ca1 : rc) {
					ca.setVoucherAbstract(ca1.getVoucherAbstract());
					ca.setDebitAccount(ca1.getDebitAccount());
					ca.setCreditAccount(ca1.getCreditAccount());
					int i = sl.insertAbstract(ca);
					if (i > 0) {
						System.out.println(ca.toString() + "\n添加成功！");
					} else {
						System.out.println("失败");
					}
				}
				ad.setCompanyName(sl.getDetail(code));
				ad.setVoucherCode(ca.getVoucherCode());
				ad.setCheckState(1);
				ad.setVoucherCount("0");
				ad.setPriority("2");
				int k = sl.insertDetail(ad);
				if (k > 0) {
					System.out.println(ad.toString() + "\n添加成功！");
				} else {
					System.out.println("失败");
				}
			}
		}
	}
}
