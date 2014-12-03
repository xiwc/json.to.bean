package com.emc.vsi.providers.data;

/**
 *
 * @author weichx
 *
 */
public class ${m.clsName} {

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
