package Test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.comparison.entity.ComparisonAbstractDetail;
import cn.comparison.entity.ComparisonAccount;
import cn.comparison.utils.GetKemuList;
import cn.comparison.web.Test_import_abstract;
import junit.framework.TestCase;

public class Test1 extends TestCase {	
	
	public void test() throws IOException{
		//Integer maxvCode = GetKemuList.getVoucherCode();
		//int maxCode = GetKemuList.getMaxCode();
		ComparisonAbstractDetail ad = new ComparisonAbstractDetail();
		for (int k = 55510; k <= 56476; k++) {
			//maxCode = GetKemuList.getMaxCode();
			ad.setCompanyName("淄博润义金环保新材料科技有限公司");// 设置公司名称
			ad.setVoucherCode(String.valueOf(k));
			ad.setPriority("2");
		/*	try {
				if (GetKemuList.getVoucherCode(ad.getVoucherCode()) < 1) {
					ad.setVoucherCode(String.valueOf(k));
				} else {
					continue;
				}
			} catch (Exception e) {  

				e.printStackTrace();
			}*/
			int detail = Test_import_abstract.insertDetail(ad);
			if (detail != 0) {
				System.out.println("添加凭证表成功");
			} else {
				System.out.println("添加凭证表失败");
			}
			System.out.println(ad.toString());
		}
	}
}
