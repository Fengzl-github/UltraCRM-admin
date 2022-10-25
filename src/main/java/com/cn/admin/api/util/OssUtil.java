package com.cn.admin.api.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author Fengzl
 * @Date 2022/10/25 10:13
 * @Desc
 **/
public class OssUtil {

    private static final String ENDPOINT = "";
    private static final String ACCESS_KEY_ID = "";
    private static final String ACCESS_KEY_SECRET = "";
    private static final String BUCKET_NAME = "";


    public static void main(String[] args) {

        String filePath = "C:\\Users\\zaiAdmin\\Desktop\\20220920masterdetailpage.pdf";
        String objectPath = "brand/master/20220920masterdetailpage.pdf";
        upload(filePath, objectPath);

    }


    /**
     * 简单上传
     * @param filePath 本地文件路径
     * @param objectPath oss存储路径
     */
    public static void upload(String filePath, String objectPath) {

        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            ossClient.putObject(BUCKET_NAME, objectPath, fileInputStream);
            System.out.println("上传成功");
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());

        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (FileNotFoundException fe) {
            System.out.println("Error Message:" + fe.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException ie) {
                    System.out.println("Error Message:" + ie.getMessage());
                }
            }
        }

    }
}
