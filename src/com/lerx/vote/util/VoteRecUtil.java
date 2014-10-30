package com.lerx.vote.util;

import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.vote.vo.VoteRec;

public class VoteRecUtil {
	public static VoteRec prosTrim(VoteRec vr){
		if (vr.getAddress()!=null){
			vr.setAddress(vr.getAddress().trim());
		}
		if (vr.getEmail()!=null){
			vr.setEmail(vr.getEmail().trim());
		}
		if (vr.getIdentity()!=null){
			vr.setIdentity(vr.getIdentity().trim());
		}
		if (vr.getName()!=null){
			vr.setName(vr.getName().trim());
		}
		if (vr.getPhone()!=null){
			vr.setPhone(vr.getPhone().trim());
		}
		if (vr.getMes()!=null){
			vr.setMes(vr.getMes().trim());
		}
		return vr;
	}
	
	public static String formatHref(FormatElements el,VoteRec vr) {
		String lf=el.getLf();
		lf = StringUtil.strReplace(lf, "{$$name$$}",
				StringUtil.nullFilter(vr.getName()));
		lf = StringUtil.strReplace(lf, "{$$email$$}",
				StringUtil.nullFilter(vr.getEmail()));
		lf = StringUtil.strReplace(lf, "{$$addIp$$}",
				StringUtil.nullFilter(vr.getAddIp()));
		lf = StringUtil.strReplace(lf, "{$$address$$}",
				StringUtil.nullFilter(vr.getAddress()));
		lf = StringUtil.strReplace(lf, "{$$identity$$}",
				StringUtil.nullFilter(vr.getIdentity()));
		lf = StringUtil.strReplace(lf, "{$$mes$$}",
				StringUtil.nullFilter(vr.getMes()));
		lf = StringUtil.strReplace(lf, "{$$occ$$}",
				StringUtil.nullFilter(vr.getOcc()));
		lf = StringUtil.strReplace(lf, "{$$phone$$}",
				StringUtil.nullFilter(vr.getPhone()));
		if (vr.getAddTime()!=null){
			lf = StringUtil.strReplace(lf, "{$$addTime$$}",
					""+vr.getAddTime());
		}else{
			lf = StringUtil.strReplace(lf, "{$$addTime$$}",
					"");
		}
		
		if (vr.getUser()!=null){
			lf = StringUtil.strReplace(lf, "{$$userName$$}",
					""+vr.getUser().getUserName());
		}else{
			lf = StringUtil.strReplace(lf, "{$$userName$$}",
					"");
		}
		
		return lf;
		
	}
}
