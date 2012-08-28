package com.mycompany;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.wicket.protocol.http.WicketFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugWicketFilter extends WicketFilter {
	private static final Logger log = LoggerFactory
			.getLogger(DebugWicketFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		log.info(request.toString());
		StringBuilder sb = new StringBuilder("{");
		boolean first = true;
		for (Object curEntry : request.getParameterMap().entrySet()) {
			if (!first)
				sb.append(", ");
			Map.Entry<?, ?> entry = (Entry<?, ?>) curEntry;
			sb.append(entry.getKey()).append("=")
					.append(Arrays.toString((Object[]) entry.getValue()));
			first = false;
		}
		sb.append("}");
		log.info(sb.toString());
		super.doFilter(request, response, chain);
	}
}
