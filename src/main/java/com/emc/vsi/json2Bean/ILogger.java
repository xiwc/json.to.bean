package com.emc.vsi.json2Bean;

public interface ILogger {

	void info(String msg, Object... params);

	void error(String msg, Object... params);
}
