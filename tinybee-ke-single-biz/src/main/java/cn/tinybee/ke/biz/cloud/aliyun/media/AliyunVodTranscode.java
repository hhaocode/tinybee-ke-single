package cn.tinybee.ke.biz.cloud.aliyun.media;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.*;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/15 10:15
 */
public class AliyunVodTranscode {


    /**
     * 添加转码模板组配置
     */
    public static AddTranscodeTemplateGroupResponse addTranscodeTemplateGroup(DefaultAcsClient client) throws Exception {
        AddTranscodeTemplateGroupRequest request = new AddTranscodeTemplateGroupRequest();
        //转码模板ID
        request.setName("grouptest");
        request.setTranscodeTemplateList(buildTranscodeTemplateList().toJSONString());
        return client.getAcsResponse(request);
    }
    /**
     * 构建需要添加的转码模板配置数据
     *
     * @return
     */
    public static JSONArray buildTranscodeTemplateList() {
        JSONObject transcodeTemplate = new JSONObject();
        //模板名称
        transcodeTemplate.put("TemplateName", "testtemplate");
        //清晰度
        transcodeTemplate.put("Definition", "LD");
        //视频流转码配置
        JSONObject video = new JSONObject();
        video.put("Width", 640);
        video.put("Bitrate", 400);
        video.put("Fps", 25);
        video.put("Remove", false);
        video.put("Codec", "H.264");
        video.put("Gop", "250");
        transcodeTemplate.put("Video", video);
        //音频流转码配置
        JSONObject audio = new JSONObject();
        audio.put("Codec", "AAC");
        audio.put("Bitrate", "64");
        audio.put("Channels", "2");
        audio.put("Samplerate", "32000");
        transcodeTemplate.put("Audio", audio);
        //封装容器
        JSONObject container = new JSONObject();
        container.put("Format", "mp4");
        transcodeTemplate.put("Container", container);
        //条件转码配置
        JSONObject transconfig = new JSONObject();
        transconfig.put("IsCheckReso", false);
        transconfig.put("IsCheckResoFail", false);
        transconfig.put("IsCheckVideoBitrate", false);
        transconfig.put("IsCheckVideoBitrateFail", false);
        transconfig.put("IsCheckAudioBitrate", false);
        transconfig.put("IsCheckAudioBitrateFail", false);
        transcodeTemplate.put("TransConfig", transconfig);
        //加密配置(只支持m3u8)
        //JSONObject encryptSetting = new JSONObject();
        //encryptSetting.put("EncryptType", "Private");
        //transcodeTemplate.put("EncryptSetting", encryptSetting);
        //水印ID(多水印关联)
        JSONArray watermarkIdList = new JSONArray();
        watermarkIdList.add("263261bdc1ff65782f8995c6dd22a16a");
        //USER_DEFAULT_WATERMARK 代表默认水印ID
        watermarkIdList.add("USER_DEFAULT_WATERMARK");
        transcodeTemplate.put("WatermarkIds", watermarkIdList);
        JSONArray transcodeTemplateList = new JSONArray();
        transcodeTemplateList.add(transcodeTemplate);
        return transcodeTemplateList;
    }


    /**
     * 修改转码模板组配置
     */
    public static UpdateTranscodeTemplateGroupResponse updateTranscodeTemplateGroup(DefaultAcsClient client) throws Exception {
        UpdateTranscodeTemplateGroupRequest request = new UpdateTranscodeTemplateGroupRequest();
        request.setName("grouptest1");
        //转码模板组ID
        request.setTranscodeTemplateGroupId("4c71a339fecec0152b4fa6f4527b89");
        request.setTranscodeTemplateList(buildTranscodeTemplateList().toJSONString());
        return client.getAcsResponse(request);
    }


    /**
     * 查询转码模板组列表
     */
    public static ListTranscodeTemplateGroupResponse listTranscodeTemplateGroup(DefaultAcsClient client) throws Exception {
        ListTranscodeTemplateGroupRequest request = new ListTranscodeTemplateGroupRequest();
        return client.getAcsResponse(request);
    }


    /**
     * 查询单个转码模板组配置
     */
    public static GetTranscodeTemplateGroupResponse getTranscodeTemplateGroup(DefaultAcsClient client) throws Exception {
        GetTranscodeTemplateGroupRequest request = new GetTranscodeTemplateGroupRequest();
        request.setTranscodeTemplateGroupId("a0fa0fda545e50e7a3eb75491f9f4");
        return client.getAcsResponse(request);
    }


    /**
     * 设置默认转码模板组
     */
    public static SetDefaultTranscodeTemplateGroupResponse setDefaultTranscodeTemplateGroup(DefaultAcsClient client) throws Exception {
        SetDefaultTranscodeTemplateGroupRequest request = new SetDefaultTranscodeTemplateGroupRequest();
        request.setTranscodeTemplateGroupId("ecf03526c945ae022165c469f4548e");
        return client.getAcsResponse(request);
    }

    /**
     * 删除转码模板组配置
     */
    public static DeleteTranscodeTemplateGroupResponse deleteTranscodeTemplateGroup(DefaultAcsClient client) throws Exception {
        DeleteTranscodeTemplateGroupRequest request = new DeleteTranscodeTemplateGroupRequest();
        request.setTranscodeTemplateGroupId("a0fa0fda545e50e91a3eb75491f9f4");
        request.setForceDelGroup("false");
        request.setTranscodeTemplateIds("ddddddd,ffffffff");
        return client.getAcsResponse(request);
    }

}
