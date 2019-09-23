package com.wzlue.app.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.wzlue.common.utils.DateUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.UUID;
@org.springframework.stereotype.Component
public class JunitImage {
    @Value("${fileupload.filepath}")
    String filePath;//访问地址

    @Value("${fileupload.server}")
    String serverUrl;//下载地址

//    public static void main(String[] args) throws Exception {
////        String currDate = DateUtils.format(new Date(), "yyyyMMdd");
////        String uuid = UUID.randomUUID().toString();
////        JunitImage junitImage=new JunitImage();
////        ApiCode acode =new ApiCode();
////        junitImage.markImageByText("d:/1.png","d:/file/fileupload/" + currDate + "/"+uuid+".png",0,"png","id=1",ApiCode.postToken());
//    }

    /**
     * 给图片添加水印文字、可设置水印文字的旋转角度
     * @param
     * @param srcImgPath 海报背景图片路径
     * @param newImagePath 新图片路径filePath + "/fileupload/" + currDate + "/"+UUID.randomUUID().toString().png
     * @param degree 旋转角度
     * @param formaName 图片后缀
     * return serverUrl + "/" + currDate + "/"+UUID.randomUUID().toString().png
     */
    public String  markImageByText( String srcImgPath, String newImagePath, Integer degree, String formaName,String scenStr,String accesstoken,String uuidNew) {
        InputStream is = null;
        OutputStream os = null;
        try {
            // 1、源图片
            Image srcImg = ImageIO.read(new File(srcImgPath));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
            // 4、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree),  buffImg.getWidth()/2,buffImg.getHeight() /2);
            }
            //获取二维码
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accesstoken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene", scenStr);
            paramJson.put("page", "pages/home/home");//---小程序端获取个人信息
            paramJson.put("width", 430);
            paramJson.put("auto_color", true);

            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
//            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            String uuid = UUID.randomUUID().toString();
            String currDate = DateUtils.format(new Date(), "yyyyMMdd");
            String suffix = ".png";
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = bis.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            outStream.close();
            bis.close();
            FileUtils.writeByteArrayToFile(new File("/"+uuid+".png"),outStream.toByteArray());//二维码
//            trans(bufferedImage);
            transferAlpha2File("/"+uuid+".png","/"+uuid+".png");
            // 得到Image对象。
            ImageIcon imgIcon = new ImageIcon("/"+uuid+".png");
            Image img = imgIcon.getImage();
            float alpha = 1;

            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            g.drawImage(img, buffImg.getWidth()/4+4, buffImg.getHeight()/2+buffImg.getHeight()/11, null);
            // 9、释放资源
            g.dispose();
            // 10、生成图片
            os = new FileOutputStream(newImagePath);
            ImageIO.write(buffImg, formaName, os);
            new File("/"+uuid+".png").delete();
            return serverUrl + "/erweima/"+uuidNew+suffix;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 图片背景透明化
     * @param imgSrc  图片路径
     * @param imgTarget 图片输出路径
     * @return
     */
    public boolean transferAlpha2File(String imgSrc, String imgTarget) {
        File file = new File(imgSrc);
        InputStream is = null;
        boolean result = false;
        try {
            is = new FileInputStream(file);
            // 如果是MultipartFile类型，那么自身也有转换成流的方法：is = file.getInputStream();
            BufferedImage bi = ImageIO.read(is);
            Image image = (Image) bi;
            ImageIcon imageIcon = new ImageIcon(image);
            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
                    BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
            g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
            int alpha = 0;
            for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
                    int rgb = bufferedImage.getRGB(j2, j1);
                    int R = (rgb & 0xff0000) >> 16;
                    int G = (rgb & 0xff00) >> 8;
                    int B = (rgb & 0xff);
                    if (((255 - R) < 30) && ((255 - G) < 30) && ((255 - B) < 30)) {
                        rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
                    }
                    bufferedImage.setRGB(j2, j1, rgb);
                }
            }
            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
            result = ImageIO.write(bufferedImage, "png", new File(imgTarget));// 直接输出文件
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                }
            }
        }
        return result;
    }
}
