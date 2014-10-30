package com.lerx.sys.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class RobotAway {
	private static String robot_EncodeKey = "jai0cbf8wghg15wq4d94h573p6b3032b23eko2kf";    //這可自己改
	private static String checkFunctionName = "";                 //ElementID可改成您想要的名稱，但要對應到html裡input的名稱
	private static int salt_num = 10 ,
			        TimeOut = 3;					//有效期限，單位(分鐘)
	//隨機創建function名稱
	private static String createRandomFunctionName()
	{
		Date now_date = new Date();
		Integer random_number = (int)(Math.random()*1000);
		return "_" + (StringUtil.md5(now_date.getTime()+random_number.toString())).substring(10, 32);
	}
	//這裡還能再自己加料，看要加啥資料檢查就加進去，自己記得string的順序是啥就好，也能把弄完的東西編個碼，不過傳回來時要能解開就是了
      //也能將其時間補滿到長度32，然後混您的驗證key中(這樣做可能比較難猜出來您把時間埋在哪兒,不過自己要記得埋在哪就是了)，您也能用 session 做一個輔助您驗證的東西
	private static String createVerifyKey()
	{
		Date now_date = new Date();
		//salt用的array
		char sal_ary[] = {'a','b','c','d','e','f'};
		int salt ,
			i = 0 ,
			inst_salt_locate;
		//16進位時：1289052f21e, 11位文數字 ; 10進位時：1273799256430,13位文字數 
		String Hex_time = Long.toString(now_date.getTime());
		//先編time和key的md5
		String ID	    = StringUtil.md5(Hex_time+robot_EncodeKey);
		//將time加點salt讓他看起來更像編碼過的
		while(i<salt_num)
		{
			salt = (int)(Math.random()*6);
			inst_salt_locate = (int)(Math.random()*Hex_time.length());
			Hex_time = Hex_time.substring(0,inst_salt_locate) + sal_ary[salt] + Hex_time.substring(inst_salt_locate);
			i++;
		}
		return Hex_time+ID+StringUtil.md5(ID+robot_EncodeKey);
	}

	//單純設定個function名字
	private static void checkFunction()
	{
		checkFunctionName = createRandomFunctionName() + "()";
	}
	//做 xor編碼的動作
	private static String xorEncode(String str,String encode_key) throws UnsupportedEncodingException
	{
		String EncStr = "";
		int encode_key_len = encode_key.length(),str_asc,encode_asc;
		for(int i=0;i<str.length();i++)
		{
			str_asc = (int)str.charAt(i);
			encode_asc = (int)encode_key.charAt(i%encode_key_len);		//主要是key不足時，循環取key值
			EncStr += (char)(str_asc^encode_asc);					//做xor
		}
		
		return URLEncoder.encode(EncStr,"UTF-8");
	}

	//用來擾亂視聽的註解
	private static String confuseComment()
	{
		String comment_char = "0123456789abcdef_+-/,'\";= ",comment_str="";
		Integer char_count = (int)(Math.random()*10)+1 ;
		for(int i=1;i<=char_count;i++)
		{
			comment_str += comment_char.charAt((int)(Math.random()*26));
		}
		return "/*"+comment_str+"*/";
	}

	//將資料分割，並加入擾亂視聽的註解
	private static String confuse(String confuse_str)
	{
		String final_str="";
		boolean break_flag = false;
		int random_num = 0 , confuse_str_len = confuse_str.length() , GetStrLen=0;
		if(confuse_str.length()<=5)
		{
			final_str = confuseComment()+"'"+confuse_str+"'"+confuseComment();
		}
		else
		{
			while(true)
			{
				random_num = (int)(Math.random()*5)+5;
				if((GetStrLen+random_num)>=confuse_str_len)
				{
					random_num = confuse_str_len - GetStrLen;
					break_flag = true;
				}
				
				
				final_str += confuseComment()+"'"+
							confuse_str.substring(GetStrLen, GetStrLen+random_num)+
							"'"+confuseComment();
				GetStrLen += random_num;
				
				if(!break_flag) final_str += "+";
				else break;
				
			}
		}
		return confuseComment()+final_str+confuseComment();
	}
	//將array亂數排序後組成string送出
	private static String randomAry(String[] ary)
	{
		int chk_ary[] = {-1,-1,-1} , //用來存打亂陣列時，已經取過的key值用的 
			seed , seed_count=0;
		boolean exist_flag = false;
		String ary_str = "";
		while(true)
		{
			exist_flag = false;
			seed = (int)(Math.random()*3);
			for(int i=0;i<chk_ary.length;i++)
			{
				if(chk_ary[i]==seed)
				{
					exist_flag = true;
					break;
				}
			}
			
			if(!exist_flag)
			{
				ary_str += ary[seed];
				chk_ary[seed_count] = seed;
				seed_count++;
			}
			if(seed_count>=3) break;
		}
		return ary_str;
	}

	//傳回function的名字，用在要送出資料時，要先塞checkkey的function名稱
	public static String getCheckFunction()
	{
		return checkFunctionName;
	}

	//這裡是主要在用的，用來建立js function
	public static String createJS() throws UnsupportedEncodingException
	{
		checkFunction();
		Integer random_number = (int)(Math.random()*10000);
		Date now_date = new Date();
		//以下都是要組成js用的
		String JS = "", EvalData = "" , js_function = "",
			   VerifyKey = createVerifyKey() ,					//取得verifykey
			   VarName = createRandomFunctionName() ,			//被encode的function中用的	
			   FunctionName = createRandomFunctionName() ,		//被encode的function name
			   EncodeKeyName = createRandomFunctionName() ,		//encode key的name
			   EvalDataName = createRandomFunctionName() ,		//這是js中要用來存xor解開後的資料
			   EncodeFunction = createRandomFunctionName() ,	//這是用來存encode過的js function資料用的
			   EvalDataEncodeKey = StringUtil.md5(now_date.getTime()+random_number.toString()); //用來加密(XOR)用的資料
		JS = "var "+VarName+"='';";
		Integer Verify_len = VerifyKey.length() , GetStrLen=0;
		boolean break_flag = false;
		
		while(true)
		{
			random_number = (int)(Math.random()*10)+1;
			if((GetStrLen+random_number)>=Verify_len-1)
			{
				random_number = Verify_len-GetStrLen;
				break_flag = true;
			}
			JS += VarName +"+='"+VerifyKey.substring(GetStrLen, GetStrLen+random_number)+"';";
			GetStrLen += random_number;
			if(break_flag) break;

		}
		JS += "return "+VarName+";";
		EvalData = xorEncode("var "+FunctionName+"=function() {"+JS+"};",EvalDataEncodeKey);
		String js_ary[] = {"var "+EncodeFunction+"=decodeURIComponent("+confuse(EvalData)+");",
						   "var "+EncodeKeyName+"="+confuse(EvalDataEncodeKey)+";",
						   "var "+EvalDataName+"="+confuse("")+";"
						  };
		
		js_function = "function "+checkFunctionName+" {"+
				   randomAry(js_ary)+
				   "for(var i=0;i<"+EncodeFunction+".length;i++)"+
				   "{"+
				       EvalDataName+"+=String.fromCharCode("+EncodeFunction+".charCodeAt(i)^"+EncodeKeyName+".charCodeAt(i%"+EncodeKeyName+".length));"+
				   "}"+
				   "eval("+EvalDataName+");"+
//				   "document.getElementById(\""+elementID+"\").value="+FunctionName+"();"+
//				   "}";
				   "return "+FunctionName+"();"+
				   "}";
//		System.out.println("FunctionName:"+FunctionName);
//		System.out.println("FunctionNameJS:"+JS);
		return js_function;
	}

	//傳入checkkey的值,檢查是否正常
	public static boolean verify(String key)
	{
//		System.out.println("key.length:"+key.length());
		if(key.length()>=87){
			Date now_date = new Date();
			int time_end_index = 13 + salt_num , i , asc_code;
			String Time = key.substring(0, time_end_index) ,
				   ID = key.substring(time_end_index,time_end_index+32) ,
				   result = key.substring(time_end_index+32) ,
				   final_chk = "" ,
				   CorrectTime = "";								//正確時間戳記
			for(i=0;i<Time.length();i++)
			{
				asc_code = (int)Time.charAt(i);
				if(asc_code>=48 && asc_code<=57)					//數字的部份才是我們想要的，其他是salt
				{
					CorrectTime += (char)asc_code;
				}
			}
			if((Long.parseLong(CorrectTime)+(60*1000*TimeOut)) <= now_date.getTime()){
				return false;
			}
			if(!((StringUtil.md5(CorrectTime+robot_EncodeKey)).equals(ID))){
				return false;
			}
			final_chk = StringUtil.md5(ID+robot_EncodeKey);
//			System.out.println("final_chk:"+final_chk);
//			System.out.println("result:"+result);
//			System.out.println("final_chk.equals(result):"+final_chk.equals(result));
			return (final_chk.equals(result));
		}else{
//			System.out.println("结果2");
			return false;
		}
	}
}
