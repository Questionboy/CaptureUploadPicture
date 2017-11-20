package com.dhl.capturepicture.controller;

import com.dhl.capturepicture.feign.CapturePictureFeignHystrixClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by daihl on 2017/10/18.
 */
@RestController
public class FeignHystrixController {
    @Autowired
    private CapturePictureFeignHystrixClient capturePictureFeignHystrixClient;

    @PostMapping("/executecmd")
    public String capturePictureCmdFeign(@RequestParam(value="host") String host,
                                         @RequestParam(value="username") String username,
                                         @RequestParam(value="password") String password,
                                         @RequestParam(value="cmd") String cmd) {
        return capturePictureFeignHystrixClient.capturePictureCmdFeign(host, username, password, cmd);
    }
}
