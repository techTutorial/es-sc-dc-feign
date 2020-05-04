package example.spring.cloud.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import example.spring.cloud.feign.resource.GreetingsFeignClientService3;

@FeignClient(name="discovery-client",
	//url="http://localhost:8181",
	fallback=GreetingsFeignClient3.GreetingClientFallback3.class)
public interface GreetingsFeignClient3 extends GreetingsFeignClientService3 {
	
    @Component
    public static class GreetingClientFallback3 implements GreetingsFeignClient3 {
    	
        @Override
        public String getGreetings3(@PathVariable("localeId") String localeId) {
            return localeId + ": Fallback error response!";
        }
        
    }
    
}
