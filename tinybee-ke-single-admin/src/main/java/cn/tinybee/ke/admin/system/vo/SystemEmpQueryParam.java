package cn.tinybee.ke.admin.system.vo;

import cn.tinybee.ke.common.entity.KeywordQueryParam;
import lombok.Data;

@Data
public class SystemEmpQueryParam extends KeywordQueryParam {

    private Long[] deptIds;
}
