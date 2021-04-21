package cn.tinybee.ke.biz.material.service.impl;

import cn.tinybee.ke.biz.material.entity.MaterialVodUpload;
import cn.tinybee.ke.biz.material.service.IMaterialVodUploadService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-23
 */
@Service
public class MaterialVodServiceImpl {


    @Autowired
    private IMaterialVodUploadService iMaterialVodUploadService;


//    private void handleData(List<MaterialVod> param) {
//        if (param.isEmpty()) {
//            return;
//        }
//        Set<Long> collect = param.stream().map(v -> v.getVodUploadId()).collect(Collectors.toSet());
//        if (collect.isEmpty()) {
//            return;
//        }
//        QueryWrapper<MaterialVodUpload> queryWrapper = new QueryWrapper<>();
//        queryWrapper.in("id", collect);
//        Map<Long, MaterialVodUpload> idMap =
//                iMaterialVodUploadService.list(queryWrapper).stream().collect(Collectors.toMap(MaterialVodUpload::getId, Function.identity()));
//        param.forEach(v -> {
//            MaterialVodUpload upload = idMap.get(v.getVodUploadId());
//            v.setName(upload.getName());
//            v.setData(upload);
//        });
//    }


}
