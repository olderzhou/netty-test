/**   
 * 文件名：ZooKeeperService.java   
 *   
 * 版本信息：   
 * 日期：2018年6月22日 下午5:15:16  
 * Copyright @Zhuiyi Inc 2018    
 * 版权所有   
 *   
 */
package org.test.eureka.service;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaxxer.hikari.HikariDataSource;

import io.shardingsphere.core.api.config.ShardingRuleConfiguration;
import io.shardingsphere.jdbc.orchestration.internal.config.ConfigurationNode;
import io.shardingsphere.jdbc.orchestration.internal.config.ConfigurationService;
//import io.shardingsphere.jdbc.orchestration.internal.json.DataSourceJsonConverter;
import io.shardingsphere.jdbc.orchestration.reg.api.RegistryCenter;

/**
 * 此类描述的是：
 * 
 * @author: niklauszhou@gmail.com
 * @version: 2018年6月22日 下午5:15:16
 */
@Service
public class ZooKeeperService {
	
	@Autowired
    private RegistryCenter regCenter;
	
	@Autowired
	private ConfigurationService configService;

	
	@Autowired
	private  ConfigurationNode configNode ;
	
	public Map<String,DataSource> testDataSource() {
		
		Map<String, DataSource> datasourceMap = null;
		
		String datasource = regCenter.get("/yiconnect_ds_sharding_ms_test/yiconnect_ds_sharding_ms/config/datasource");
		System.out.println(datasource);
		return datasourceMap;
	}  
	
	public Map<String, DataSource> loadDataSourceMap() {
//		Map<String, DataSource> dataSources = DataSourceJsonConverter.fromJson(regCenter.getDirectly(configNode.getFullPath(ConfigurationNode.DATA_SOURCE_NODE_PATH)));
		HikariDataSource dataMaster = new HikariDataSource();
		dataMaster.setDriverClassName("com.mysql.jdbc.Driver");
		dataMaster.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/demo_ds_master_2");
		dataMaster.setUsername("root");
		dataMaster.setPassword("123456");
		dataMaster.setMinimumIdle(1);
		dataMaster.setMaximumPoolSize(30);
		dataMaster.setAutoCommit(true);
		dataMaster.setIdleTimeout(60000);
		dataMaster.setMaxLifetime(18000000);
		dataMaster.setConnectionTestQuery("SELECT 1");
		dataMaster.setConnectionTimeout(60000);
		HikariDataSource dataSlave1 = new HikariDataSource();
		dataSlave1.setDriverClassName("com.mysql.jdbc.Driver");
		dataSlave1.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/demo_ds_master_2");
		dataSlave1.setUsername("root");
		dataSlave1.setPassword("123456");
		dataSlave1.setMinimumIdle(1);
		dataSlave1.setMaximumPoolSize(30);
		dataSlave1.setAutoCommit(true);
		dataSlave1.setIdleTimeout(60000);
		dataSlave1.setMaxLifetime(18000000);
		dataSlave1.setConnectionTestQuery("SELECT 1");
		dataSlave1.setConnectionTimeout(60000);
		HikariDataSource dataSlave2 = new HikariDataSource();
		dataSlave2.setDriverClassName("com.mysql.jdbc.Driver");
		dataSlave2.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/demo_ds_master_2");
		dataSlave2.setUsername("root");
		dataSlave2.setPassword("123456");
		dataSlave2.setMinimumIdle(1);
		dataSlave2.setMaximumPoolSize(30);
		dataSlave2.setAutoCommit(true);
		dataSlave2.setIdleTimeout(60000);
		dataSlave2.setMaxLifetime(18000000);
		dataSlave2.setConnectionTestQuery("SELECT 1");
		dataSlave2.setConnectionTimeout(60000);
//		dataSources.put("ds_master_2", dataMaster);
//		dataSources.put("ds-master-2-slave-0", dataSlave1);
//		dataSources.put("ds-master-2-slave-2", dataSlave2);
//		
//		regCenter.persist(configNode.getFullPath(ConfigurationNode.DATA_SOURCE_NODE_PATH), DataSourceJsonConverter.toJson(dataSources));
//		return DataSourceJsonConverter.fromJson(regCenter.getDirectly(configNode.getFullPath(ConfigurationNode.DATA_SOURCE_NODE_PATH)));
		return null;
	}
	
	public ShardingRuleConfiguration loadShardingRuleConfig() {
		return  configService.loadShardingRuleConfiguration();
	}
	
}
