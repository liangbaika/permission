package com.lq.service.impl;

import com.lq.enums.DataUseful;
import com.lq.enums.ErrorCode;
import com.lq.exception.ParamException;
import com.lq.mapping.BeanMapper;
import com.lq.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.lq.entity.SysUser;
import com.lq.repository.SysUserRepository;
import com.lq.model.SysUserModel;
import com.lq.service.SysUserService;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SysUserRepository sysUserRepo;

    @Transactional
    @Override
    public int create(SysUserModel sysUserModel) {
        ParamValidator.check(sysUserModel);
        if (checkEmailExist(sysUserModel.getMail())) {
            throw new ParamException(ErrorCode.EMAIL_EXIST.getMsg());
        }
        if (checkPhoneExist(sysUserModel.getTelephone())) {
            throw new ParamException(ErrorCode.PHONE_EXIST.getMsg());
        }
        //内部系统 自己生成密码
        String passwd = PasswdGenerator.generatePasswdRandom();
        //todo  send  the email to notice  the  user  about  password
        sysUserModel.setPassword(EncryptUtils.Md5Encrypt(passwd));
        sysUserModel.setOperateIp("127.0.0.1");
        sysUserModel.setOperateTime(new Date());
        sysUserModel.setOperater(LoginHolder.getUser().getUsername());
        return sysUserRepo.insertSelective(beanMapper.map(sysUserModel, SysUser.class));
    }

    private boolean checkPhoneExist(String telephone) {
        SysUserModel sysUserModel = sysUserRepo.selectUserByKeyWord(telephone);
        return sysUserModel==null? false:true;
    }
    private boolean checkNameExist(String telephone) {
        SysUserModel sysUserModel = sysUserRepo.selectUserByKeyWord(telephone);
        return sysUserModel==null? false:true;
    }
    private boolean checkEmailExist(String mail) {
        SysUserModel sysUserModel = sysUserRepo.selectUserByKeyWord(mail);
        return sysUserModel==null? false:true;
    }

    @Transactional
    @Override
    public int createSelective(SysUserModel sysUserModel) {
        return sysUserRepo.insertSelective(beanMapper.map(sysUserModel, SysUser.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysUserRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public SysUserModel findByPrimaryKey(Integer id) {
        SysUser sysUser = sysUserRepo.selectByPrimaryKey(id);
        return beanMapper.map(sysUser, SysUserModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public long selectCount(SysUserModel sysUserModel) {
        return sysUserRepo.selectCount(beanMapper.map(sysUserModel, SysUser.class));
    }

    @Transactional(readOnly = true)
    @Override
    public List<SysUserModel> selectPage(SysUserModel sysUserModel, Pageable pageable) {
        SysUser sysUser = beanMapper.map(sysUserModel, SysUser.class);
        return beanMapper.mapAsList(sysUserRepo.selectPage(sysUser, pageable), SysUserModel.class);
    }

    @Override
    public Integer login(SysUserModel sysUserModel) {
        if (sysUserModel == null || sysUserModel.getPassword() == null || StringUtils.isBlank(sysUserModel.getKeyWord())) {
            throw new ParamException(ErrorCode.PARAM_MISS.getMsg());
        }
        SysUserModel sysUserModelDB = sysUserRepo.selectUserByKeyWord(sysUserModel.getKeyWord());
        if (sysUserModelDB == null) {
            throw new ParamException(ErrorCode.USER_NOT_EXIST.getMsg());
        }
        if (sysUserModelDB.getStatus().equals(DataUseful.NOUSEFUL.getCode())) {
            throw new ParamException(ErrorCode.USER_FROZI.getMsg());
        }
        if (!sysUserModelDB.getPassword().equals(EncryptUtils.Md5Encrypt(sysUserModel.getPassword()))) {
            throw new ParamException(ErrorCode.PARAM_ERROR.getMsg());
        }
        // put  the  user  info  in  http session
        HttpServletRequest currentRequest = LoginHolder.getRequest();
        if (currentRequest != null) {
            HttpSession session = currentRequest.getSession(true);
            session.setAttribute("user", sysUserModelDB);
        } else {
            // generally, can not reach  hear
            log.debug("put user in seesion error");
        }

//        return  1; a flag >0  represent success
        return DataUseful.USEFUL.getCode();
    }

    @Override
    public Integer logout() {
        HttpSession session = LoginHolder.getRequest().getSession();
        if (session != null) {
            session.invalidate();
        } else {
            return DataUseful.NOUSEFUL.getCode();
        }
        return DataUseful.USEFUL.getCode();
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(SysUserModel sysUserModel) {
        return sysUserRepo.updateByPrimaryKey(beanMapper.map(sysUserModel, SysUser.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(SysUserModel sysUserModel) {
        ParamValidator.check(sysUserModel);
        SysUserModel before = findByPrimaryKey(sysUserModel.getId());
        if (checkEmailExist(sysUserModel.getMail())) {
            throw new ParamException(ErrorCode.EMAIL_EXIST.getMsg());
        }
        if (checkPhoneExist(sysUserModel.getTelephone())) {
            throw new ParamException(ErrorCode.PHONE_EXIST.getMsg());
        }

        if (before == null) {
            throw new ParamException(ErrorCode.USER_NOT_EXIST.getMsg());
        }
        if (sysUserModel.getPassword() != null) {
            String passwd = EncryptUtils.Md5Encrypt(sysUserModel.getPassword());
            sysUserModel.setPassword(passwd);
        }
        sysUserModel.setOperateTime(new Date());
        return sysUserRepo.updateByPrimaryKeySelective(beanMapper.map(sysUserModel, SysUser.class));
    }

}
