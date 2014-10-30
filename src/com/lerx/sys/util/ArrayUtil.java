package com.lerx.sys.util;

import java.util.HashSet;
import java.util.Set;

public class ArrayUtil {

	// 检查集合中的值是否包含相同的值
	public static boolean arraySamCheckRandomMod(Long[] ar) {

		int len = ar.length;
		Set<Long> set = new HashSet<Long>();

		for (int i = 0; i < len; i++) {

			if (set.add(ar[i])) {

			}

		}
		
//		System.out.println("ar.size():"+len);
//		System.out.println("set.size():"+set.size());
		
		if (len == set.size()) {
			return true;
		} else {
			return false;
		}

	}
}
