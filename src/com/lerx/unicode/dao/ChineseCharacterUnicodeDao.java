package com.lerx.unicode.dao;



import com.lerx.unicode.vo.ChineseCharacter;

public interface ChineseCharacterUnicodeDao {
	public ChineseCharacter findById();
	public int findOrderByChar(char cc);
}
