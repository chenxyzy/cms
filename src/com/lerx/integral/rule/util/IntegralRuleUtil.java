package com.lerx.integral.rule.util;

import com.lerx.integral.rule.vo.DoModel;
import com.lerx.integral.rule.vo.IntegralRule;

public class IntegralRuleUtil {
	public static IntegralRule findDefault(DoModel dm) {
		if (dm.getIntegralRuleDaoImp()!=null){
			return dm.getIntegralRuleDaoImp().findDefault(dm.getLocalPostion());
		}else{
			return null;
		}
		
	}

	public static int value(DoModel dm, int mod) {
		IntegralRule ir;
		if (dm.getIr()==null){
			ir = findDefault(dm);
			dm.setIr(ir);
		}else{
			ir=dm.getIr();
		}
		
		if (ir == null) {
			return 0;
		} else {
			int value;
			switch (mod) {
			case 1:
				value = ir.getValueOfReg();
				break;
			case 2:
				value = ir.getValueOfLogin();
				break;
			case 3:
				value = ir.getValueOfAdd();
				break;
			case 4:
				value = ir.getValueOfDel();
				break;
			default:
				value = 0;
			}
			return value;
		}

	}

}
