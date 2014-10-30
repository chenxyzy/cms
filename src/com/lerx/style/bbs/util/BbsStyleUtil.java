package com.lerx.style.bbs.util;

import com.lerx.style.bbs.vo.BbsStyle;

public class BbsStyleUtil {

	public static String icoFolder(BbsStyle bs, int act) {
		String f;
		f=bs.getIcoFolderUrl();
		if (f.length()>0){
			if (!f.substring(f.length()-1,f.length()).equals("/")){
				f+="/";
			}
			switch (act) {
			case 1:
				f+="act/";
				break;
			case 2:
				f+="sta/";
				break;
			default:
				
				break;
			}
		}
		
		return f;
	}

	public static String customFormat(BbsStyle bs, int n) {
		String fstr;
		switch (n) {
		case 1:
			fstr = bs.getCustomFormatCode1();
			break;
		case 2:
			fstr = bs.getCustomFormatCode2();
			break;
		case 3:
			fstr = bs.getCustomFormatCode3();
			break;
		case 4:
			fstr = bs.getCustomFormatCode4();
			break;
		case 5:
			fstr = bs.getCustomFormatCode5();
			break;
		case 6:
			fstr = bs.getCustomFormatCode6();
			break;
		case 7:
			fstr = bs.getCustomFormatCode7();
			break;
		case 8:
			fstr = bs.getCustomFormatCode8();
			break;
		default:
			fstr = bs.getCustomFormatCode1();
			break;
		}

		return fstr;
	}
}
