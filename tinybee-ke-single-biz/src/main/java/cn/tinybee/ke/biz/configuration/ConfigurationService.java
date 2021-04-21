package cn.tinybee.ke.biz.configuration;

import cn.tinybee.ke.biz.configuration.enums.CloudServiceType;
import cn.tinybee.ke.biz.configuration.enums.CloudType;
import cn.tinybee.ke.biz.platform.entity.PlatformCloudAccess;
import cn.tinybee.ke.biz.platform.entity.PlatformCloudOss;
import cn.tinybee.ke.biz.platform.entity.PlatformCloudVod;
import cn.tinybee.ke.biz.platform.service.IPlatformCloudAccessService;
import cn.tinybee.ke.biz.platform.service.IPlatformCloudOssService;
import cn.tinybee.ke.biz.platform.service.IPlatformCloudVodService;
import cn.tinybee.ke.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/3/19 15:52
 */
@Component
public class ConfigurationService {

    private static final String PLATFORM_CLOUD_ACCESS_MAP_KEY = "PLATFORM:CLOUD:ACCESS:MAP";

    private static final String PLATFORM_CLOUD_OSS_MAP_KEY = "PLATFORM:CLOUD:OSS:MAP";

    private static final String PLATFORM_CLOUD_VOD_MAP_KEY = "PLATFORM:CLOUD:VOD:MAP";

    private static final String CLOUD_SERVICE_TYPE_DEFAULT_KEY = "CLOUD:SERVICE:TYPE:DEFAULT";

    private static Map<CloudType, PlatformCloudAccess> PLATFORM_CLOUD_ACCESS_MAP = new HashMap<>();

    private static Map<CloudType, PlatformCloudOss> PLATFORM_CLOUD_OSS_MAP = new HashMap<>();

    private static Map<CloudType, PlatformCloudVod> PLATFORM_CLOUD_VOD_MAP = new HashMap<>();

    private static Map<CloudServiceType, CloudType> CLOUD_SERVICE_TYPE_DEFAULT = new HashMap<>();

    private IPlatformCloudAccessService platformCloudAccessService;

    private IPlatformCloudOssService platformCloudOssService;

    private IPlatformCloudVodService platformCloudVodService;

    @Autowired
    public void setPlatformCloudAccessService(IPlatformCloudAccessService platformCloudAccessService) {
        this.platformCloudAccessService = platformCloudAccessService;
    }
    @Autowired
    public void setPlatformCloudOssService(IPlatformCloudOssService platformCloudOssService) {
        this.platformCloudOssService = platformCloudOssService;
    }
    @Autowired
    public void setPlatformCloudVodService(IPlatformCloudVodService platformCloudVodService) {
        this.platformCloudVodService = platformCloudVodService;
    }

    private RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    public void initConfig () {
        //  单机下  这样就可以  // 集群环境下需要

        for (PlatformCloudAccess platformCloudAccess : platformCloudAccessService.list()) {
            PLATFORM_CLOUD_ACCESS_MAP.put(platformCloudAccess.getId(), platformCloudAccess);
        }
        redisService.set(PLATFORM_CLOUD_ACCESS_MAP_KEY, PLATFORM_CLOUD_ACCESS_MAP);
        // 初始化 对象存储
        for (PlatformCloudOss platformCloudOss : platformCloudOssService.list()) {
            PLATFORM_CLOUD_OSS_MAP.put(platformCloudOss.getId(), platformCloudOss);
            if (platformCloudOss.getIsDefault() != null && platformCloudOss.getIsDefault()) {
                CLOUD_SERVICE_TYPE_DEFAULT.put(CloudServiceType.OSS, platformCloudOss.getId());
            }
        }
        redisService.set(PLATFORM_CLOUD_OSS_MAP_KEY, PLATFORM_CLOUD_OSS_MAP);
        for (PlatformCloudVod platformCloudVod : platformCloudVodService.list()) {
            PLATFORM_CLOUD_VOD_MAP.put(platformCloudVod.getId(), platformCloudVod);
            if (platformCloudVod.getIsDefault() != null && platformCloudVod.getIsDefault()) {
                CLOUD_SERVICE_TYPE_DEFAULT.put(CloudServiceType.VOD, platformCloudVod.getId());
            }
        }
        redisService.set(PLATFORM_CLOUD_VOD_MAP_KEY, PLATFORM_CLOUD_VOD_MAP);
        // 保存
        redisService.set(CLOUD_SERVICE_TYPE_DEFAULT_KEY, CLOUD_SERVICE_TYPE_DEFAULT);
    }

}
