package cn.tinybee.ke.common.service.tencent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hao.huang
 * @description
 * @date 2020/4/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TencentVod implements Serializable {

    private static final long serialVersionUID = -9019171013983969094L;
    private  String fileId;//	媒体文件的唯一标识。	String
    private  String  mediaUrl;//	媒体播放地址。	String
    private  String  coverUrl;//	媒体封面地址。	String
    private  String  requestId;//	唯一请求 ID，每次请求都会返回。定位问题时需要提供该次请求的 RequestId。	String

}
