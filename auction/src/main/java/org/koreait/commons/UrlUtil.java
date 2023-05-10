package org.koreait.commons;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UrlUtil {
	@Autowired
	private HttpServletRequest request;

	private List<String> keys;

	public String getPageUrl(String url, int page){

		if(url.contains("?")) {
			String[] urls = url.split("\\?");
			if (urls.length > 1) {
				keys = Arrays.stream(urls[1].split("&")).map(s -> s.split("=")[0]).toList();
			}
			url += "&";
		} else {
			url += "?";
		}

		String qs = request.getQueryString();
		if (qs != null && !qs.isBlank()){
			qs = Arrays.stream(qs.split("&"))
					.filter(s-> !s.contains("page"))
					.filter(this::keyExists)
					.collect(Collectors.joining("&"));

			if(!qs.isBlank()){
				url += qs + "&";
			}
		}
		url += "page=" + page;
		return url;
	}

	private boolean keyExists(String str) {
		String key = str.split("=")[0];
		if (keys != null && keys.contains(key)) {
			return false;
		}

		return true;
	}
}