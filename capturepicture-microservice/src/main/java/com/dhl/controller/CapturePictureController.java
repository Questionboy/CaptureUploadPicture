package com.dhl.controller;

import com.dhl.service.CapturePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by daihl on 2017/10/18.
 */
@RestController
public class CapturePictureController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private CapturePictureService capturePictureService;

    @PostMapping("/executecmd")
    public String capturePictureCmd( @RequestParam(value="host") String host,
                                     @RequestParam(value="username") String username,
                                     @RequestParam(value="password") String password,
                                     @RequestParam(value="cmd") String cmd){
        String result = "";
        try {
            result = capturePictureService.execShellScript(host, username, password, cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 本地服务实例的信息
     * @return
     */
    @GetMapping("/instance-info")
    public ServiceInstance showInfo() {
        ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
        return localServiceInstance;
    }
}
