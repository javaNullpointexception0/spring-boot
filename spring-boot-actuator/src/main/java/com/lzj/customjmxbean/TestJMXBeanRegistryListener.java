package com.lzj.customjmxbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 注册自定义的MBean
 */
@Component
public class TestJMXBeanRegistryListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private JmxTest jmxTest;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            //创建MBeanServer
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName objectName = new ObjectName("jmxBean:name=customJmxBean");
            //注册自定义Bean
            mBeanServer.registerMBean(jmxTest, objectName);
            //这句话非常重要，不能缺少，注册一个端口，绑定url后，客户端就可以使用rmi通过url方式来连接JMXConnectorServer
            Registry registry = LocateRegistry.createRegistry(1099);
            JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi:localhost:1099/jmxrmi");
            JMXConnectorServer jmxConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, mBeanServer);
            jmxConnectorServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
