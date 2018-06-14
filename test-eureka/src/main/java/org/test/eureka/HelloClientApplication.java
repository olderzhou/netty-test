package org.test.eureka;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.eureka.service.DemoService;

  
/**   
 * 此类描述的是：   
 * @author: klauszhou@wezhuiyi.com 
 * @version: 2018年6月14日 上午10:16:59    
 */   
@SpringBootApplication
@RestController
public class HelloClientApplication {

	@GetMapping("/info")
	public Map<String,Object> hello() {
		Map<String,Object> data =new HashMap<>();
		data.put("info", "for test");
		data.put("code", 0);
		return data;
	}
	
	@Autowired
	DemoService demoService;
	
	
	@GetMapping("/test")
	public Map<String,Object> test() {
		demoService.demo();
		
		Map<String,Object> data =new HashMap<>();
		data.put("info", "demoService.demo()");
		data.put("code", 1);
		return data;
	}
	

	public static void main(String[] args) {
		SpringApplication.run(HelloClientApplication.class, args);
	}
	
}

