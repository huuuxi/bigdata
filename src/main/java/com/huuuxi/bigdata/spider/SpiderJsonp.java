package com.huuuxi.bigdata.spider;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**   
 * @Title: SpiderJsonp.java 
 * @Description:  jsonp 返回处理的数据
 * @author huuuxi 
 * @Email huuuxi@gmail.com 
 * @date 2013-7-13 下午6:07:05 
 */
public class SpiderJsonp {

	private static Set<String> urlSet = new HashSet<String>();
	
	//private static Pattern p = Pattern.compile( "^(((http|https)://(www.|([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}:[0-9]+/)?){1}.+){1}",Pattern.CASE_INSENSITIVE);  
	
	private static String regx = "^(((http|https)://(www.|([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}:[0-9]+/)?){1}.+){1}";
	
	public static void main(String[] args) {
		String url = "http://www.sina.com.cn/";
		spiderInternet(url, "");
	}
	
	private static void spiderInternet(String baseUrl,String exUrl){
		if (baseUrl.endsWith("/") && exUrl.startsWith("/")) {
			baseUrl = baseUrl.substring(0,baseUrl.length()-1);
		}
		String new_url = baseUrl + exUrl;
		if (urlSet.contains(new_url)) {
			return;
		}
		System.out.println(new_url);
		try {
			Document doc = Jsoup.connect(new_url).get();
			urlSet.add(new_url);
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				String href  = link.attr("href");
				if (href == null || "".equals(href) || "#".endsWith(href) || href.startsWith("javascript:")) {
					continue;
				}else if (href.matches(regx)) {
					spiderInternet(href, "");
				}else {
					spiderInternet(baseUrl, href);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("抓取错误，错误url："+new_url);
		}
	}
}
