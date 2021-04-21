package cn.tinybee.ke.portal.api.common;

import cn.tinybee.ke.biz.cms.entity.CmsClassify;
import cn.tinybee.ke.biz.cms.service.ICmsClassifyService;
import cn.tinybee.ke.biz.material.service.IMaterialFileService;
import cn.tinybee.ke.biz.sem.entity.SemAdvertisement;
import cn.tinybee.ke.biz.sem.service.ISemAdvertisementService;
import cn.tinybee.ke.common.handle.tree.TreeNode;
import cn.tinybee.ke.common.web.ApiResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Classname BaseApiController
 * @Description TODO
 * @Date 2020/6/7 20:40
 * @Created by hao.huang
 */
@RestController
@RequestMapping("/api/open/base")
public class BaseApenApiController {


    @Autowired
    private ISemAdvertisementService iSemAdvertisementService;

    @Autowired
    private IMaterialFileService iMaterialFileService;

    @Autowired
    private ICmsClassifyService iCmsClassifyService;


    @GetMapping("/getAdvertisement")
    public ApiResult<List<SemAdvertisement>> getAdvertisement (@RequestParam Integer platform, @RequestParam Integer location) {
        QueryWrapper<SemAdvertisement> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("platform", platform);
        queryWrapper.eq("location", location);
        queryWrapper.le("begin_time", LocalDateTime.now());
        queryWrapper.ge("end_time", LocalDateTime.now());
        List<SemAdvertisement> list = iSemAdvertisementService.list(queryWrapper);
        return ApiResult.success(list);
    }

    @GetMapping("/classifyTree")
    public ApiResult<List<TreeNode<CmsClassify>>> classifyTree () {
        List<TreeNode<CmsClassify>> tree = iCmsClassifyService.tree(true);
        return ApiResult.success(tree);
    }

}
