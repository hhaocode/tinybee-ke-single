package cn.tinybee.ke.biz.cms.task;

import cn.tinybee.ke.biz.cms.entity.CmsVod;
import cn.tinybee.ke.biz.cms.service.ICmsVodService;
import cn.tinybee.ke.common.config.properties.TinybeeVideoProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname MediaTranscodeTask
 * @Description TODO
 * @Date 2020/6/7 9:58
 * @Created by hao.huang
 */
public class MediaTranscodeTask implements Runnable{

    private CmsVod cmsVod;

    private ICmsVodService iCmsMediaService;

    private TinybeeVideoProperties tinybeeVideoProperties;

    public MediaTranscodeTask(CmsVod cmsVod, ICmsVodService iCmsMediaService, TinybeeVideoProperties tinybeeVideoProperties) {
        this.cmsVod = cmsVod;
        this.iCmsMediaService = iCmsMediaService;
        this.tinybeeVideoProperties = tinybeeVideoProperties;
    }

    @Override
    public void run() {
        // 转码
        List<String> commands = new ArrayList<>();
        commands.add("ffmpeg");
        commands.add("-i");
//        commands.add(tinybeeVideoProperties.getUploadDir() + cmsVod.getPrefixDir() + cmsVod.getMediaNo() + "." + cmsVod.getExtension());
        commands.add("-hls_time");
        commands.add("60");
        commands.add("-hls_list_size");
        commands.add("0");
        commands.add("-f");
        commands.add("hls");
        commands.add("-y");
//        String hlsDir = tinybeeVideoProperties.getM3u8Dir() + cmsVod.getPrefixDir();
//        FileUtils.mkdirs(hlsDir);
//        commands.add(hlsDir + cmsVod.getMediaNo() + ".m3u8");
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(commands);
        Process process = null;
        try {
            process = processBuilder.start();
            int i = process.waitFor();

            InputStream errorStream = process.getErrorStream(); // 错误输出
            InputStream inputStream = process.getInputStream(); // 正确输出
            iCmsMediaService.updateTranscoded(cmsVod);
        } catch (Exception e) {
            e.printStackTrace();

            try {
                process.getErrorStream().close();
                process.getInputStream().close();
                process.getOutputStream().close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
//        Runtime.getRuntime().exec("")



    }
}
