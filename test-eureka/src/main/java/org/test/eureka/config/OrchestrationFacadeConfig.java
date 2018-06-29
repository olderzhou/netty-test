/**   
 * 文件名：OrchestrationFacade.java   
 *   
 * 版本信息：   
 * 日期：2018年6月22日 下午7:26:32  
 * Copyright @Zhuiyi Inc 2018    
 * 版权所有   
 *   
 */
package org.test.eureka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import io.shardingsphere.jdbc.orchestration.internal.config.ConfigurationNode;
import io.shardingsphere.jdbc.orchestration.internal.config.ConfigurationService;
import io.shardingsphere.jdbc.orchestration.reg.api.RegistryCenter;
import io.shardingsphere.jdbc.orchestration.reg.api.RegistryCenterConfiguration;
import io.shardingsphere.jdbc.orchestration.reg.etcd.EtcdConfiguration;
import io.shardingsphere.jdbc.orchestration.reg.etcd.EtcdRegistryCenter;
import io.shardingsphere.jdbc.orchestration.reg.zookeeper.ZookeeperConfiguration;
import io.shardingsphere.jdbc.orchestration.reg.zookeeper.ZookeeperRegistryCenter;
import io.shardingsphere.jdbc.orchestration.spring.boot.orchestration.SpringBootOrchestrationConfigurationProperties;

/**
 * 此类描述的是：
 * 
 * @author: klauszhou@wezhuiyi.com
 * @version: 2018年6月22日 下午7:26:32
 */
@Component
public class OrchestrationFacadeConfig {

	@Autowired
	private SpringBootOrchestrationConfigurationProperties orchestrationProperties;
	
	@Bean
    private RegistryCenter createRegistryCenter() {
		final RegistryCenterConfiguration regCenterConfig = orchestrationProperties.getOrchestrationConfiguration().getRegCenterConfig();
        Preconditions.checkNotNull(regCenterConfig, "Registry center configuration cannot be null.");
        if (regCenterConfig instanceof ZookeeperConfiguration) {
            return new ZookeeperRegistryCenter((ZookeeperConfiguration) regCenterConfig);
        }
        if (regCenterConfig instanceof EtcdConfiguration) {
            return new EtcdRegistryCenter((EtcdConfiguration) regCenterConfig);
        }
        throw new UnsupportedOperationException(regCenterConfig.getClass().getName());
    }
	
	@Bean
	private ConfigurationService createConfigurationService() {
		return new ConfigurationService(orchestrationProperties.getOrchestrationConfiguration().getName(), createRegistryCenter());
	}
	
	@Bean
	private ConfigurationNode createConfigurationNode() {
		return new ConfigurationNode(orchestrationProperties.getOrchestrationConfiguration().getName());
	}
	
	
	
	
	
	
}
