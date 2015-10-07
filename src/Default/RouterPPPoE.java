package Default;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class RouterPPPoE {
	
	private String IP;
	
	public RouterPPPoE(String ip){
		this.IP=ip;
	};
	
	public void PPPoE(String user,String password) throws ParseException, IOException{
		HttpResponse response;
		HttpEntity httpEntity;
		String content;
		
		HttpPost post = new HttpPost("http://"+ IP + "/goform/AdvSetWan");
			
		HttpClient client = new DefaultHttpClient();
		
		post.setHeader("Cookie","language=cn; admin:language=cn");
		
		ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair> ();
			
		nvps.add(new BasicNameValuePair("GO","wan_connectd.asp"));
		nvps.add(new BasicNameValuePair("v12_time","1444204280.385"));
		nvps.add(new BasicNameValuePair("WANT2","3"));
		nvps.add(new BasicNameValuePair("WANT1","2"));
		nvps.add(new BasicNameValuePair("dynamicMTU","1500"));
		nvps.add(new BasicNameValuePair("WANIP","0.0.0.0"));
		nvps.add(new BasicNameValuePair("WANMSK","0.0.0.0"));
		nvps.add(new BasicNameValuePair("WANGW","0.0.0.0"));
		nvps.add(new BasicNameValuePair("staticMTU","1500"));
		nvps.add(new BasicNameValuePair("PUN",user));
		nvps.add(new BasicNameValuePair("PPW",password));
		nvps.add(new BasicNameValuePair("MTU","1492"));
		nvps.add(new BasicNameValuePair("PIDL","60"));
		nvps.add(new BasicNameValuePair("PCM","2"));
		nvps.add(new BasicNameValuePair("hour1","0"));
		nvps.add(new BasicNameValuePair("minute1","0"));
		nvps.add(new BasicNameValuePair("hour2","0"));
		nvps.add(new BasicNameValuePair("minute2","0"));
		nvps.add(new BasicNameValuePair("l2tpMTU","1452"));
		nvps.add(new BasicNameValuePair("l2tpAdrMode","1"));
		nvps.add(new BasicNameValuePair("l2tpWANIP","0.0.0.0"));
		nvps.add(new BasicNameValuePair("l2tpWANMSK","0.0.0.0"));
		nvps.add(new BasicNameValuePair("l2tpWANGW","0.0.0.0"));
	
		post.setEntity(new UrlEncodedFormEntity(nvps,Charset.forName("UTF-8")));
			
		response = client.execute(post);
	
		httpEntity =  response.getEntity();
	
		content = EntityUtils.toString(httpEntity);
		
		System.out.println(content);
	}
	
}


