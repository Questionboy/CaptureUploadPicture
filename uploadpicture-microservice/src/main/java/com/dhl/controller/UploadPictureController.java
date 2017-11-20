package com.dhl.controller;

import com.dhl.service.UploadPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by daihl on 2017/10/18.
 */
@RestController
public class UploadPictureController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private UploadPictureService uploadPictureService;

    @RequestMapping(method = RequestMethod.POST, value = "/uploadpicture")
    public String uploadPictureCmd(@PathVariable("files") MultipartFile[] files) throws IOException {
        String result = "success";
        for(int i=0; i<files.length; i++) {
            uploadPictureService.upload(files[i].getOriginalFilename(), files[i].getBytes());
        }
        //uploadPictureService.upload(files.getOriginalFilename(), files.getBytes());
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
