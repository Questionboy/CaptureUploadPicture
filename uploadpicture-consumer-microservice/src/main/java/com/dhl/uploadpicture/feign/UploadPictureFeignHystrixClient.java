package com.dhl.uploadpicture.feign;

import com.dhl.uploadpicture.config.FeignSpringFormEncoder;
import com.dhl.uploadpicture.feign.UploadPictureFeignHystrixClient.HystrixClientFallback;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by daihl on 2017/10/18.
 */
@FeignClient(name = "uploadpicture-microservice", configuration = UploadPictureFeignHystrixClient.MultipartSupportConfig.class, fallback = HystrixClientFallback.class)
public interface UploadPictureFeignHystrixClient {
    /*@PostMapping("/uploadpicture")
    public String uploadPictureCmdFeign(@RequestPart(value = "file") MultipartFile[] file);

    @Component
    static class HystrixClientFallback implements UploadPictureFeignHystrixClient {
        @Override
        public String uploadPictureCmdFeign(MultipartFile[] files) {
            return "fail";
        }
    }
    */
    @RequestMapping(method = RequestMethod.POST, value = "/uploadpicture")
    public String uploadPictureCmdFeign(@PathVariable("files") MultipartFile[] files);

    @Component
    static class HystrixClientFallback implements UploadPictureFeignHystrixClient {
        @Override
        public String uploadPictureCmdFeign(MultipartFile[] files) {
            return "fail";
        }
    }

    @Configuration
    class MultipartSupportConfig {

        @Autowired
        ObjectFactory<HttpMessageConverters> messageConverters;

        /*@Bean
        @Primary
        @Scope("prototype")
        public Encoder multipartFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }*/

        @Bean
        @Primary
        @Scope("prototype")
        public Encoder multipartFormEncoder() {
            return new FeignSpringFormEncoder();
        }

        /*@Bean
        @Primary
        @Scope("prototype")
        public Decoder decoder() {
            Decoder decoder = (response, type) -> {
                if (type instanceof Class && MultipartFile.class.isAssignableFrom((Class) type)) {
                    Collection<String> contentTypes = response.headers().get("content-type");
                    String contentType = "application/octet-stream";
                    if (contentTypes.size() > 0) {
                        String[] temp = new String[contentTypes.size()];
                        contentTypes.toArray(temp);
                        contentType = temp[0];
                    }

                    byte[] bytes = StreamUtils.copyToByteArray(response.body().asInputStream());
                    InMemoryMultipartFile inMemoryMultipartFile = new InMemoryMultipartFile("file","", contentType,bytes);
                    return inMemoryMultipartFile;
                }
                return new SpringDecoder(messageConverters).decode(response, type);
            };
            return new ResponseEntityDecoder(decoder);
        }*/
    }
}
