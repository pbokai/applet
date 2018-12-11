package top.guolisha.lisaapplet.service;

import org.springframework.stereotype.Service;
import top.guolisha.lisaapplet.bean.UserInfo;
import top.guolisha.lisaapplet.mapper.UserInfoMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoService {

    @Resource
    UserInfoMapper userInfoMapper;

    public List<UserInfo> login(){
        List<UserInfo> userInfo = userInfoMapper.login();
        return userInfo;
    }

    public void addWeChatUser(UserInfo userInfo){
        userInfoMapper.addWeChatUser(userInfo);
    }

    public UserInfo selectWeChatUser(String openid){
       return userInfoMapper.selectWeChatUser(openid);
    }
}
