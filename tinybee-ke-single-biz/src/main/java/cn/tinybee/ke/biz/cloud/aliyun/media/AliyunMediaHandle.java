package cn.tinybee.ke.biz.cloud.aliyun.media;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.kms.model.v20160120.GenerateDataKeyRequest;
import com.aliyuncs.kms.model.v20160120.GenerateDataKeyResponse;
import com.aliyuncs.vod.model.v20170321.*;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/15 10:05
 */
public class AliyunMediaHandle {

    /**
     * 提交媒体处理作业
     */
    public static SubmitTranscodeJobsResponse submitTranscodeJobs(DefaultAcsClient client) throws Exception {
        SubmitTranscodeJobsRequest request = new SubmitTranscodeJobsRequest();
        //需要转码的视频ID
        request.setVideoId("34a6ca54f5c140eece85a289096d");
        //转码模板ID
        request.setTemplateGroupId("e8aa925a9798c630d30cd737d4");
        //构建需要替换的水印参数(只有需要替换水印相关信息才需要构建)
        JSONObject overrideParams = buildOverrideParams();
        //覆盖参数，暂只支持水印部分参数替换(只有需要替换水印相关信息才需要传递)
        request.setOverrideParams(overrideParams.toJSONString());
        //构建标准加密配置参数(只有标准加密才需要构建)
        JSONObject encryptConfig = buildEncryptConfig(client);
        //HLS标准加密配置(只有标准加密才需要传递)
        request.setEncryptConfig(encryptConfig.toJSONString());
        return client.getAcsResponse(request);
    }

    /**
     * 构建HLS标准加密的配置信息
     * @return
     * @throws ClientException
     */
    public static JSONObject buildEncryptConfig(DefaultAcsClient client) throws ClientException {
        //点播给用户在KMS(秘钥管理服务)中的Service Key，可在用户秘钥管理服务对应的区域看到描述为vod的service key
        String serviceKey = "<您的Service Key>";
        //随机生成一个加密的秘钥，返回的response包含明文秘钥以及密文秘钥，
        //视频标准加密只需要传递密文秘钥即可
        //注意：KMS Client建议单独初始化来保证正确的接入区域，可参考VOD初始化方式，传入正确的KMS服务区域。
        GenerateDataKeyResponse response = generateDataKey(client, serviceKey);
        JSONObject encryptConfig = new JSONObject();
        //解密接口地址，该参数需要将每次生成的密文秘钥与接口URL拼接生成，表示每个视频的解密的密文秘钥都不一样
        //至于Ciphertext这个解密接口参数的名称，用户可自行制定，这里只作为参考参数名称
        encryptConfig.put("DecryptKeyUri", "http://decrypt.demo.com/decrypt?" +
                "Ciphertext=" + response.getCiphertextBlob());
        //秘钥服务的类型，目前只支持KMS
        encryptConfig.put("KeyServiceType", "KMS");
        //密文秘钥
        encryptConfig.put("CipherText", response.getCiphertextBlob());
        return encryptConfig;
    }

    /**
     * 1、构建覆盖参数，目前只支持图片水印文件地址、文字水印的内容覆盖；
     * 2、需要替换的水印信息对应水印ID必须是关联在指定的模板ID(即TranscodeTemplateId)中；
     * 3、不支持通过媒体处理接口去增加一个没有关联上的水印
     * 注意：图片水印的文件存储源站需要和发起转码的视频存储源站一致
     * @return
     */
    public static JSONObject buildOverrideParams() {
        JSONObject overrideParams = new JSONObject();
        JSONArray watermarks = new JSONArray();
        //图片水印文件地址替换
        JSONObject watermark1 = new JSONObject();
        //模板上面关联需要替换的水印文件图片水印ID
        watermark1.put("WatermarkId", "2ea587477c5a1bc8b5742d7");
        //需要替换成对应图片水印文件的OSS地址，水印文件存储源站需要和视频存储源站一致
        watermark1.put("FileUrl", "https://outin-40564284ef05113e1403e7.oss-cn-shanghai.aliyuncs.com/watermarks/02A1B22DF25D46C3C725A4-6-2.png");
        watermarks.add(watermark1);

        //文字水印内容替换
        JSONObject watermark2 = new JSONObject();
        //模板上面关联需要替换内容的文字水印ID
        watermark2.put("WatermarkId", "d297ba31ac5242d2071bf7");
        //需要替换成对应的内容
        watermark2.put("Content", "用户ID：66666");
        watermarks.add(watermark2);
        overrideParams.put("Watermarks", watermarks);
        return overrideParams;
    }

    /**
     * 生成加密需要的秘钥，response中包含密文秘钥和明文秘钥，用户只需要将密文秘钥传递给点播即可
     * 注意：KeySpec 必须传递AES_128，且不能设置NumberOfBytes
     * @param client KMS-SDK客户端
     * @param serviceKey 点播提供生成秘钥的service key，在用户的秘钥管理服务中可看到描述为vod的加密key
     * @return
     * @throws ClientException
     */
    public static GenerateDataKeyResponse generateDataKey(DefaultAcsClient client, String serviceKey) throws ClientException {
        GenerateDataKeyRequest request = new GenerateDataKeyRequest();
        request.setKeyId(serviceKey);
        request.setKeySpec("AES_128");
        return client.getAcsResponse(request);
    }

    /**
     * 提交媒体截图处理作业调用函数
     */
    public static SubmitSnapshotJobResponse submitSnapshotJob(DefaultAcsClient client) throws Exception {
        SubmitSnapshotJobRequest request = new SubmitSnapshotJobRequest();
        //需要截图的视频ID(推荐传递截图模板ID)
        request.setVideoId("4d237a8270084849bf42078761814069");
        //截图模板ID
        request.setSnapshotTemplateId("5d745e6b8baadf589e0702426cfc62c81");

        //如果设置了SnapshotTemplateId，会忽略下面参数
        request.setCount(50L);
        request.setSpecifiedOffsetTime(0L);
        request.setInterval(1L);
        request.setWidth("200");
        request.setHeight("200");
        JSONObject spriteSnapshotConfig = buildSnapshotTemplateConfig();
        request.setSpriteSnapshotConfig(spriteSnapshotConfig.toJSONString());
        return client.getAcsResponse(request);
    }

    /**
     * 构建雪碧图截图配置
     * @return
     */
    public static JSONObject buildSnapshotTemplateConfig() {
        JSONObject spriteSnapshotConfig = new JSONObject();
        spriteSnapshotConfig.put("CellWidth", "120");
        spriteSnapshotConfig.put("CellHeight", "68");
        spriteSnapshotConfig.put("Columns", "3");
        spriteSnapshotConfig.put("Lines", "10");
        spriteSnapshotConfig.put("Padding", "20");
        spriteSnapshotConfig.put("Margin", "50");
        //保留雪碧图原始图
        spriteSnapshotConfig.put("KeepCellPic", "keep");
        spriteSnapshotConfig.put("Color", "tomato");
        return spriteSnapshotConfig;
    }


    /**
     * 查询截图数据
     */
    public static ListSnapshotsResponse listSnapshots(DefaultAcsClient client) throws Exception {
        ListSnapshotsRequest request = new ListSnapshotsRequest();
        //视频ID
        request.setVideoId("c86c0ceba97965352418");
        //截图类型
        request.setSnapshotType("CoverSnapshot");
        request.setPageNo("1");
        request.setPageSize("20");
        return client.getAcsResponse(request);
    }


    /**
     * 导播台视频预处理
     */
    public static SubmitPreprocessJobsResponse submitPreprocessJobs(DefaultAcsClient client) throws Exception {
        SubmitPreprocessJobsRequest request = new SubmitPreprocessJobsRequest();
        //视频ID
        request.setVideoId("c86c0ceb8db54ae097965352418");
        request.setPreprocessType("PreprocessType");
        return client.getAcsResponse(request);
    }



    /**
     * 添加水印配置信息函数
     */
    public static AddWatermarkResponse addWatermark(DefaultAcsClient client) throws Exception {
        AddWatermarkRequest request = new AddWatermarkRequest();
        //水印名称
        request.setName("addwatermark");
        //获取水印文件在oss的URL
        String fileUrl = "http://test-bucket.oss-cn-shanghai.aliyuncs.com/watermark/test.png";
        //图片水印必传图片文件的oss文件地址，水印文件必须和视频在同一个区域，例如:华东2视频，水印文件必须存放在华东2
        request.setFileUrl(fileUrl);
        //水印配置数据
        JSONObject watermarkConfig = null;
        //图片水印的位置配置数据
        watermarkConfig = buildImageWatermarkConfig();
        //文字水印的位置配置数据
        //watermarkConfig = buildTextWatermarkConfig();
        request.setWatermarkConfig(watermarkConfig.toJSONString());
        //文字水印:Text; 图片水印:Image
        request.setType("Image");
        return client.getAcsResponse(request);
    }

    /**
     * 构建图片水印的配置数据，根据具体设置需求修改对应的参数值
     * @return
     */
    public static JSONObject buildImageWatermarkConfig() {
        JSONObject watermarkConfig = new JSONObject();
        //水印的横向偏移距离
        watermarkConfig.put("Dx", "8");
        //水印的纵向偏移距离
        watermarkConfig.put("Dy", "8");
        //水印显示的宽
        watermarkConfig.put("Width", "55");
        //水印显示的高
        watermarkConfig.put("Height", "55");
        //水印显示的相对位置(左上、右上、左下、右下)
        watermarkConfig.put("ReferPos", "BottomRight");
        //水印显示的时间线(开始显示和结束显示时间)
        JSONObject timeline = new JSONObject();
        //水印开始显示时间
        timeline.put("Start", "2");
        //水印结束显示时间
        timeline.put("Duration", "ToEND");
        watermarkConfig.put("Timeline", timeline);
        return watermarkConfig;
    }
    /**
     * 构建文字水印的配置数据，根据具体设置需求修改对应的参数值
     * @return
     */
    public static JSONObject buildTextWatermarkConfig() {
        JSONObject watermarkConfig = new JSONObject();
        //文字水印显示的内容
        watermarkConfig.put("Content", "testwatermark");
        //文字水印的字体名称
        watermarkConfig.put("FontName", "SimSun");
        //文字水印的字体大小
        watermarkConfig.put("FontSize", 25);
        //文字水印的颜色(也可为RGB颜色取值，例如:#000000)
        watermarkConfig.put("FontColor", "Black");
        //文字水印的透明度
        watermarkConfig.put("FontAlpha", "0.2");
        //文字水印的字体描边颜色(也可为RGB颜色取值，例如:#ffffff)
        watermarkConfig.put("BorderColor", "White");
        //文字水印的描边宽度
        watermarkConfig.put("BorderWidth", 1);
        //文字水印距离视频画面上边的偏移距离
        watermarkConfig.put("Top", 20);
        //文字水印距离视频画面左边的偏移距离
        watermarkConfig.put("Left", 15);
        return watermarkConfig;
    }



    /**
     * 修改水印配置信息函数
     * 注意：不支持图片文件地址的修改，如果更换请创建新的水印信息
     */
    public static UpdateWatermarkResponse updateWatermark(DefaultAcsClient client) throws Exception {
        UpdateWatermarkRequest request = new UpdateWatermarkRequest();
        request.setName("updatewatermark");
        //需要更新配置信息的水印ID
        request.setWatermarkId("421ddddd4f6e734a526fd2e442");
        //水印配置数据
        JSONObject watermarkConfig = null;
        //图片水印的位置配置数据
        //watermarkConfig = buildImageWatermarkConfig();
        //文字水印的位置配置数据
        watermarkConfig = buildTextWatermarkConfig();
        request.setWatermarkConfig(watermarkConfig.toJSONString());
        return client.getAcsResponse(request);
    }


    /**
     *删除水印配置信息函数
     */
    public static DeleteWatermarkResponse deleteWatermark(DefaultAcsClient client) throws Exception {
        DeleteWatermarkRequest request = new DeleteWatermarkRequest();
        request.setWatermarkId("53f9d796fad9d7b862b2e5e5b");
        return client.getAcsResponse(request);
    }


    /**
     * 查询水印配置信息列表函数
     */
    public static ListWatermarkResponse listWatermark(DefaultAcsClient client) throws Exception {
        ListWatermarkRequest request = new ListWatermarkRequest();
        return client.getAcsResponse(request);
    }


    /**
     * 查询单个水印配置信息函数
     */
    public static GetWatermarkResponse getWatermark(DefaultAcsClient client) throws Exception {
        GetWatermarkRequest request = new GetWatermarkRequest();
        //需要查询水印信息的水印ID
        request.setWatermarkId("96d4f6e734a526fd2e442");
        return client.getAcsResponse(request);
    }

    /**
     * 设置默认水印配置信息函数
     */
    public static SetDefaultWatermarkResponse setDefaultWatermark(DefaultAcsClient client) throws Exception {
        SetDefaultWatermarkRequest request = new SetDefaultWatermarkRequest();
        //设置默认的水印ID
        request.setWatermarkId("82105a29c6e96d4f6e734a");
        return client.getAcsResponse(request);
    }


}
