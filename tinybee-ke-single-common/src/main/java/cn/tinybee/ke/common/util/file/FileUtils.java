package cn.tinybee.ke.common.util.file;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.math.BigInteger;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class FileUtils {

    private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

    private final static String PREFIX_VIDEO="video/";

    public static String getcontentType(String FileNameExtension) {
        if (FileNameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FileNameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FileNameExtension.equalsIgnoreCase(".jpeg") ||
                FileNameExtension.equalsIgnoreCase(".jpg") ||
                FileNameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (FileNameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FileNameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FileNameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FileNameExtension.equalsIgnoreCase(".pptx") ||
                FileNameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FileNameExtension.equalsIgnoreCase(".docx") ||
                FileNameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FileNameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

    public static byte[] readNio(File file) {
//        FileChannel channel = null;
        try (FileInputStream fileInputStream = new FileInputStream(file);FileChannel channel = fileInputStream.getChannel()){
            int capacity = 1024;
            ByteBuffer buffer = ByteBuffer.allocate(capacity);
            byte[] res = new byte[0];
            int length = -1;
            while ((length = channel.read(buffer)) != -1) {
                buffer.clear();
                byte[] bytes = buffer.array();
                res = ArrayUtils.addAll(res, bytes);
//                String str = new String(bytes, 0, length);
//                System.out.println(str);
            }
            //
            return res;
        } catch (IOException e) {
            return null;
        }
    }

    public static void writeNio (File file, byte[] src) {
        try (FileOutputStream outputStream = new FileOutputStream(file); FileChannel channel = outputStream.getChannel()) {
            ByteBuffer buffer = ByteBuffer.wrap(src);
            int length = 0;
            while ((length = channel.write(buffer)) != 0) {
                /*
                 * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读
                 */
                System.out.println("写入长度:" + length);
            }
        }catch (IOException e) {

        }
    }

    public static void mkdirs(String path) {
        if (StringUtils.isBlank(path)) {
            return;
        }
        File file = new File(path);
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new IllegalArgumentException("路径不是目录");
            }
        } else {
            file.mkdirs();
        }
    }

//    public static void main(String[] args) throws IOException, ParseException, PlaylistException {
////        byte[] bytes = FileUtils.readNio(new File("C:\\Users\\Administrator\\Desktop\\tmp\\tinybee\\vueapp\\1.png"));
////        System.out.println(bytes.length);
//        InputStream inputStream = new FileInputStream(new File("E:\\upload\\m3u8\\test.m3u8"));
//        PlaylistParser parser = new PlaylistParser(inputStream, Format.EXT_M3U, Encoding.UTF_8);
//        Playlist playlist = parser.parse();
//        System.out.println(playlist);
//    }

    private static String getMimeType(String fileName) {
        return URLConnection.getFileNameMap().getContentTypeFor(fileName);
    }

    public static String toHexString(byte[] data) {
        StringBuilder builder = new StringBuilder((data.length));
        for (byte b : data) {
            builder.append(hexCode[(b >> 4) & 0xF]);
            builder.append(hexCode[(b & 0xF)]);
        }
        return builder.toString();
    }


    public static String getMd5 (InputStream in) {
        try {
            return DigestUtils.md5Hex(in);
        } catch (IOException e) {
            log.error("获取流的MD5码失败: {}", e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("D:\\resource\\bunana\\kibana-7.6.2-linux-x86_64.tar.gz")) {
//            System.out.println(md5(fileInputStream));
            System.out.println(getMd5(fileInputStream));
        } catch (Exception e) {

        }
    }

}
