package com.sinothk.redheart.utils;

import com.sinothk.base.keyValue.Constant;
import org.springframework.web.multipart.MultipartFile;

import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    private static FileManager fileManager;

    public static FileManager getInstance() {
        if (fileManager == null) {                // Single Checked
            synchronized (FileManager.class) {
                if (fileManager == null) {        // Double checked
                    fileManager = new FileManager();
                }
            }
        }
        return fileManager;
    }



    public void saveFileIntoDisk(String sysType, String virtualPath, String locPath, String fileNewName, MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return;
        }

        if (Constant.LINUX.equals(sysType)) {

        } else {
            new Thread(() -> {
                try {
                    String allPath = virtualPath + locPath;
                    File fp = new File(allPath);
                    if (!fp.exists()) {
                        fp.mkdirs();
                    }

                    Path path = fp.toPath().resolve(fileNewName);

                    Files.copy(multipartFile.getInputStream(), path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
