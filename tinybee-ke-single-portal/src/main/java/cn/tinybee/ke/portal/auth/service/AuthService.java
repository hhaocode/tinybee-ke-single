package cn.tinybee.ke.portal.auth.service;

import cn.hutool.core.lang.Validator;
import cn.tinybee.ke.biz.ums.dto.UserPasswordDto;
import cn.tinybee.ke.biz.ums.entity.UmsStudent;
import cn.tinybee.ke.biz.ums.service.IUmsStudentCourseService;
import cn.tinybee.ke.biz.ums.service.IUmsStudentService;
import cn.tinybee.ke.common.exception.BusinessException;
import cn.tinybee.ke.common.exception.LoginFailedException;
import cn.tinybee.ke.common.service.RedisService;
import cn.tinybee.ke.common.service.cloud.SmsService;
import cn.tinybee.ke.common.util.AssertUtils;
import cn.tinybee.ke.common.util.CodeUtils;
import cn.tinybee.ke.portal.core.enums.AuthCodeChannel;
import cn.tinybee.ke.portal.core.enums.AuthCodeType;
import cn.tinybee.ke.portal.auth.dto.MemberRegisterDto;
import cn.tinybee.ke.portal.auth.dto.MobileAuthParamDto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/29
 */
@Slf4j
@Service
public class AuthService {

    @Autowired
    private IUmsStudentService umsUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisService redisService;

    @Autowired
    private IUmsStudentCourseService iUmsStudentCourseService;

    @Autowired
    private SmsService smsService;

    public UmsStudent login(String username) throws LoginFailedException {
        UmsStudent member = umsUserService.getByUsername(username);
        if (member == null) {
            throw new LoginFailedException("用户不存在");
        }
        if (!member.getAvailable()) {
            throw new LoginFailedException("用户不可用状态");
        }
        return member;
    }

    @Transactional
    public boolean register(MemberRegisterDto registerDto) {
//        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
//            throw new BusinessException("密码不一致");
//        }
        UmsStudent byUsername = umsUserService.getByUsername(registerDto.getMobile());
        if (byUsername != null) {
            throw new BusinessException("账户已经存在，请登录");
        }
        String key = String.format("%s_%s_%s", AuthCodeChannel.REGISTER, registerDto.getMobile(), AuthCodeType.MOBILE);
        String s = redisService.get(key, String.class);
        AssertUtils.notNull(s, "验证码已过期");
        AssertUtils.isEq(s, registerDto.getAuthCode(), "验证码错误");
        UmsStudent member = new UmsStudent();
        member.setAvailable(true);
        member.setUsername(registerDto.getMobile());
        member.setMobile(registerDto.getMobile());
        member.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        member.setCreateTime(LocalDateTime.now());
        member.setNickname(registerDto.getMobile());
        return umsUserService.save(member);
    }


    public void sendMobileAuthCode(AuthCodeChannel channel, String target, AuthCodeType type) {
        String authCode = CodeUtils.randomCode(4);
        switch (type) {
            case MOBILE:
                AssertUtils.isTrue(Validator.isMobile(target), "请输入正确的手机号码");
                smsService.sendSms(target, channel.getCode(), "{\"code\":\""+ authCode +"\"}");
                break;
            case EMAIL:
                AssertUtils.isTrue(Validator.isEmail(target), "请输入正确的邮箱");
                break;

            default:
                break;

        }
        // 限制  不能
        log.info("验证码为{}", authCode);
        redisService.set(String.format("%s_%s_%s", channel, target, type), authCode, 60 * 30); // 半个小时
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean modifyEmail(String email, Long userId) {
        UmsStudent user = checkUser(userId);
        user.setEmail(email);
        user.setModifyTime(LocalDateTime.now());
        return umsUserService.updateById(user);
    }

    /**
     * @param userPasswordDto
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean modifyPassword(UserPasswordDto userPasswordDto, Long userId) {
        AssertUtils.isEq(userPasswordDto.getNewPassword(), userPasswordDto.getConfirmNewPassword(), "新密码与确认密码不同");
        UmsStudent user = checkUser(userId);
        String newPassword = passwordEncoder.encode(userPasswordDto.getPassword());
        AssertUtils.isEq(newPassword, user.getPassword(), "原密码不正确");
        user.setPassword(newPassword);
        user.setLatestPwdChgTime(LocalDateTime.now());
        user.setModifyTime(LocalDateTime.now());
        return umsUserService.updateById(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean modifyMobile(MobileAuthParamDto mobileAuthParamDto, Long userId) {
        // 校验验证码 TODO

        QueryWrapper<UmsStudent> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("available", true)
        queryWrapper.ne("id", userId);
        queryWrapper.eq("mobile", mobileAuthParamDto.getMobile());
        int count = umsUserService.count(queryWrapper);
        if (count > 0) {
            throw new BusinessException("该手机号已被使用，请登录使用");
        }
        UmsStudent user = checkUser(userId);
        user.setMobile(mobileAuthParamDto.getMobile());
        user.setModifyTime(LocalDateTime.now());
        return umsUserService.updateById(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean logoff(Long userId) {
        UmsStudent user = this.checkUser(userId);
        int i = iUmsStudentCourseService.countUserCourse(userId);
        if (i > 0) {
            throw new BusinessException("您有有效的学习数据，不能注销");
        }
        user.setAvailable(false);
        user.setModifyTime(LocalDateTime.now());
        // 获取用户信息
        return umsUserService.updateById(user);
    }

    private UmsStudent checkUser(Long userId) {
        UmsStudent user = umsUserService.getById(userId);
        AssertUtils.notNull(user, "用户无效，不能操作");
        return user;
    }

}
