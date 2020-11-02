package com.ultrapower.cdndetect;

//import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
//@RequestMapping(value = { "/cdn" }, method = { RequestMethod.POST }, produces="application/json;charset=UTF-8")
public class DetectController {
	Logger logger = LoggerFactory.getLogger(getClass());
//	@ApiOperation(value="嫌疑人研判分析", notes="嫌疑人研判")
//	@PostMapping("/test")
@RequestMapping(value="/cdn", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
//	@ResponseStatus(HttpStatus.OK)
	public String getUserJudgeAnalysisByQuery(@RequestParam("url") String curl) {
		String result = "";
//		int rowno = (pageno - 1) * pagesize;
//		Object[] args = new Object[]{longStartTime, longEndTime, longStartTime, longEndTime, longStartTime, longEndTime, daypercent, trailcount, rowno, pagesize};
//		byte[] result = JdbcDao.getResult(MappingEnum.UserJudge, args, true);
//		result = object.toString().getBytes("UTF-8");
//		logger.info(type.toString());
//		logger.info(object.toString());
//		result = url1;
	HttpURLConnection httpURLConnection = null;
	try {
		// 1. 得到访问地址的URL
//		URL url = new URL("https://d23rx7c48u70zk.cloudfront.net/aws-icons_32x32.png");
		URL url = new URL(curl);
		// 2. 得到网络访问对象java.net.HttpURLConnection
		httpURLConnection = (HttpURLConnection) url.openConnection();
		/* 3. 设置请求参数（过期时间，输入、输出流、访问方式），以流的形式进行连接 */
		// 设置是否向HttpURLConnection输出
		httpURLConnection.setDoOutput(false);
		// 设置是否从httpUrlConnection读入
		httpURLConnection.setDoInput(true);
		// 设置请求方式　默认为GET
		httpURLConnection.setRequestMethod("GET");
		// 设置是否使用缓存
		httpURLConnection.setUseCaches(true);
		// 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
		httpURLConnection.setInstanceFollowRedirects(true);
		// 设置超时时间
		httpURLConnection.setConnectTimeout(3000);
		// 连接
		httpURLConnection.connect();
		// 4. 得到响应状态码的返回值 responseCode
		int code = httpURLConnection.getResponseCode();
		httpURLConnection.getHeaderFields();
		Map<String ,List<String >> map=httpURLConnection.getHeaderFields();
		Set<String> set=map.keySet();
		for (String  key:set){
			List<String > list=map.get(key);
			for (String  value:list){
				if (key == null)
					result += value+"\r\n";
				else
				result += key + ":" +value+"\r\n";
//				logger.info("HttpUtil","key="+key+"  value="+value);
			}
		}
//		message= (String) connection.getContent();
//		connection.disconnect();
		// 5. 如果返回值正常，数据在网络中是以流的形式得到服务端返回的数据
//		String msg = "";
//		if (code == 200) { // 正常响应
//			// 从流中读取响应信息
//			BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
//			String line = null;
//			while ((line = reader.readLine()) != null) { // 循环从流中读取
//				msg += line + "\n";
//			}
//			reader.close(); // 关闭流
//		}
		// 显示响应结果
//		log.info(msg);
	} catch (IOException e) {
		logger.error("转发出错，错误信息："+e.getLocalizedMessage()+";"+e.getClass());
		result = "url error";
	}finally {
		// 6. 断开连接，释放资源
		if (null != httpURLConnection){
			try {
				httpURLConnection.disconnect();
			}catch (Exception e){
				logger.info("httpURLConnection 流关闭异常："+ e.getLocalizedMessage());
			}
		}
	}
		return result;
	}
}