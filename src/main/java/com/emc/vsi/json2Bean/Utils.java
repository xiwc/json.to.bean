package com.emc.vsi.json2Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static String formatDate(Date date) {
		return DATE_FORMAT.format(date);
	}
}
