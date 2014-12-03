package com.emc.vsi.json2Bean;

import java.util.ArrayList;
import java.util.List;

public class BeanInfo {

	private String clsName;

	private List<Field> fields = new ArrayList<Field>();

	public BeanInfo() {
		super();
	}

	public BeanInfo(String clsName) {
		super();
		this.clsName = clsName;
	}

	public String getClsName() {
		return clsName;
	}

	public void setClsName(String clsName) {
		this.clsName = clsName;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

}