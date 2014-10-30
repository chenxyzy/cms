package com.lerx.integral.rule.vo;

import com.lerx.integral.rule.dao.IIntegralRuleDao;

public class DoModel {
	
	private IIntegralRuleDao integralRuleDaoImp;
	private int localPostion;
	private IntegralRule ir;
	
	public IIntegralRuleDao getIntegralRuleDaoImp() {
		return integralRuleDaoImp;
	}
	public void setIntegralRuleDaoImp(IIntegralRuleDao integralRuleDaoImp) {
		this.integralRuleDaoImp = integralRuleDaoImp;
	}
	public int getLocalPostion() {
		return localPostion;
	}
	public void setLocalPostion(int localPostion) {
		this.localPostion = localPostion;
	}
	public IntegralRule getIr() {
		return ir;
	}
	public void setIr(IntegralRule ir) {
		this.ir = ir;
	}
	
}
