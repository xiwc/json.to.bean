package com.emc.vsi.json2Bean;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Main {

	private static final String FILE_PATH = "C:/json-2-bean";
	private static final String PKG_PATH = "package com.emc.vsi.providers.data;";
	private static final String AUTHOR = "weichx";

	public static void main(String[] args) throws IOException {

		FileUtils.forceMkdir(new File(FILE_PATH));

		String json = JOptionPane.showInputDialog("Input Json String");

		parseJSONObject(null, JSON.parseObject(json));

		java.awt.Desktop.getDesktop().open(new File(FILE_PATH));
		Runtime.getRuntime().exec(String.format("explorer %s", FILE_PATH));

		try {
			String[] cmd = new String[5];
			cmd[0] = "cmd";
			cmd[1] = "/c";
			cmd[2] = "start";
			cmd[3] = " ";
			cmd[4] = FILE_PATH;
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void writeln(File file, String line) throws IOException {

		if (file != null) {
			FileUtils.write(file, line, true);
			FileUtils.write(file, "\r\n", true);
		}
	}

	private static void parseJSONObject(String name, JSONObject jsonObject) throws IOException {

		File file = null;

		if (!StringUtils.isEmpty(name)) {
			file = new File(String.format("%s/%s.java", FILE_PATH, StringUtils.capitalize(name)));

			if (file.exists()) {
				FileUtils.write(file, "");
			}

			writeln(file, PKG_PATH);
			writeln(file, "");
			writeln(file, "/**");
			writeln(file, "*");
			writeln(file, "* @author " + AUTHOR);
			writeln(file, "*");
			writeln(file, "*/");
			writeln(file, "public class " + StringUtils.capitalize(name) + " {");
			writeln(file, "");
		}

		Map<String, Object> map = new HashMap<String, Object>();

		for (String key : jsonObject.keySet()) {

			Object object = jsonObject.get(key);

			if (object instanceof String) {
				System.out.println(String.format("private String %s;", key));
				writeln(file, String.format("private String %s;", key));
			} else if (object instanceof Boolean) {
				System.out.println(String.format("private Boolean %s;", key));
				writeln(file, String.format("private Boolean %s;", key));
			} else if (object instanceof JSONObject) {
				System.out.println(String.format("private %s %s;", StringUtils.capitalize(key), key));
				writeln(file, String.format("private %s %s;", StringUtils.capitalize(key), key));
				map.put(key, object);
			} else if (object instanceof JSONArray) {

				String clsName = key;

				if (key.endsWith("ies")) {
					clsName = key.substring(0, key.length() - 3) + "y";
				} else if (key.endsWith("s")) {
					clsName = key.substring(0, key.length() - 1);
				}

				System.out.println(String.format("private List<%s> %s;", StringUtils.capitalize(clsName), key));
				writeln(file, String.format("private List<%s> %s;", StringUtils.capitalize(clsName), key));
				map.put(clsName, ((JSONArray) object).getJSONObject(0));
			} else {
				System.err.println(String.format("Key: %s, Value: %s, Unrecognized Type: %s", key,
						String.valueOf(object), object.getClass().getName()));
			}
		}

		writeln(file, "");
		writeln(file, "}");

		for (String key : map.keySet()) {

			Object object = map.get(key);

			if (object instanceof JSONObject) {

				System.out.println();
				System.out.println(String.format("JSONObject: [%s] %s", StringUtils.capitalize(key), key));

				parseJSONObject(key, (JSONObject) object);

				System.out.println();
			}
		}
	}
}
