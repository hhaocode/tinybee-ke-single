package cn.tinybee.ke.biz.cloud.aliyun.media;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.*;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/15 10:01
 */
public class AliyunMediaManage {

    /**
     * 搜索媒资信息
     * @param client 发送请求客户端
     * @return SearchMediaResponse 搜索媒资信息响应数据
     * @throws Exception

     */
    public static SearchMediaResponse searchMedia(DefaultAcsClient client) throws Exception {
        SearchMediaRequest request = new SearchMediaRequest();
        request.setFields("Title,CoverURL,Status");
        request.setMatch("Status in ('Normal','Checking') and CreationTime = ('2018-07-01T08:00:00Z','2018-08-01T08:00:00Z')");
        request.setPageNo(1);
        request.setPageSize(10);
        request.setSearchType("video");
        request.setSortBy("CreationTime:Desc");
        return client.getAcsResponse(request);
    }


    /**
     * 获取视频信息
     * @param client 发送请求客户端
     * @return GetVideoInfoResponse 获取视频信息响应数据
     * @throws Exception
     */
    public static GetVideoInfoResponse getVideoInfo(DefaultAcsClient client) throws Exception {
        GetVideoInfoRequest request = new GetVideoInfoRequest();
        request.setVideoId("VideoId");
        return client.getAcsResponse(request);
    }

    /**
     * 批量获取视频信息函数
     * @param client 发送请求客户端
     * @return GetVideoInfosResponse 获取视频信息响应数据
     * @throws Exception
     */
    public static GetVideoInfosResponse getVideoInfos(DefaultAcsClient client) throws Exception {
        GetVideoInfosRequest request = new GetVideoInfosRequest();
        request.setVideoIds("VideoId1,VideoId2");
        return client.getAcsResponse(request);
    }


    /**
     * 修改视频信息
     * @param client 发送请求客户端
     * @return UpdateVideoInfoResponse 修改视频信息响应数据
     * @throws Exception
     */
    public static UpdateVideoInfoResponse updateVideoInfo(DefaultAcsClient client) throws Exception {
        UpdateVideoInfoRequest request = new UpdateVideoInfoRequest();
        request.setVideoId("VideoId");
        request.setTitle("new Title");
        request.setDescription("new Description");
        request.setTags("new Tag1,new Tag2");
        return client.getAcsResponse(request);
    }


    /**
     * 批量修改视频信息
     * @param client 发送请求客户端
     * @return UpdateVideoInfosResponse 批量修改视频信息响应数据
     * @throws Exception
     */
    public static UpdateVideoInfosResponse updateVideoInfos(DefaultAcsClient client) throws Exception {
        UpdateVideoInfosRequest request = new UpdateVideoInfosRequest();
        JSONArray updateContentArray = new JSONArray();
        JSONObject updateContent1 = new JSONObject();
        updateContent1.put("VideoId", "VideoId1");
        // updateContent1.put("Title", "new Title");
        // updateContent1.put("Tags", "new Tag1,new Tag2");
        updateContentArray.add((updateContent1));
        JSONObject updateContent2 = new JSONObject();
        updateContent2.put("VideoId", "VideoId2");
        // updateContent2.put("Title", "new Title");
        // updateContent2.put("Tags", "new Tag1,new Tag2");
        updateContentArray.add((updateContent2));
        request.setUpdateContent(updateContentArray.toJSONString());
//        DefaultAcsClient client = init();
        return client.getAcsResponse(request);
    }


    /**
     * 删除视频
     * @param client 发送请求客户端
     * @return DeleteVideoResponse 删除视频响应数据
     * @throws Exception
     */
    public static DeleteVideoResponse deleteVideo(DefaultAcsClient client) throws Exception {
        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds("VideoId1,VideoId2");
        return client.getAcsResponse(request);
    }


    /**
     * 获取源文件信息
     * @param client 发送请求客户端
     * @return GetMezzanineInfoResponse 获取源文件信息响应数据
     * @throws Exception
     */
    public static GetMezzanineInfoResponse getMezzanineInfo(DefaultAcsClient client) throws Exception {
        GetMezzanineInfoRequest request = new GetMezzanineInfoRequest();
        request.setVideoId("VideoId");
        //源片下载地址过期时间
        request.setAuthTimeout(3600L);
        return client.getAcsResponse(request);
    }


    // 根据Date时间生成UTC时间函数
    public static String generateUTCTime(Date time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        dateFormat.setLenient(false);
        return dateFormat.format(time);
    }

    /**
     * 获取视频列表
     * @param client 发送请求客户端
     * @return GetVideoListResponse 获取视频列表响应数据
     * @throws Exception
     */
    public static GetVideoListResponse getVideoList(DefaultAcsClient client) throws Exception {
        GetVideoListRequest request = new GetVideoListRequest();
        // 分别取一个月前、当前时间的UTC时间作为筛选视频列表的起止时间
        String monthAgoUTCTime = generateUTCTime(new Date(System.currentTimeMillis() - 30*86400*1000L));
        String nowUTCTime = generateUTCTime(new Date(System.currentTimeMillis()));
        // 视频创建的起始时间，为UTC格式
        request.setStartTime(monthAgoUTCTime);
        // 视频创建的结束时间，为UTC格式
        request.setEndTime(nowUTCTime);
        // 视频状态，默认获取所有状态的视频，多个用逗号分隔
        // request.setStatus("Uploading,Normal,Transcoding");
        request.setPageNo(1);
        request.setPageSize(20);
        return client.getAcsResponse(request);
    }


    /**
     * 删除媒体流函数
     * @param client 发送请求客户端
     * @return DeleteMezzaninesResponse 删除媒体流响应数据
     * @throws Exception
     */
    public static DeleteStreamResponse deleteStream(DefaultAcsClient client) throws Exception {
        DeleteStreamRequest request = new DeleteStreamRequest();
        request.setVideoId("VideoId");
        request.setJobIds("JobId1,JobId2");
        return client.getAcsResponse(request);
    }

    /**
     * 批量删除源文件函数
     * @param client 发送请求客户端
     * @return DeleteMezzaninesResponse 批量删除源文件响应数据
     * @throws Exception
     */
    public static DeleteMezzaninesResponse deleteMezzanines(DefaultAcsClient client) throws Exception {
        DeleteMezzaninesRequest request = new DeleteMezzaninesRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds("VideoId1,VideoId2");
        request.setForce(false);
        return client.getAcsResponse(request);
    }

    /**
     * 批量更新图片信息函数
     * @param client 发送请求客户端
     * @return UpdateImageInfosResponse 批量更新图片信息响应数据
     * @throws Exception
     */
    public static UpdateImageInfosResponse updateImageInfos(DefaultAcsClient client) throws Exception{
        UpdateImageInfosRequest request = new UpdateImageInfosRequest();
        JSONArray updateContentArray = new JSONArray();
        JSONObject updateContent1 = new JSONObject();
        updateContent1.put("ImageId", "ImageId1");
//        updateContent1.put("Title", "new Title");
//        updateContent1.put("Tags", "new Tag1,new Tag2");
        updateContentArray.add((updateContent1));
        JSONObject updateContent2 = new JSONObject();
        updateContent2.put("ImageId", "ImageId2");
//        updateContent2.put("Title", "new Title");
//        updateContent2.put("Tags", "new Tag1,new Tag2");
        updateContentArray.add((updateContent2));
        request.setUpdateContent(updateContentArray.toJSONString());
        return client.getAcsResponse(request);
    }


    /**
     * 获取图片信息函数
     *
     * @param client 发送请求客户端
     * @return GetImageInfoResponse 获取图片信息响应数据
     * @throws Exception
     */
    public static GetImageInfoResponse getImageInfo(DefaultAcsClient client) throws Exception {
        GetImageInfoRequest request = new GetImageInfoRequest();
        request.setImageId("ImageId");
        return client.getAcsResponse(request);
    }

    /**
     * 删除图片函数
     *
     * @param client 发送请求客户端
     * @return DeleteImageResponse 删除图片响应数据
     * @throws Exception
     */
    public static DeleteImageResponse deleteImage(DefaultAcsClient client) throws Exception {
        DeleteImageRequest request = new DeleteImageRequest();
        //根据ImageURL删除图片文件
        request.setDeleteImageType("ImageURL");
        String url = "http://sample.aliyun.com/cover.jpg";
        String encodeUrl = URLEncoder.encode(url, "UTF-8");
        request.setImageURLs(encodeUrl);
        //根据ImageId删除图片文件
        //request.setDeleteImageType("ImageId");
        //request.setImageIds("ImageId1,ImageId2");
        //根据VideoId删除指定ImageType的图片文件
        //request.setDeleteImageType("VideoId");
        //request.setVideoId("VideoId");
        //request.setImageType("SpriteSnapshot");
        return client.getAcsResponse(request);
    }

}
