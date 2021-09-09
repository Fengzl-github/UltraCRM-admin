package com.cn.admin.api.gg.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *@Author fengzhilong
 *@Date 2021/9/4 10:41
 *@Desc
 **/
public class DownFile {

    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {


        List<String> fileList = new ArrayList<>();
        fileList.add("https://orthodontic-maiqi-cad.oss-cn-beijing.aliyuncs.com/600001/ec9441d0dda94833904e77dd70a414bc/productMeshes/5_upperMesh.ply");
        fileList.add("https://orthodontic-maiqi-cad.oss-cn-beijing.aliyuncs.com/600001/ec9441d0dda94833904e77dd70a414bc/productMeshes/1_lowerMesh.ply");
        fileList.add("https://orthodontic-maiqi-cad.oss-cn-beijing.aliyuncs.com/600001/ec9441d0dda94833904e77dd70a414bc/productMeshes/3_lowerMesh.ply");
        fileList.add("https://orthodontic-maiqi-cad.oss-cn-beijing.aliyuncs.com/600001/ec9441d0dda94833904e77dd70a414bc/productMeshes/9_upperMesh.ply");
        fileList.add("https://orthodontic-maiqi-cad.oss-cn-beijing.aliyuncs.com/600001/ec9441d0dda94833904e77dd70a414bc/productMeshes/5_upperMesh.ply");
        fileList.add("https://orthodontic-maiqi-cad.oss-cn-beijing.aliyuncs.com/600001/ec9441d0dda94833904e77dd70a414bc/productMeshes/1_lowerMesh.ply");
        fileList.add("https://orthodontic-maiqi-cad.oss-cn-beijing.aliyuncs.com/600001/ec9441d0dda94833904e77dd70a414bc/productMeshes/3_lowerMesh.ply");
        fileList.add("https://orthodontic-maiqi-cad.oss-cn-beijing.aliyuncs.com/600001/ec9441d0dda94833904e77dd70a414bc/productMeshes/9_upperMesh.ply");
        fileList.add("https://orthodontic-maiqi-cad.oss-cn-beijing.aliyuncs.com/600001/ec9441d0dda94833904e77dd70a414bc/productMeshes/3_lowerMesh.ply");
        fileList.add("https://orthodontic-maiqi-cad.oss-cn-beijing.aliyuncs.com/600001/ec9441d0dda94833904e77dd70a414bc/productMeshes/9_upperMesh.ply");

        downZipFile(fileList);
    }


    public static void downZipFile(List<String> fileList) {
        long start = System.currentTimeMillis();
        File zipFile = new File("D:\\downtest\\ddsp\\model2.zip");

        //压缩文件
        ZipOutputStream zout = null;
        try {
            zout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile), BUFFER_SIZE));
            for (int i = 0; i < fileList.size(); i++) {

                zout.putNextEntry(new ZipEntry("hmode" + i + ".ply"));

                URL url = new URL(fileList.get(i));
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection httpConn = (HttpURLConnection) urlConnection;
                httpConn.connect();
                System.out.println("获取连接:" + httpConn.getResponseMessage());
                InputStream inputStream = httpConn.getInputStream();
                int contentLength = httpConn.getContentLength();
                System.out.println("获取url资源完成,文件大小:" + contentLength / (1024 * 1024) + "MB");
                long l = System.currentTimeMillis();
                BufferedInputStream bin = new BufferedInputStream(inputStream, BUFFER_SIZE);
                int size;
                byte[] buf = new byte[BUFFER_SIZE];
                while ((size = bin.read(buf)) != -1) {
                    zout.write(buf, 0, size);
                }
                System.out.println("写入压缩文件,用时:" + (System.currentTimeMillis() - l));

                zout.closeEntry();
                bin.close();
                httpConn.disconnect();
            }
            System.out.println("整体用时:" + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zout != null) {
                try {
                    zout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
