package consumer;

import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@FeignClient(name = "spring-cloud-eureka-client",url ="http://127.0.0.1:9091",fallback = FallbackFactory.Default.class)
interface FeignController {
    @GetMapping("/greeting")
    public String getHandler();
}

@SpringBootApplication
@EnableFeignClients
public class MyFeignClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFeignClientApplication.class, args);
    }

}

@RestController
@RequestMapping("/feignGreeting")
class MyRestController {
    @Autowired
    FeignController feignController;

    @GetMapping
    public String getHandler() {
        return feignController.getHandler();
    }
}
