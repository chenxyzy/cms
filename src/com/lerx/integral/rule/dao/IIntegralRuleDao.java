package com.lerx.integral.rule.dao;

import java.util.List;

import com.lerx.integral.rule.vo.IntegralRule;

public interface IIntegralRuleDao {
	public int add(IntegralRule ir);
	public boolean modify(IntegralRule ir);
	public boolean deyById(int id);
	public IntegralRule findById(int id);
	public List<IntegralRule> query(int localPostion);
	public IntegralRule findByName(String name);
	public boolean changeState(int id,boolean state,int localPostion);
	public IntegralRule findDefault(int localPostion);
}
