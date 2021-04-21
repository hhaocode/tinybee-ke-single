package cn.tinybee.ke.biz.cloud.aliyun.media;

import cn.tinybee.ke.common.exception.BusinessException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/15 9:55
 */
public class AliyunMediaCategory {

    /**
     *
     * @param client
     * @param cateName
     * @param pid
     */
    public void addCategory(DefaultAcsClient client, String cateName, Long pid) {
        AddCategoryRequest request = new AddCategoryRequest();
        // 父分类ID，若不填，则默认生成一级分类，根节点分类ID为-1
        request.setParentId(pid == null ? -1L : pid);
        // 分类名称
        request.setCateName(cateName);
        try {
            AddCategoryResponse acsResponse = client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new BusinessException("创建分类失败");
        }
    }

    /**
     * 修改分类
     * @param client
     * @param id
     * @param cateName
     * @return
     * @throws Exception
     */
    public static UpdateCategoryResponse updateCategory(DefaultAcsClient client, Long id, String cateName) throws Exception {
        UpdateCategoryRequest request = new UpdateCategoryRequest();
        // 请设置真实分类ID
        request.setCateId(id);
        // 分类名称
        request.setCateName(cateName);
        return client.getAcsResponse(request);
    }

    /**
     * 删除分类函数
     * @param client
     * @return
     * @throws Exception
     */
    public static DeleteCategoryResponse deleteCategory(DefaultAcsClient client, Long id) throws Exception {
        DeleteCategoryRequest request = new DeleteCategoryRequest();
        // 请设置待删除分类ID
        request.setCateId(id);
        return client.getAcsResponse(request);
    }

    /**
     * 查询分类及其子分类函数
     * @param client
     * @param cateId
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public static GetCategoriesResponse getCategories(DefaultAcsClient client, Long cateId, Long pageNo, Long pageSize) throws Exception {
        GetCategoriesRequest request = new GetCategoriesRequest();
        request.setCateId(cateId);
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        return client.getAcsResponse(request);
    }
}
