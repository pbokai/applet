package top.guolisha.lisaapplet.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.guolisha.lisaapplet.bean.UserInfo;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    List<UserInfo> login();
    void addWeChatUser(UserInfo userInfo);

    UserInfo selectWeChatUser(String openid);
}
