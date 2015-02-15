package com.emc.vsi.providers.data.vplex;

import java.io.Serializable;

/**
 *
 * @author weichx
 *
 */
public class ${m.clsName} implements Serializable {

	<#list m.fields as f>
	private ${f.type} ${f.name};
	</#list>
	
	<#list m.fields as f>
	public ${f.type} get${f.name ? cap_first}() {
		return ${f.name};
	}

	public void set${f.name ? cap_first}(${f.type} ${f.name}) {
		this.${f.name} = ${f.name};
	}
		
	</#list>
}
