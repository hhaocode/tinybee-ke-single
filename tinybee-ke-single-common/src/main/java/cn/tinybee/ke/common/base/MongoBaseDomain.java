package cn.tinybee.ke.common.base;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/11/17 16:47
 */
@Data
public class MongoBaseDomain implements Serializable {
    private static final long serialVersionUID = 8725679004436756375L;

    @Id
    private Long id;


    private LocalDateTime createTime = LocalDateTime.now();

    private LocalDateTime modifyTime = LocalDateTime.now();

    private Long creator;

    private Long modifier;
}
