package com.dhl.uploadpicture.controller;

import com.dhl.uploadpicture.feign.UploadPictureFeignHystrixClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by daihl on 2017/10/18.
 */
@RestController
public class FeignHystrixController {
    /*@Autowired
    private UploadPictureFeignHystrixClient uploadPictureFeignHystrixClient;
    @PostMapping("/uploadpicture")
    public String uploadPictureCmdFeign(@RequestPart(value = "file") MultipartFile[] files) {
        return uploadPictureFeignHystrixClient.uploadPictureCmdFeign(files);
    }*/
    @Autowired
    private UploadPictureFeignHystrixClient uploadPictureFeignHystrixClient;
    @RequestMapping(method = RequestMethod.POST, value = "/uploadpicture")
    public String uploadPictureCmdFeign(@PathVariable("files") MultipartFile[] files) throws IOException {
        return uploadPictureFeignHystrixClient.uploadPictureCmdFeign(files);
    }
}
