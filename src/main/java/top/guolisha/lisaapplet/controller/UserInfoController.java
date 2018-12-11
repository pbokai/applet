package top.guolisha.lisaapplet.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.guolisha.lisaapplet.bean.WeChatAppLoginReq;
import top.guolisha.lisaapplet.common.AES;
import top.guolisha.lisaapplet.common.Constant;
import top.guolisha.lisaapplet.common.HmacUtil;
import top.guolisha.lisaapplet.service.UserInfoService;
import top.guolisha.lisaapplet.bean.UserInfo;

import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
public class UserInfoController {

    private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/getUser")
    public Map<String, Object> getUser(String word){
        System.out.println("map"+word);

        System.out.println("微信小程序正在调用。。。");
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> list = new ArrayList<String>();
        list.add("zhangsan");
        list.add("lisi");
        list.add("wanger");
        list.add("mazi");
        map.put("list",list);
        System.out.println("微信小程序调用完成。。。");
        return map;
    }

    @RequestMapping(value = "/authorization",method = RequestMethod.POST)
    @ResponseBody
    public Map authorization(WeChatAppLoginReq req) throws Exception {
        logger.info("UserInfoController_authorization_WeChatAppLoginReq: "+req);
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + Constant.WX_APPID + "&secret=" + Constant.WX_SECRET + "&js_code=" + req.getCode() + "&grant_type=" + Constant.GRANT_TYPE;
        Map resultMap = new HashMap<>();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        logger.info("UserInfoController_authorization_responseEntity: "+responseEntity);
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            JSONObject jsonObj = JSON.parseObject(responseEntity.getBody());

            String openId = jsonObj.getString("openid");
            //随机生成密钥
            byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
            //构建
            SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
            String sessionid = aes.encryptHex(openId);
            resultMap.put("sessionid",sessionid);
            String sessionKey = jsonObj.getString("session_key");
            String signature = HmacUtil.SHA1(req.getRawData() + sessionKey);

            if (!signature.equals(req.getSignature())) {
                logger.info(" UserInfoController_authorization_signature_weChat: " + req.getSignature());
                logger.info(" UserInfoController_authorization_signature_weChatService: " + signature);
            }

            byte[] resultByte = null;
            try {
                resultByte = AES.decrypt(Base64.decode(req.getEncryptedData()), Base64.decode(sessionKey), Base64.decode(req.getIv()));
            } catch (Exception e) {
                logger.error("UserInfoController_authorization_resultByte: " + e);
            }
            if (null != resultByte && resultByte.length > 0) {
                String userInfoStr = "";
                try {
                    userInfoStr = new String(resultByte, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    logger.error("UserInfoController_authorization_userInfoStr_e: "+e.getMessage());
                }
                JSONObject userInfoObj = JSON.parseObject(userInfoStr);
                UserInfo userInfo = new UserInfo();
                userInfo.setNickName(userInfoObj.getString("nickName"));
                userInfo.setCreateTime(DateUtil.now());
                userInfo.setGender(userInfoObj.getInteger("gender"));
                userInfo.setAvatarUrl(userInfoObj.getString("avatarUrl"));
                userInfo.setOpenId(userInfoObj.getString("openId"));
                userInfo.setCity(userInfoObj.getString("city"));
                userInfo.setCountry(userInfoObj.getString("country"));
                userInfo.setLanguage(userInfoObj.getString("language"));
                userInfo.setProvince(userInfoObj.getString("province"));
                userInfo.setSessionid(sessionid);
                resultMap.put("status",1);
                userInfoService.addWeChatUser(userInfo);
            }else {
                resultMap.put("status",0);
               logger.info("UserInfoController_authorization_resultByte: "+resultByte);
            }

        }else{
            logger.error("UserInfoController_authorization_responseEntity_responseEntity.getStatusCode: "+HttpStatus.OK);
        }
        return resultMap;
    }


}
