package cn.tinybee.ke.admin.common.controller;

import cn.hutool.core.util.IdUtil;
import cn.tinybee.ke.common.config.properties.TinybeeVideoProperties;
import cn.tinybee.ke.common.util.file.FileUtils;
import com.iheartradio.m3u8.*;
import com.iheartradio.m3u8.data.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author hao.huang
 * @description
 * @date 2020/4/15
 */
@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private TinybeeVideoProperties tinybeeVideoProperties;

//    @Autowired
//    private OSSService ossService;

//    @PostMapping("/upload")
//    public String upload(MultipartFile file, @RequestParam(required = false) String typeId) throws Exception {
//        return ossService.uploadFile(file, "images");
//    }

    @PostMapping("/upload/video")
    public String upload(MultipartFile file) throws IOException {
        /*
        * 1. 将文件保存到
         *
         */

        File file1 = file.getResource().getFile();
        String fileName = file.getOriginalFilename();
        String extend = fileName.substring(fileName.lastIndexOf("."));
        String fileId = IdUtil.randomUUID();
        FileUtils.mkdirs(tinybeeVideoProperties.getUploadDir());
        try {
            String path = tinybeeVideoProperties.getUploadDir() + fileId + "." + extend;
            file.transferTo(new File(path));
            // 将文件插入文件中
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    @GetMapping("/a.m3u8")
    public void m3u8(HttpServletResponse response) throws IOException, ParseException, PlaylistException {
        InputStream inputStream = new FileInputStream(new File("E:\\upload\\m3u8\\test.m3u8"));
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

//        writer = new PlaylistWriter.Builder()
//                .withOutputStream(outputStream)
//                .withFormat(Format.EXT_M3U)
//                .withEncoding(Encoding.UTF_8)
//                .build();
//
//        writer.write(playlist);
    }

    @GetMapping("/{tsName}")
    public void ts(@PathVariable() String tsName,HttpServletResponse response) throws IOException {
        InputStream inputStream = new FileInputStream(new File("E:\\upload\\m3u8\\" + tsName));
        int len = 0;
        byte[] buffer = new byte[1024];
        ServletOutputStream outputStream = response.getOutputStream();
                while ((len = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0 ,len);
        }
        inputStream.close();
        outputStream.flush();
    }
}
