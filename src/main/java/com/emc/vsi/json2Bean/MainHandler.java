package com.emc.vsi.json2Bean;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MainHandler {

	public static final String FILE_PATH = "C:\\json-2-bean\\";
	private static final String PKG_PATH = "package com.emc.vsi.providers.data;";
	private static final String AUTHOR = "weichx";
	private static ILogger logger;
	private static Map<String, Object> objMap = new HashMap<String, Object>();

	public static void handle(String json, ILogger logger) throws Exception {

		MainHandler.logger = logger;

		logger.info("force mkdir [%s].", FILE_PATH);
		FileUtils.forceMkdir(new File(FILE_PATH));

		objMap.clear();

		logger.info("start parse json object");
		parseJSONObject(null, JSON.parseObject(json));
		logger.info("end parse json object");
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

			String clsName = StringUtils.capitalize(name);

			file = new File(String.format("%s%s.java", FILE_PATH, clsName));

			if (file.exists()) {

				clsName = String.format("%s%s", StringUtils.capitalize(name), String.valueOf(new Date().getTime()));

				file = new File(String.format("%s%s.java", FILE_PATH, clsName));

				FileUtils.write(file, "");

				logger.info("file [%s] exists, will create a new file[%s]",
						String.format("%s%s.java", FILE_PATH, StringUtils.capitalize(name)), file.getParent());
			} else {
				logger.info("file [%s] not exists, will create new.",
						String.format("%s%s.java", FILE_PATH, StringUtils.capitalize(name)));
			}

			writeln(file, PKG_PATH);
			writeln(file, "");
			writeln(file, "/**");
			writeln(file, "*");
			writeln(file, "* @author " + AUTHOR);
			writeln(file, "*");
			writeln(file, "*/");
			writeln(file, "public class " + clsName + " {");
			writeln(file, "");
		}

		Map<String, Object> map = new HashMap<String, Object>();

		for (String key : jsonObject.keySet()) {

			Object object = jsonObject.get(key);

			if (object instanceof String || object == null) {
				System.out.println(String.format("private String %s;", key));
				logger.info("private String %s;", key);
				writeln(file, String.format("private String %s;", key));
			} else if (object instanceof Boolean) {
				System.out.println(String.format("private Boolean %s;", key));
				logger.info("private Boolean %s;", key);
				writeln(file, String.format("private Boolean %s;", key));
			} else if (object instanceof Integer) {
				System.out.println(String.format("private Integer %s;", key));
				logger.info("private Integer %s;", key);
				writeln(file, String.format("private Integer %s;", key));
			} else if (object instanceof Long) {
				System.out.println(String.format("private Long %s;", key));
				logger.info("private Long %s;", key);
				writeln(file, String.format("private Long %s;", key));
			} else if (object instanceof Short) {
				System.out.println(String.format("private Short %s;", key));
				logger.info("private Short %s;", key);
				writeln(file, String.format("private Short %s;", key));
			} else if (object instanceof Character) {
				System.out.println(String.format("private Character %s;", key));
				logger.info("private Character %s;", key);
				writeln(file, String.format("private Character %s;", key));
			} else if (object instanceof JSONObject) {

				String newK = key;

				if (objMap.containsKey(key)) {
					newK = key + new Date().getTime();
				}

				objMap.put(newK, null);

				System.out.println(String.format("private %s %s;", StringUtils.capitalize(newK), newK));
				logger.info("private %s %s;", StringUtils.capitalize(newK), newK);
				writeln(file, String.format("private %s %s;", StringUtils.capitalize(newK), newK));
				map.put(newK, object);
			} else if (object instanceof JSONArray) {

				String clsName = key;

				if (key.endsWith("ies")) {
					clsName = key.substring(0, key.length() - 3) + "y";
				} else if (key.endsWith("s")) {
					clsName = key.substring(0, key.length() - 1);
				}

				if (objMap.containsKey(clsName)) {
					clsName = clsName + new Date().getTime();
				}

				objMap.put(clsName, null);

				System.out.println(String.format("private List<%s> %s;", StringUtils.capitalize(clsName), key));
				logger.info("private List<%s> %s;", StringUtils.capitalize(clsName), key);
				writeln(file, String.format("private List<%s> %s;", StringUtils.capitalize(clsName), key));
				map.put(clsName, ((JSONArray) object).getJSONObject(0));
			} else {
				System.err.println(String.format("Key: %s, Value: %s, Unrecognized Type: %s", key,
						String.valueOf(object), object.getClass().getName()));
				logger.error("Key: %s, Value: %s, Unrecognized Type: %s", key, String.valueOf(object), object
						.getClass().getName());
			}
		}

		writeln(file, "");
		writeln(file, "}");

		for (String key : map.keySet()) {

			Object object = map.get(key);

			if (object instanceof JSONObject) {

				System.out.println();
				System.out.println(String.format("JSONObject: [%s] %s", StringUtils.capitalize(key), key));
				logger.info("JSONObject: [%s] %s", StringUtils.capitalize(key), key);

				parseJSONObject(key, (JSONObject) object);

				System.out.println();
			}
		}
	}
}
