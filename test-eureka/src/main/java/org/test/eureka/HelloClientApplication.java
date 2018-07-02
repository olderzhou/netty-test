package org.test.eureka;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.eureka.service.DemoService;
import org.test.eureka.service.ZooKeeperService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Maps;

/**
 * 此类描述的是：
 * 
 * @author: klauszhou@wezhuiyi.com
 * @version: 2018年6月14日 上午10:16:59
 */
@SpringBootApplication
@RestController
public class HelloClientApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloClientApplication.class);

	@GetMapping("/info")
	public Map<String, Object> hello() {
		Map<String, Object> data = Maps.newHashMapWithExpectedSize(256);
		data.put("info", "for test");
		data.put("code", 0);
		return data;
	}

	@Autowired
	DemoService demoService;

	@Autowired
	ZooKeeperService keeperService;

	@GetMapping("/test")
	public Map<String, Object> test() {
		demoService.demo();
//		Map<String, Object> data = new HashMap<>(256);
		Map<String, Object> data = Maps.newHashMapWithExpectedSize(256);
		data.put("info", "demoService.demo()");
		data.put("code", 1);
		return data;
	}

	@GetMapping("/testZooKeeperService")
	public Map<String, Object> testZooKeeperService() {

		Map<String, Object> data = Maps.newHashMapWithExpectedSize(256);
		data.put("info", "demoService.demo()");
		data.put("code", 1);
		LOGGER.info("loadDataSourceMap is {}",keeperService.loadDataSourceMap());
		LOGGER.info("loadShardingRuleConfig is {}",keeperService.loadShardingRuleConfig());
		return data;
	}
	
	
	@Bean("objectMapper")
	public ObjectMapper myMapper() {
		return new ObjectMapper()
				.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
				.disable(SerializationFeature.FAIL_ON_SELF_REFERENCES);
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloClientApplication.class, args);
	}

}
