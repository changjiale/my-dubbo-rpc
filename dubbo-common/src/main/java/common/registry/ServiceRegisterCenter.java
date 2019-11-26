package common.registry;

/**
 * @author: changjiale
 * @create: 2019/11/25 18:50
 * @description:
 */
public interface ServiceRegisterCenter {

    /**
     * 服务注册
     * @param serverName
     * @param serverAddress
     */
    void register(String serverName, String serverAddress);

    /**
     * 服务发现
     * @param serverName
     * @return
     */
    String discover(String serverName);

}
