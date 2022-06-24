package com.project.shop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ImageService {

    @Value("D:/MyProject/Image/shop")
    private String imgPath;

    public String getImgPath() {
        return imgPath;
    }

    public String transferImg(MultipartFile file, int ran, int type) throws IOException {

        String originName = file.getOriginalFilename();
        String ext = originName.substring(originName.lastIndexOf("."));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");


        String fname = "";


        if (type == 0) {
            fname = dateFormat.format(new Date()) + "_" + ran + ext;
        } else {
            fname = dateFormat.format(new Date()) + "_" + ran + "d" + ext;
        }
        file.transferTo(new File(imgPath + "/" + fname));

        return fname;
    }

    public boolean imgDelete(String img) {
        if (!imgPath.isEmpty()) {
            File file = new File(imgPath + "/" + img);

            if (file.exists()) {
                if (file.delete()) {
                    return true;
                } else{
                    return false;
                }
            }
        }

        return false;
    }
}
