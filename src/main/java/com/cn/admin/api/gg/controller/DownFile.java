package com.cn.admin.api.gg.controller;

import org.apache.jasper.tagplugins.jstl.core.Out;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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


    public static void getfileload(String urlPath, String filePath) {
        long start = System.currentTimeMillis();
        try {
            URL url = new URL(urlPath);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) urlConnection;
            httpConn.connect();
            System.out.println("获取连接:" + httpConn.getResponseMessage());
            if (httpConn.getResponseCode() == 200) {
                InputStream inputStream = httpConn.getInputStream();
                int contentLength = httpConn.getContentLength();
                System.out.println("获取url资源完成,文件大小:" + contentLength / (1024 * 1024) + "MB, 用时:" + (System.currentTimeMillis() - start));
                try (BufferedInputStream bin = new BufferedInputStream(inputStream);
                     BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File(filePath)))) {
                    System.out.println("写入文件开始-----");
                    long l = System.currentTimeMillis();
                    int size = 0;
                    byte[] buf = new byte[1024 * 10];
                    while ((size = bin.read(buf)) != -1) {
                        bout.write(buf, 0, size);
                    }
                    System.out.println("写入文件结束,用时-----" + (System.currentTimeMillis() - l));
                }
                httpConn.disconnect();
            }
            System.out.println("操作结束,用时:" + (System.currentTimeMillis() - start));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {


//        getfileload("https://orthodontic-maiqi-cad.oss-cn-beijing.aliyuncs.com/600001/ec9441d0dda94833904e77dd70a414bc/productMeshes/5_upperMesh.ply",
//                "E:\\downloadtest\\ddsp\\aaa1.ply");
//        getzipFile("https://orthodontic-maiqi-cad.oss-cn-beijing.aliyuncs.com/600001/ec9441d0dda94833904e77dd70a414bc/productMeshes/1_lowerMesh.ply",
//                "E:\\downloadtest\\ddsp\\ttest.zip");

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


    public static void getzipFile(String urlPath, String zipfilePath) {
        long start = System.currentTimeMillis();
        try {
            URL url = new URL(urlPath);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) urlConnection;
            httpConn.connect();
            System.out.println("获取连接:" + httpConn.getResponseMessage());
            if (httpConn.getResponseCode() == 200) {
                InputStream inputStream = httpConn.getInputStream();
                int contentLength = httpConn.getContentLength();
                System.out.println("获取url资源完成,文件大小:" + contentLength / (1024 * 1024) + "MB, 用时:" + (System.currentTimeMillis() - start));
                try (BufferedInputStream bin = new BufferedInputStream(inputStream);
                     ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(new File(zipfilePath)));
                     BufferedOutputStream bout = new BufferedOutputStream(zout)) {
                    System.out.println("写入文件开始-----");

                    zout.putNextEntry(new ZipEntry("bbbsb.ply"));
                    long l = System.currentTimeMillis();
                    int size = 0;
                    while ((size = bin.read()) != -1) {
                        bout.write(size);
                    }
                    System.out.println("写入文件结束,用时-----" + (System.currentTimeMillis() - l));
//                    zout.closeEntry();
                }
                httpConn.disconnect();
            }
            System.out.println("操作结束,用时:" + (System.currentTimeMillis() - start));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void downZipFile(List<String> fileList) {
        long start = System.currentTimeMillis();
        File zipFile = new File("E:\\downloadtest\\ddsp\\model.zip");

        //压缩文件
        ZipOutputStream zout = null;
        try {
            zout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
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
                BufferedInputStream bin = new BufferedInputStream(inputStream);
                int size = 0;
                while ((size = bin.read()) != -1) {
                    zout.write(size);
                }
                System.out.println("写入压缩文件,用时:" + (System.currentTimeMillis() - l));

                bin.close();
                zout.closeEntry();
                httpConn.disconnect();

            }
            System.out.println("整体用时:" + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert zout != null;
                zout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
