package cn.tinybee.ke.common.ffmpeg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Classname FFMPEGUtils
 * @Description TODO
 * @Date 2020/6/3 13:45
 * @Created by hao.huang
 */
public class FFMPEGUtils {



    public static void exec(String cmd) {
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            InputStream is = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String content = null;
            while ((content = bufferedReader.readLine()) != null) {
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        exec("java -version");
    }
}
