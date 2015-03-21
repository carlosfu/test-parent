package com.sohu.tv.index.data.engine.es;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.util.ResourceBundle;

/**
 * Created by yijunzhang on 15-2-2.
 */
public class ElasticSearchClientFactory {
    private volatile static Client client;

    /**
     * private constructor
     */
    private ElasticSearchClientFactory() {
    }

    /**
     * create a transport client
     *
     * @return
     */
    public static Client createTransportClient() {
        if (client == null) {
            synchronized (Client.class) {
                if (client != null) {
                    return client;
                }
                ResourceBundle resourceBundle = ResourceBundle.getBundle("index-engine");
                String clusterName = resourceBundle.getString("es.cluster.name");
                String timeout = resourceBundle.getString("es.client.transport.ping_timeout");
                String hosts = resourceBundle.getString("es.hosts");
                String sniff = resourceBundle.getString("es.client.transport.sniff");

                Settings settings = ImmutableSettings.settingsBuilder()
                        //sniff默认是false,但是一般设置成true,有利于集群伸缩的嗅探
                        .put("client.transport.sniff", sniff)
                        .put("cluster.name", clusterName)
                        //客户端超时
                        .put("client.transport.ping_timeout", timeout)
                        .build();
                TransportClient innerClient = new TransportClient(settings);
                String[] hostPorts = hosts.split(",");
                for (String hostPort : hostPorts) {
                    String[] array = hostPort.split(":");
                    innerClient.addTransportAddress(new InetSocketTransportAddress(array[0], Integer.parseInt(array[1])));
                }
                client = innerClient;
            }
        }
        return client;
    }


}
