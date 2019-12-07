package common.loadbalance;

import java.util.List;

public interface LoadBalance {
    String select(String serverName, List<String> repos);
}
