// Auto Generated Code by Tool [JsonToBean]
// Author Mail: weicheng.xi@emc.com
// Version: 1.0.0

import java.io.Serializable;

/**
 * 
 * @author ${c.author}
 * 
 * @date ${c.date}
 * 
 */
public class ${m.clsName} implements Serializable {

	<#list m.fields as f>
	<#if c.addValueAsComment && f.value>
	/** eg: ${f.value} **/
	</#if>
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
