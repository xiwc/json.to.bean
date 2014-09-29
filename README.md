json2bean
=========

Convert json string to java bean.

=========

### Input like:

```json
{
  "VirtualMachineCopies": [
    {
      "id": "d141161b-8d68-4ed0-be9b-39e01ec9fdd3",
      "servicePlans": [
        {
          "id": "a4e81175-7578-47bd-b140-d6157844f198",
          "name": "Bronze"
        }
      ],
      "phasepitPhaseOptions": {
        "throttle": "4",
        "includeAllDisks": "true",
        "expireKeepCopyCount": "7",
        "rpCopyType": "CDP"
      },
      "version": "vmx-09",
      "numCPUs": "1",
      "powerState": "poweredOff"
    }
  ]
}
```

##Output like:

```
Log output:

[INFO] - force mkdir [C:\json-2-bean\].
[INFO] - start parse json object
[INFO] - private List<VirtualMachineCopy> VirtualMachineCopies;
[INFO] - JSONObject: [VirtualMachineCopy] VirtualMachineCopy
[INFO] - file [C:\json-2-bean\VirtualMachineCopy.java] exists.
[INFO] - private String id;
[INFO] - private String powerState;
[INFO] - private List<ServicePlan> servicePlans;
[INFO] - private String numCPUs;
[INFO] - private PhasepitPhaseOptions phasepitPhaseOptions;
[INFO] - private String version;
[INFO] - JSONObject: [ServicePlan] servicePlan
[INFO] - file [C:\json-2-bean\ServicePlan.java] exists.
[INFO] - private String id;
[INFO] - private String name;
[INFO] - JSONObject: [PhasepitPhaseOptions] phasepitPhaseOptions
[INFO] - file [C:\json-2-bean\PhasepitPhaseOptions.java] exists.
[INFO] - private String throttle;
[INFO] - private String includeAllDisks;
[INFO] - private String expireKeepCopyCount;
[INFO] - private String rpCopyType;
[INFO] - end parse json object

Created Java File:

VirtualMachineCopy.java

package com.emc.vsi.providers.data;

/**
*
* @author weichx
*
*/
public class VirtualMachineCopy {

private String id;
private String powerState;
private List<ServicePlan> servicePlans;
private String numCPUs;
private PhasepitPhaseOptions phasepitPhaseOptions;
private String version;

}

PhasepitPhaseOptions.java

package com.emc.vsi.providers.data;

/**
*
* @author weichx
*
*/
public class PhasepitPhaseOptions {

private String throttle;
private String includeAllDisks;
private String expireKeepCopyCount;
private String rpCopyType;

}


ServicePlan.java

package com.emc.vsi.providers.data;

/**
*
* @author weichx
*
*/
public class ServicePlan {

private String id;
private String name;

}


```


# 使用说明:

- 输入json字符串
- 输出java bean类文件


### json字符串要求(key:value value的类型要求)
- Boolean
- String
- JsonObject
- JsonArray
- 其它类型暂不支持
