package top.guolisha.lisaapplet.bean;

public class WeChatAppLoginReq {
    private String code;

    private String rawData;

    private String encryptedData;

    private String iv;

    private String signature;

    public WeChatAppLoginReq() {
    }

    public WeChatAppLoginReq(String code, String rawData, String encryptedData, String iv, String signature) {
        this.code = code;
        this.rawData = rawData;
        this.encryptedData = encryptedData;
        this.iv = iv;
        this.signature = signature;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "code: "+code+"  :::rawData: "+rawData+"  :::encryptedData: "+encryptedData+"  :::iv: "+iv+"  :::signature: "+signature;
    }
}
