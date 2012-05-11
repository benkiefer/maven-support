package org.burgers.maven.support.enforcer.rules;

import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import java.io.IOException;

public class ActiveMqHelper {
    private MBeanServerConnectionFactoryBean mBean = new MBeanServerConnectionFactoryBean();

    public void connect(String activeMqUrl) throws IOException {
/*
        don't forget the following things in your activemq.xml
            --    <managementContext>
                        <managementContext createConnector="true"/>
                  </managementContext>
            -- <broker useJmx="true">
 */
        try {
            mBean.setServiceUrl(activeMqUrl);
            mBean.afterPropertiesSet();
            mBean.getObject();
        } finally {
            mBean.destroy();
        }
    }

    public void setmBean(MBeanServerConnectionFactoryBean bean) {
        this.mBean = bean;
    }
}