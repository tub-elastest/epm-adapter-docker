package elastest.epm.adapter.docker;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "adapter")
public class AdapterProperties {

    private int port = 50053;


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "AdapterProperties{" + "port=" + port + '}';
    }
}
