package cn.cwbolg.providerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //nacos客户端开启
public class ProviderDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderDemoApplication.class, args);
	}

}
