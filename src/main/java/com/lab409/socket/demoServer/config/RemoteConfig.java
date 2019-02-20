package com.lab409.socket.demoServer.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//@Configuration   cassadra 数据库服务器配置
public class RemoteConfig {
    @Value("${remote.cassadra.host}")
    private String host;

    @Value("${remote.cassadra.port}")
    private Integer port;

    @Bean
    @Qualifier("cassadra_session")
    @Scope("singleton")
    public Session session() {
        Cluster.Builder b = Cluster.builder().addContactPoint(host);
        if (port != null) {
            b.withPort(port);
        }
        Session session = b.build().connect();
        createKeyspace(session, "ThunderAnalysis", "SimpleStrategy", 1);
        return session;

    }
    private void createKeyspace(Session session ,String keyspaceName, String replicationStrategy, int replicationFactor) {
        StringBuilder sb =
                new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                        .append(keyspaceName).append(" WITH replication = {")
                        .append("'class':'").append(replicationStrategy)
                        .append("','replication_factor':").append(replicationFactor)
                        .append("};");

        String query = sb.toString();
        session.execute(query);
        session.execute(" USE " + keyspaceName + ";");
    }
}
