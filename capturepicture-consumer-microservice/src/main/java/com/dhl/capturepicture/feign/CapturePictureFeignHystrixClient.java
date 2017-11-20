package com.dhl.capturepicture.feign;

import com.dhl.capturepicture.feign.CapturePictureFeignHystrixClient.HystrixClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * Created by daihl on 2017/10/18.
 */
@FeignClient(name = "caputrepicture-microservice", fallback = HystrixClientFallback.class)
public interface CapturePictureFeignHystrixClient {
    @PostMapping("/executecmd")
    public String capturePictureCmdFeign(@RequestParam(value="host") String host,
                                         @RequestParam(value="username") String username,
                                         @RequestParam(value="password") String password,
                                         @RequestParam(value="cmd") String cmd);

    @Component
    static class HystrixClientFallback implements CapturePictureFeignHystrixClient {
        @Override
        public String capturePictureCmdFeign(String host, String username, String password, String cmd) {
            return "fail";
        }
    }
}
