package org.koreait.commons;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class UrlUtil {
	@Autowired
	private HttpServletRequest request;

	public String getPageUrl(String url, int page){
		if(!url.contains("?")){
			url += "?";
		}

		String qs = request.getQueryString();
		//System.out.println("qs : " + qs);
		if (qs != null && !qs.isBlank()){
			qs = Arrays.stream(qs.split("&")).filter(s-> !s.contains("page")).collect(Collectors.joining("&"));

			if(!qs.isBlank()){
				url += qs + "&";
			}
		}
		url += "page=" + page;

		return url;
	}
/*
	public String getPageUrl(String url, int page, String sort){
		System.out.println("url : " + url);
		if(!url.contains("?")){
			url += "?";
		}

		String qs = request.getQueryString();
		System.out.println("qs : " + qs);
		if (qs != null && !qs.isBlank()){
			if(qs.contains("sort")){
				return url+"sort="+sort+"&page="+page;
			}
			qs = Arrays.stream(qs.split("&")).filter(s-> !s.contains("page")).collect(Collectors.joining("&"));

			if(!qs.isBlank()){
				url += qs + "&";
			}
		}
		url += "sort=" + sort + "&page=" + page;

		return url;
	}

 */
}
