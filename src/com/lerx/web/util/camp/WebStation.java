package com.lerx.web.util.camp;

public class WebStation {
	//通过检查wel的station，判别当前模块位置并返回
	public static int check(int sta){
		int mod=0;
		if (sta < 100){
			mod = 1;
		}else if(sta > 100 && sta < 200){
			mod = 2;
		}else if(sta > 200 && sta < 300){
			mod = 3;
		}else if(sta > 300 && sta < 400){
			mod = 4;
		}else if(sta > 400 && sta < 500){
			mod = 5;
		}
		return mod;
	}
}	
