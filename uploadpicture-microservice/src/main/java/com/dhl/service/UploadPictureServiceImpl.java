package com.dhl.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * UploadPictureServiceImpl class
 *
 * @author daihongliang
 * @date 2017/10/17
 */
@Service
public class UploadPictureServiceImpl implements UploadPictureService {

    @Value("${image.uploadpath}")
    private String imageUploadPath;

    @Override
    public void upload(String filename, byte[] data){
        try {
            FileOutputStream fos = new FileOutputStream(imageUploadPath + filename);
            fos.write(data);
            fos.close();
            System.out.println("-------文件上传成功！-------------");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
