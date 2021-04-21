package cn.tinybee.ke.biz.cloud.aliyun.media;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.*;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/15 12:45
 */
public class AliyunVodSnapshot {

    /**
     * 构建截图模板的配置数据，根据具体设置需求修改对应的参数值
     * (以下代码示例为雪碧图完整配置)
     * @return
     */
    public static JSONObject buildSnapshotTemplateConfig() {
        JSONObject templateConfig = new JSONObject();
        JSONObject snapshotConfig = new JSONObject();
        snapshotConfig.put("Count", "50");
        snapshotConfig.put("Interval", "1");
        snapshotConfig.put("SpecifiedOffsetTime", "0");
        snapshotConfig.put("Width", "200");
        snapshotConfig.put("Height", "200");
        snapshotConfig.put("FrameType", "normal");
        //普通截图配置(与雪碧图原始图配置共用该配置信息)
        templateConfig.put("SnapshotConfig", snapshotConfig);
        //雪碧图配置(雪碧图配置必须是建立在普通截图配置之上)
        JSONObject spriteSnapshotConfig = new JSONObject();
        spriteSnapshotConfig.put("CellWidth", "120");
        spriteSnapshotConfig.put("CellHeight", "68");
        spriteSnapshotConfig.put("Columns", "3");
        spriteSnapshotConfig.put("Lines", "10");
        spriteSnapshotConfig.put("Padding", "20");
        spriteSnapshotConfig.put("Margin", "50");
        spriteSnapshotConfig.put("KeepCellPic", "keep");
        spriteSnapshotConfig.put("Color", "tomato");
        snapshotConfig.put("SpriteSnapshotConfig", spriteSnapshotConfig);
        //截图类型(存在雪碧图配置，该类型必须是"SpriteSnapshot"，否则为"NormalSnapshot")
        templateConfig.put("SnapshotType", "SpriteSnapshot");
        return templateConfig;
    }
    /**
     * 添加截图模板函数
     */
    public static AddVodTemplateResponse addSnapshotVodTemplate(DefaultAcsClient client) throws Exception {
        AddVodTemplateRequest request = new AddVodTemplateRequest();
        //模板名称
        request.setName("截图模板添加测试");
        //模板类型，固定值为Snapshot
        request.setTemplateType("Snapshot");
        //截图模板配置数据生成
        JSONObject templateConfig = buildSnapshotTemplateConfig();
        request.setTemplateConfig(templateConfig.toJSONString());
        return client.getAcsResponse(request);
    }


    /**
     * 修改截图模板函数
     */
    public static UpdateVodTemplateResponse updateSnapshotVodTemplate(DefaultAcsClient client) throws Exception {
        UpdateVodTemplateRequest request = new UpdateVodTemplateRequest();
        //设置要修改的模板ID
        request.setVodTemplateId("53azf9d796fad9d7b862b2e5e5b");
        //模板名称
        request.setName("截图模板修改测试");
        //截图模板配置数据生成
        JSONObject templateConfig = buildSnapshotTemplateConfig();
        request.setTemplateConfig(templateConfig.toJSONString());
        return client.getAcsResponse(request);
    }

    /**
     * 删除截图模板函数
     */
    public static DeleteVodTemplateResponse deleteSnapshotVodTemplate(DefaultAcsClient client) throws Exception {
        DeleteVodTemplateRequest request = new DeleteVodTemplateRequest();
        //设置要删除的模板ID
        request.setVodTemplateId("53azf9d796fad9d7b862b2e5e5b");
        return client.getAcsResponse(request);
    }

    /**
     * 查询截图模板列表函数
     */
    public static ListVodTemplateResponse listSnapshotVodTemplate(DefaultAcsClient client) throws Exception {
        ListVodTemplateRequest request = new ListVodTemplateRequest();
        //模板类型，固定值为Snapshot
        request.setTemplateType("Snapshot");
        return client.getAcsResponse(request);
    }

    /**
     * 查询截图模板函数
     */
    public static GetVodTemplateResponse getSnapshotVodTemplate(DefaultAcsClient client) throws Exception {
        GetVodTemplateRequest request = new GetVodTemplateRequest();
        //设置要查询的模板ID
        request.setVodTemplateId("53azf9d796fad9d7b862b2e5e5b");
        return client.getAcsResponse(request);
    }

    



}
