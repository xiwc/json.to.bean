package com.emc.vsi.json2Bean;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class MainHandlerTest {

	private static final String EMPTY_STRING = "";

	@Test
	public void test() {

		try {
			Configuration configuration = new Configuration(Configuration.VERSION_2_3_21);
			configuration.setClassicCompatible(true);
			configuration.setClassForTemplateLoading(this.getClass(), EMPTY_STRING);
			configuration.setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			configuration.setNumberFormat("");
			configuration.setDefaultEncoding("utf-8");
			Template template = configuration.getTemplate("Bean.ftl");

			StringWriter sw = new StringWriter();

			BeanInfo beanInfo = new BeanInfo("TestBean");
			beanInfo.getFields().add(new Field("name", String.class.getSimpleName()));
			beanInfo.getFields().add(new Field("age", String.class.getSimpleName()));

			Map<String, Object> dataModelMap = new HashMap<String, Object>();
			dataModelMap.put("m", beanInfo);

			template.process(dataModelMap, sw);

			System.out.println(sw.toString());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
