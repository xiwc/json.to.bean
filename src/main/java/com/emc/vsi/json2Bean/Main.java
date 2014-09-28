package com.emc.vsi.json2Bean;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Main {

	private static final String FILE_PATH = "C:/json-2-bean";

	public static void main(String[] args) throws IOException {

		FileUtils.forceMkdir(new File(FILE_PATH));

		String json = JOptionPane.showInputDialog("Input Json String");

		parseJSONObject(JSON.parseObject(json));

	}

	private static void parseJSONObject(JSONObject jsonObject) {

		Map<String, Object> map = new HashMap<String, Object>();

		for (String key : jsonObject.keySet()) {

			Object object = jsonObject.get(key);

			if (object instanceof String) {
				System.out.println(String.format("private String %s;", key));
			} else if (object instanceof Boolean) {
				System.out.println(String.format("private Boolean %s;", key));
			} else if (object instanceof JSONObject) {
				System.out.println(String.format("private %s %s;", StringUtils.capitalize(key), key));
				map.put(key, object);
			}
		}

		for (String key : map.keySet()) {

			Object object = map.get(key);

			if (object instanceof JSONObject) {

				System.out.println();
				System.out.println(String.format("JSONObject: [%s] %s", StringUtils.capitalize(key), key));

				parseJSONObject((JSONObject) object);

				System.out.println();
			}
		}
	}
}
