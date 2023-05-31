package com.eskcti.algafoodapi.di.notification;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notifier.email")
public class NotifierProperties {

    private String hostServer;
    private Integer portServer;

    public String getHostServer() {
        return hostServer;
    }

    public void setHostServer(String hostServer) {
        this.hostServer = hostServer;
    }

    public Integer getPortServer() {
        return portServer;
    }

    public void setPortServer(Integer portServer) {
        this.portServer = portServer;
    }
}
