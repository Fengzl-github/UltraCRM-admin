package com.cn.admin.api.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Author Fengzl
 * @Date 2022/8/17 17:45
 * @Desc 生成缩略图
 **/
public class ImageUtil {

    public static void main(String[] args) throws IOException {

        System.out.println(thumbnailImage("C:\\Users\\zaiAdmin\\Pictures\\Saved Pictures\\x2.jpeg"));

    }


    /**
     * 文件后缀分隔符
     */
    private static final String DEFAULT_SEPARATOR = ".";
    /**
     * 缩略图文件名前缀
     */
    private static final String DEFAULT_PREFIX = "thumbnail.";


    public static String thumbnailImage(String imagePath) {
        return thumbnailImage(imagePath, 80, 80);
    }


    public static String thumbnailImage(String imagePath, int width, int height) {
        System.out.println("开始执行...");
        String newImagePath;
        File imgFile = new File(imagePath);
        //文件存在
        if (imgFile.exists() && imgFile.isFile()) {
            try {
                String imgName = imgFile.getName();
                String parentPath = imgFile.getParent();
                String newImgName = DEFAULT_PREFIX + imgName;

                String types = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = "";
                // 获取文件后缀
                if (imgName.contains(DEFAULT_SEPARATOR)) {
                    suffix = imgName.substring(imgName.lastIndexOf(DEFAULT_SEPARATOR) + 1);
                }
                // 判断是否合法
                if (StringUtils.isEmpty(suffix) || !types.toLowerCase().contains(suffix.toLowerCase())) {
                    throw new RuntimeException("图片不合法，支持" + types);
                }
                Thumbnails.of(imgFile)
                        .size(width, height)
                        .outputFormat("jpg")
                        .toFiles(Rename.PREFIX_DOT_THUMBNAIL);

                newImagePath = parentPath + File.separator + newImgName;
                if (!new File(newImagePath).exists()) {
                    throw new RuntimeException("生产缩略图失败: 保存缩略图片失败");
                }
            } catch (Exception e) {
                throw new RuntimeException("生产缩略图失败: " + e.getMessage());
            }
        } else {
            throw new RuntimeException("文件不存在，或不是一个文件");
        }

        return newImagePath;

    }
}
