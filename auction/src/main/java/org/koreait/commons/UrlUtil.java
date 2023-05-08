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
		url += "?";
		String qs = request.getQueryString();
		if (qs != null && !qs.isBlank()){
			qs = Arrays.stream(qs.split("&")).filter(s-> !s.contains("page")).collect(Collectors.joining("&"));

			if(!qs.isBlank()){
				url += qs + "&";
			}
		}
		url += "page=" + page;

		return url;
	}
}
