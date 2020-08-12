package cn.tedu.straw.api.service.impl;


import cn.tedu.straw.api.dto.StudentRegisterDTO;
import cn.tedu.straw.api.ex.ServiceException.InserException;
import cn.tedu.straw.api.ex.ServiceException.InviteCodeException;
import cn.tedu.straw.api.ex.ServiceException.PhoneDuplicateException;
import cn.tedu.straw.api.mapper.ClassInfoMapper;
import cn.tedu.straw.api.mapper.UserMapper;
import cn.tedu.straw.api.service.IUserService;
import cn.tedu.straw.commons.model.ClassInfo;
import cn.tedu.straw.commons.model.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Override
    public void regStudent(StudentRegisterDTO studentRegisterDTO) {
        log.debug("准备注册：参数StudentRegisterDTO >>> {}", studentRegisterDTO);
        QueryWrapper<ClassInfo> classInfoQueryWrapper = new QueryWrapper<>();
        classInfoQueryWrapper.eq("invite_code",studentRegisterDTO.getInviteCode());
        ClassInfo classInfo = classInfoMapper.selectOne(classInfoQueryWrapper);



        log.debug("根据邀请码查询班级信息 >>>{}",classInfo);

        if (classInfo == null){
            throw  new InviteCodeException();
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone",studentRegisterDTO.getPhone());
        User result = userMapper.selectOne(userQueryWrapper);
        log.debug("根据手机号查询用户>>>{}",result);

        if (result != null){
            throw  new PhoneDuplicateException();
        }

        User user =  new User();

        user.setUsername((studentRegisterDTO.getPhone()));

        user.setNickname(studentRegisterDTO.getNickname());

        user.setPassword(studentRegisterDTO.getPassword());

        user.setGender(studentRegisterDTO.getGender());

        user.setDayOfBirth(studentRegisterDTO.getDayOfBirth());

        user.setPhone(studentRegisterDTO.getPhone());

        user.setClassId(classInfo.getId());

        user.setCreatedTime(LocalDateTime.now());

        user.setEnabled(1);

        user.setLocked(0);

        user.setType(0);

        user.setSelfIntroduction(studentRegisterDTO.getSelfIntroduction());

        log.debug("准备插入用户数据>>>{}",user);

        int rows = userMapper.insert(user);

        log.debug("插入用户数据>>>{}",rows);

        if (rows !=1){
            throw  new InserException();
        }
    }
}
