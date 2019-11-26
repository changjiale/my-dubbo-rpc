package common.request;

import java.io.Serializable;

/**
 * @author: changjiale
 * @create: 2019/11/25 17:50
 * @description:
 */
public class RpcRequest implements Serializable {
    private String className;
    private String methodName;
    private Class<?>[] types;
    private Object[] params;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getTypes() {
        return types;
    }

    public void setTypes(Class<?>[] types) {
        this.types = types;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}

