package cn.tinybee.ke.portal.cms.controller;


import cn.tinybee.ke.biz.cms.service.ICmsMediaService;
import cn.tinybee.ke.common.config.properties.TinybeeVideoProperties;
import cn.tinybee.ke.common.util.WebUtils;
import cn.tinybee.ke.core.base.BaseController;
import com.iheartradio.m3u8.*;
import com.iheartradio.m3u8.data.Playlist;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 视频信息 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-06-07
 */
@Api("媒体操作类")
@RestController
@RequestMapping("/api/cms-media")
public class CmsMediaController extends BaseController {


    @Autowired
    private ICmsMediaService ICmsMediaService;

    @Autowired
    private TinybeeVideoProperties tinybeeVideoProperties;

//    @Autowired
//    private AliyunVodService aliyunVodService;


    @GetMapping("/{contentItemId}/**/*.m3u8")
    public void m3u8( @PathVariable Long contentItemId, HttpServletResponse response,
                       HttpServletRequest request) throws IOException, ParseException, PlaylistException {

        String arguments = WebUtils.arguments(request);
//        String moduleName;
//        if (null != arguments && !arguments.isEmpty()) {
//            moduleName = moduleBaseName + '/' + arguments;
//        } else {
//            moduleName = moduleBaseName;
//        }
//
//        return "module name is: " + moduleName;

        InputStream inputStream = new FileInputStream(new File(tinybeeVideoProperties.getM3u8Dir() + arguments));
        int len = 0;
        byte[] buffer = new byte[1024];
        response.setContentType("application/x-mpegURL");
        ServletOutputStream outputStream = response.getOutputStream();
//        while ((len = inputStream.read(buffer)) > 0) {
//            outputStream.write(buffer, 0 ,len);
//        }
//        inputStream.close();
//        outputStream.flush();
        PlaylistParser parser = new PlaylistParser(inputStream, Format.EXT_M3U, Encoding.UTF_8);
        Playlist playlist = parser.parse();
        System.out.println(playlist);
        PlaylistWriter writer = new PlaylistWriter(outputStream, Format.EXT_M3U, Encoding.UTF_8);
        writer.write(playlist);
        inputStream.close();
        outputStream.close();
    }

    @GetMapping("/{contentItemId}/**/*.ts")
    public void ts(@PathVariable Long contentItemId,  HttpServletResponse response,
                   HttpServletRequest request) throws IOException {
        String arguments = WebUtils.arguments(request);
        InputStream inputStream = new FileInputStream(new File(tinybeeVideoProperties.getM3u8Dir() + arguments));
        int len = 0;
        byte[] buffer = new byte[1024];
        ServletOutputStream outputStream = response.getOutputStream();
        while ((len = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0 ,len);
        }
        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }

//    @GetMapping("/getVodPlayAuth/{vid}")
//    public ApiResult<GetVideoPlayAuthResponse> getVodPlayAuth (@PathVariable String vid) {
//        try {
//            GetVideoPlayAuthResponse videoPlayAuth = aliyunVodService.getVideoPlayAuth(vid);
//            return ApiResult.success(videoPlayAuth);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ApiResult.failed("获取视频播放许可失败");
//        }
//    }


}
