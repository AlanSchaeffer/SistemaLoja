package br.unisinos.desenvsoft3.model.generic.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HQLBuilder {

	private StringBuilder hql = new StringBuilder();
	private List<Object> params = new ArrayList<>();
	private Map<String, Object> namedParameters = new HashMap<>();
	
	public HQLBuilder append(String snippet) {
		hql.append(snippet);
		return this;
	}
	
	public HQLBuilder append(String snippet, Object param) {
		hql.append(snippet);
		
		if(snippet.contains("?")) {
			params.add(param);
		} else if(snippet.contains(":")) {
			Matcher matcher = Pattern.compile(":([a-zA-Z0-9]+)").matcher(snippet);
			if(matcher.find()) {
				namedParameters.put(matcher.group(1), param);
			}
		}
		
		return this;
	}
	
	public HQLBuilder appendOnCondition(String snippet, Object param, boolean condition) {
		if(condition) {
			append(snippet, param);
		}
		return this;
	}
	
	@Override
	public String toString() {
		return hql.toString();
	}
	
	public List<Object> params() {
		return params;
	}
	
	public Map<String, Object> namedParameters() {
		return namedParameters;
	}
}
