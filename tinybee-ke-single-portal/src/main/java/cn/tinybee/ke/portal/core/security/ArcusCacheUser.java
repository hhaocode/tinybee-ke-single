package cn.tinybee.ke.portal.core.security;

import cn.tinybee.ke.biz.ums.entity.UmsStudent;
import cn.tinybee.ke.portal.core.security.spring.support.ArcusUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/29
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArcusCacheUser extends ArcusUser {

    private UmsStudent member;

    public ArcusCacheUser(Long id, String username, String password, String token,  List<GrantedAuthority> authorities, UmsStudent member) {
        super(id, username, password, token, authorities);
        this.member = member;
    }

}
