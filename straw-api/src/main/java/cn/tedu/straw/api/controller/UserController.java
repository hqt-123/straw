package cn.tedu.straw.api.controller;


import cn.tedu.straw.api.dto.StudentRegisterDTO;
import cn.tedu.straw.api.ex.ServiceException.InserException;
import cn.tedu.straw.api.ex.ServiceException.InviteCodeException;
import cn.tedu.straw.api.ex.ServiceException.PhoneDuplicateException;
import cn.tedu.straw.api.service.IUserService;
import cn.tedu.straw.commons.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUserService userService;

//    @RequestMapping("ex")
//    public String a() {
//        try {
//            throw new NullPointerException();
//        } catch (NullPointerException e) {
//            return "异常已经被处理！！！";
//        }
//    }


    @RequestMapping("/student/register")
    public R regStudtent(StudentRegisterDTO studentRegisterDTO) {
        R r = new R();
        try {
            userService.regStudent(studentRegisterDTO);
            r.setMessage("注册成功！");
            return r;
        } catch (InviteCodeException e) {
            r.setMessage("注册失败！邀请码错误！");
            return r;
        } catch (PhoneDuplicateException e) {
            r.setMessage("注册失败！手机号已经被占用！");
            return r;
        } catch (InserException e) {
            r.setMessage("注册失败！服务器忙，请稍后再次尝试！");
            return r;
        } catch (Throwable e) {
            r.setMessage("注册失败！出现预期以外的异常！" + e.getClass() + "," + e.getMessage());
            return r;
        }

    }

}
