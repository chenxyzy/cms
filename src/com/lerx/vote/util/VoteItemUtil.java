package com.lerx.vote.util;

import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.SysUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.vote.vo.VoteItem;

public class VoteItemUtil {
	public static String formatHref(FormatElements el,VoteItem vi,String key) {
		String lf=el.getLf();
		lf = StringUtil.strReplace(lf, "{$$title$$}",
				vi.getTitle());
		if (key==null || key.trim().equals("")){
			lf = StringUtil.strReplace(lf, "{$$id$$}",
					""+vi.getId());
		}else{
//			System.out.println("secStr:"+secStr);
			lf = StringUtil.strReplace(lf, "{$$id$$}",
					""+SysUtil.covValue(vi.getId(), key));
		}
//		lf = StringUtil.strReplace(lf, "{$$id$$}",
//				""+SysUtil.covValue(vi.getId(), secStr));
		lf = StringUtil.strReplace(lf, "{$$body$$}",
				vi.getBody());
		lf = StringUtil.strReplace(lf, "{$$recNum$$}",
				""+vi.getRecNum());
		lf = StringUtil.strReplace(lf, "{$$item1$$}",
				vi.getItem1());
		lf = StringUtil.strReplace(lf, "{$$item2$$}",
				vi.getItem2());
		lf = StringUtil.strReplace(lf, "{$$item3$$}",
				vi.getItem3());
		lf = StringUtil.strReplace(lf, "{$$item4$$}",
				vi.getItem4());
		lf = StringUtil.strReplace(lf, "{$$item5$$}",
				vi.getItem5());
		lf = StringUtil.strReplace(lf, "{$$item6$$}",
				vi.getItem6());
		lf = StringUtil.strReplace(lf, "{$$item7$$}",
				vi.getItem7());
		lf = StringUtil.strReplace(lf, "{$$item8$$}",
				vi.getItem8());
		lf = StringUtil.strReplace(lf, "{$$item9$$}",
				vi.getItem9());
		lf = StringUtil.strReplace(lf, "{$$item10$$}",
				vi.getItem10());
		lf = StringUtil.strReplace(lf, "{$$item11$$}",
				vi.getItem11());
		lf = StringUtil.strReplace(lf, "{$$item12$$}",
				vi.getItem12());
		lf = StringUtil.strReplace(lf, "{$$item13$$}",
				vi.getItem13());
		lf = StringUtil.strReplace(lf, "{$$item14$$}",
				vi.getItem14());
		lf = StringUtil.strReplace(lf, "{$$item15$$}",
				vi.getItem15());
		lf = StringUtil.strReplace(lf, "{$$item16$$}",
				vi.getItem16());
		lf = StringUtil.strReplace(lf, "{$$item17$$}",
				vi.getItem17());
		lf = StringUtil.strReplace(lf, "{$$item18$$}",
				vi.getItem18());
		lf = StringUtil.strReplace(lf, "{$$item19$$}",
				vi.getItem19());
		lf = StringUtil.strReplace(lf, "{$$item20$$}",
				vi.getItem20());
		return lf;
	}
}
