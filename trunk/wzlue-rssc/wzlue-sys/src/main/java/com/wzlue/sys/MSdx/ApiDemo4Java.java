package com.wzlue.sys.MSdx;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *  短信代理平台HTTP接口Java开发示�?
 *  具体接口说明请查看文档《短信代理平台HTTP接口文档.docx�?
 *  	短信发送接口与其他业务接口区别�?
 *  		1.请求地址端口不同，详细查看示例说�?
 *          2.短信发送采用POST方式并设置请求头信息，而其他业务接口采用GET方式;
  * @ClassName: ApiDemo4Java
  * @Description: TODO
  * @author Peng_Hu
  * @date 2017�?�?4�?上午11:44:20
  * 
  */
public class ApiDemo4Java {
	static Map<String, String> smsCode = new HashMap<String, String>(); //后期存放redis

	/**
	 *	账号
	 */
	static String ACCOUNT_SID = "WZLUE06";
	/**
	 * APIKEY
	 */
	static String ACCOUNT_APIKEY = "5E56DDF3CCFC4E9D99D9F46DE3143B20";
	/**
	 * utf8编码
	 */
	static final String CHARSET_UTF8 = "utf-8";
	static final String CHARSET_GBK = "gbk";
	
	/**
	 * 短信发送接口请求地址
	 */
	static String SendSmsHttpPostUrl = "http://101.37.77.10:7090/api/rest";
	/**
	 * 其他HTTP接口地址
	 */
	static String OtherApiHttpGetUrl = "http://101.37.77.10:7099/api/rest";

	// 付款 您好：订单号：@1@。@2@ 已付款，请注意查收！
	public static void sendPay(String ch ,String sp,String mobile){

		sendTplSms("315bd3948d9646189fd2822c0b3d281c", "@1@="+ch+"||@2@="+sp, mobile,"");
	}

	// 接单
	public static void sendbJD(String ch ,String sp,String mobile){

		sendTplSms("6c42963e24384ce2b9c92d9fc190238b", "@1@="+ch+"||@2@="+sp, mobile,"");
	}

	//发送验保质期证码   您好，您在xxx仓库中的xxx商品保质期还剩xxx天，请知晓！
	public static void sendbzq(String ch ,String sp,String day,String mobile){

		sendTplSms("d8b310242de44760aaf9f10812e0abe2", "@1@="+ch+"||@2@="+sp+"||@3@="+day, mobile,"");
	};

	//仓储费用   您好，您在xxx仓库中的货值小于仓储费用，请知晓！
	public static void sendccfy(String ck,String mobile){

		sendTplSms("f95690323a124f098ef614eb5b85689b", "@1@="+ck, mobile,"");
	};

	//发送验证码
	public static void send(String mobile){
//		Random r = new Random();
//		sendTplSms("d3c66808e1ea474f969b529ebc78390c", "@1@="+r.nextInt(10000)+"||@2@=短信应用", "17602151495","");
		int code = (int)((Math.random()*9+1)*1000);
		smsCode.put(mobile, String.valueOf(code));
		sendTplSms("a9f68f959f684e369a5f3e846b2840e7", "@1@="+code+"||@2@="+"10", mobile,"");
		
	}
	//发送充值卡的卡号
	public static void sendCard(String mobile,String card){
		sendTplSms("a5b21291c5db448d94807e8e36d97bc5","@1@="+card, mobile,"");
	}

	//验证验证码
	public static boolean yz(String mobile, String code){
		if(code.equals(smsCode.get(mobile))){
			return true;
		}
		//String rpt = queryRpt();
		//if (rpt.indexOf(mobile)!=-1){
            //"只要test.indexOf('This')返回的值不是-1说明test字符串中包含字符串'This',相反如果包含返回的值必定是-1"
            //return true;
        //}
        return  false;
	};
	
	/**
	 * @param args
	 * void 
	*/
	public static void main(String[] args) {

		//sendbzq("ch","牛肉","222","17602151495");
//		sendccfy("dddd","17602151495");

		/******获取短信账号信息**************/
//		queryUser();
		send("18021532358");
		/******查询国际短信资费******/
//		queryUserGjPrices();
		/******获取某个模板信息**************/
//		queryTemplateById("4C08CFDDB8494DA6931364D3AEC41352");
		
		/******获取账号所有模板信�?*************/
//		queryTemplateByAccount();
		
//		Random r = new Random();
		/******发送普通短�?*************/
//		sendSms("你好帅 ","15650138007","发噶施工方");
		
		/******发送模板短�?*************/
//		sendTplSms("d3c66808e1ea474f969b529ebc78390c", "@1@="+r.nextInt(10000)+"||@2@=短信应用", "17602151495","");
		
		/*****发送国际短�?*******/
//		sendGlobalSms("this is test sms,the virefy is 8888.", "890000", "");
		
		/*****获取状态报�?*******/
//		queryRpt();
		
		/*****获取上行短信********/
//		queryMO();
	}
	
	/**
	 * 获取账号国际短信资费
	 * @return
	 * String
	 */
	public static String queryUserGjPrices(){
		//签名
		String sign = "";
		try {
			sign = md5Digest(ACCOUNT_SID + ACCOUNT_APIKEY);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//请求url
		StringBuilder url = new StringBuilder();
		url.append(OtherApiHttpGetUrl).append("/user/price/gets").append("?sid=").append(ACCOUNT_SID).append("&sign=").append(sign);
		String resultJson = sendHttpGet(url.toString());
		System.out.println("/user/price/gets=" + resultJson);
		return resultJson;
	}
	
	/**
	 * 发送国际短�?
	 * 支持同时发送国际短信和国内短信
	 * @param content 必填	短信内容
	 * @param mobiles 手机号码，多个以英文逗号隔开，最�?0000个号码，（号码前面必须添加国家区号，区号+号码）如:8613800000000,61423547790,85264859465
	 * @param extno 自定义扩展，建议1-3位，只适用国内短信
	 * @return
	 * String
	 */
	 public static String sendGlobalSms(String content,String mobiles,String extno){
	    	String sign = "";
	    	try {
	    		StringBuilder signStr = new StringBuilder();
	    		signStr.append(ACCOUNT_SID).append(ACCOUNT_APIKEY).append(mobiles);
	    		//签名=md5(sid+apikey+mobile)
	    		sign = md5Digest(signStr.toString());
	    	} catch (NoSuchAlgorithmException e) {
	    		e.printStackTrace();
	    	} catch (UnsupportedEncodingException e) {
	    		e.printStackTrace();
	    	}
	    	StringBuilder url = new StringBuilder();
	    	url.append(SendSmsHttpPostUrl).append("/sms/sendGlobalSms");//.append("?sid=").append(ACCOUNT_SID).append("&sign=").append(sign);
	    	System.out.println(url);
	    	Map<String,String> params = new HashMap<String,String>();
	    	params.put("sid", ACCOUNT_SID);
	    	params.put("sign", sign);
	    	params.put("mobile", mobiles);
	    	params.put("content", content);
	    	params.put("extno", extno);
	    	String resultJson = sendHttpPost(url.toString(), params);
	    	System.out.println("/sms/sendGlobalSms="+resultJson);
	    	return resultJson;
	    }
	 
	/**
     * 发送普通短�?
     * @param content 短信内容
     * @param mobiles 手机号码  （多个以英文逗号隔开，最�?0000个号码）
     * @param extno  1.	自定义签名时，填写报备签名所对应的扩展子�?
     * 				 2.	强制签名时，自定义扩展，建议1-3�?

     * @return
     * String
     */
    public static String sendSms(String content,String mobiles,String extno){
    	String sign = "";
    	try {
    		/**
			 * 签名认证 sign=md5(sid+apikey+tplid+mobile+content);
			 * 若sid和apikey确定无误，请求返回值返�?005错误码（sign参数签名认证错误），
			 * 处理方法：短信内容包含中文字符，请采用utf-8或gb2312转码后进行md5签名后提交发�?
			 */
    		StringBuilder signStr = new StringBuilder();
    		signStr.append(ACCOUNT_SID).append(ACCOUNT_APIKEY).append(mobiles).append(content);
    		sign = md5Digest(signStr.toString());
    	} catch (NoSuchAlgorithmException e) {
    		e.printStackTrace();
    	} catch (UnsupportedEncodingException e) {
    		e.printStackTrace();
    	}
    	StringBuilder url = new StringBuilder();
    	url.append(SendSmsHttpPostUrl).append("/sms/sendSms");
    	
    	//添加POST请求参数
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("sid", ACCOUNT_SID);
    	params.put("sign", sign);
    	params.put("mobile", mobiles);	
    	params.put("content", content);
    	//params.put("time", "20170222120222");  //定时短信：格�?yyyyMMddmmhhss
    	params.put("extno", extno);
    	
    	//发送Http Post请求
    	String resultJson = sendHttpPost(url.toString(), params);
    	System.out.println("/sms/sendTplSms="+resultJson);
    	return resultJson;
    }
    
    
    /**
     * 发送模板短�?
     * @param tplId 模板编号
     * @param content 参数值，多个参数以“||”隔开   �?@1@=HY001||@2@=3281
     * @param mobiles 手机号码，多个以英文逗号隔开，最�?0000个号�?
     * @param extno 自定义扩展，建议1-3�?
     * @return
     * String
     */
	public static String sendTplSms(String tplId,String content,String mobiles,String extno){
		//签名
		String sign = "";
		try {
			/**
			 * 签名认证 sign=md5(sid+apikey+tplid+mobile+content);
			 * 若sid和apikey确定无误，请求返回值返�?005错误码（sign参数签名认证错误），
			 * 处理方法：短信内容包含中文字符，请采用utf-8或gb2312转码后进行md5签名后提交发�?
			 */
			StringBuilder signStr = new StringBuilder();
			signStr.append(ACCOUNT_SID).append(ACCOUNT_APIKEY).append(tplId).append(mobiles).append(content);
			
			sign = md5Digest(signStr.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		StringBuilder url = new StringBuilder();
		url.append(SendSmsHttpPostUrl).append("/sms/sendTplSms");
		//添加POST请求参数
		Map<String,String> params = new HashMap<String,String>();
		params.put("sid", ACCOUNT_SID);
		params.put("sign", sign);
		params.put("tplid", tplId);
		params.put("mobile", mobiles);
		params.put("content", content);
		params.put("extno", extno);
		
		//发送Http Post请求
		String resultJson = sendHttpPost(url.toString(), params);
		System.out.println("/sms/sendTplSms="+resultJson);
		return resultJson;
	}
	/**
	 * 查询账号信息
	 * 	/user/get?sid={sid}&sign={sign}
	 * @return json字符�?详细描述请参考接口文�?
	 * String
	 */
	public static String queryUser(){
		String sign = "";
		try {
			//签名认证 sign=md5(sid+apikey);
			sign = md5Digest(ACCOUNT_SID + ACCOUNT_APIKEY);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//请求url
		StringBuilder url = new StringBuilder();
		url.append(OtherApiHttpGetUrl).append("/user/get").append("?sid=").append(ACCOUNT_SID).append("&sign=").append(sign);
		
		//发送Http Get请求
		String resultJson = sendHttpGet(url.toString());
		System.out.println("/user/get=" + resultJson);
		return resultJson;
	}
	
	
	/**
	 * 查询某个模板信息
	 * /tpl/gets?sid={sid}&sign={sign}
	 * @return json字符�?详细描述请参考接口文�?
	 * @return
	 * String
	 */
	public static String queryTemplateByAccount(){
		String sign = "";
		try {
			//签名认证 sign=md5(sid+apikey);
			sign = md5Digest(ACCOUNT_SID + ACCOUNT_APIKEY);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//请求url
		StringBuilder url = new StringBuilder();
		url.append(OtherApiHttpGetUrl).append("/tpl/gets").append("?sid=").append(ACCOUNT_SID).append("&sign=").append(sign);
		
		//发送Http Get请求
		String resultJson = sendHttpGet(url.toString());
		System.out.println("/tpl/gets=" + resultJson);
		return resultJson;
	}
	
	/**
	 * 查询某个模板信息
	 * /tpl/get?sid={sid}&sign={sign}&tplid={tplid}
	 * @return json字符�?详细描述请参考接口文�?
	 * @return
	 * String
	 */
	public static String queryTemplateById(String tplId){
		//签名
		String sign = "";
		try {
			//签名认证 sign=md5(sid+apikey);
			sign = md5Digest(ACCOUNT_SID + ACCOUNT_APIKEY + tplId);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//请求url
		StringBuilder url = new StringBuilder();
		url.append(OtherApiHttpGetUrl).append("/tpl/get").append("?sid=").append(ACCOUNT_SID).append("&sign=").append(sign).append("&tplid=").append(tplId);
		
		//发送Http Get请求
		String resultJson = sendHttpGet(url.toString());
		System.out.println("/tpl/get=" + resultJson);
		return resultJson;
	}
	
	/**
	 * 获取状态报�?
	 * 	每次请求只能获取未被获取过的短信状态报告，已获取的报告不会重复获取;单次请求最多获�?0000条状态报告记录�?
	 * @return json字符�?详细描述请参考接口文�?
	 * String
	 */
	public static String queryRpt(){
		String sign = "";
		try {
			//签名认证 sign=md5(sid+apikey);
			sign = md5Digest(ACCOUNT_SID + ACCOUNT_APIKEY);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//请求url
		StringBuilder url = new StringBuilder();
		url.append(OtherApiHttpGetUrl).append("/report/query").append("?sid=").append(ACCOUNT_SID).append("&sign=").append(sign);
		
		//发送Http Get请求
		String resultJson = sendHttpGet(url.toString());
		System.out.println("resultJson=" + resultJson);
		return resultJson;
	}
	
	
	/**
	 * 获取上行状态报�?
	 * 	每次请求只能获取未被获取过的上行短信，已获取的上行短信不会重复获�?单次请求最多获�?0000条上行短信记�?
	 * @return json字符�?详细描述请参考接口文�?
	 * String
	 */
	public static String queryMO(){
		String sign = "";
		try {
			//签名认证 sign=md5(sid+apikey);
			sign = md5Digest(ACCOUNT_SID + ACCOUNT_APIKEY);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//请求url
		StringBuilder url = new StringBuilder();
		url.append(OtherApiHttpGetUrl).append("/deliver/query").append("?sid=").append(ACCOUNT_SID).append("&sign=").append(sign);
		
		//发送Http Get请求
		String resultJson = sendHttpGet(url.toString());
		System.out.println("resultJson=" + resultJson);
		return resultJson;
	}
	
	
	/***
	 * 
	 * @param apiUrl 接口请求地址
	 * @param
	 * @return json字符�?详细描述请参考接口文�?
	 * String
	 */
	private static String sendHttpGet(String apiUrl) {
		String responseText = "";
		BufferedReader br = null;
		try {
			URL url = new URL(apiUrl);
			URLConnection connection = url.openConnection();
			//读取响应返回�?
			InputStream is = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is,CHARSET_UTF8));
			String temp = "";
			while (( temp = br.readLine()) != null) {
				responseText += temp;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseText;
	}
	
	
	/***
	 * 
	 * @param apiUrl 接口请求地址
	 * @param paramsMap 请求参数集合
	 * @return json字符�?详细描述请参考接口文�?
	 * String
	 */
	private static String sendHttpPost(String apiUrl, Map<String, String> paramsMap) {
		String responseText = "";
		BufferedReader br = null;
		StringBuilder params = new StringBuilder();
		Iterator<Map.Entry<String, String>> iterator = paramsMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			try {
				params.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(),CHARSET_UTF8)).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
//			params.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		try {
			URL url = new URL(apiUrl);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), CHARSET_UTF8);
			out.write(params.toString()); //post的关键所在！
			out.flush();
			out.close();
			//读取响应返回�?
			InputStream is = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is,CHARSET_UTF8));
			String temp = "";
			while (( temp = br.readLine()) != null) {
				responseText += temp;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}try {
			if(br != null){
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseText;
	}
	
	
	private static String md5Digest(String src) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(src.getBytes(CHARSET_UTF8));
		return byte2HexStr(b);
	}
	
	private static String byte2HexStr(byte[] b){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; ++i) {
			String s = Integer.toHexString(b[i] & 0xFF);
			if (s.length() == 1)
				sb.append("0");
			sb.append(s.toUpperCase());
		}
		return sb.toString();
	}
	

}

