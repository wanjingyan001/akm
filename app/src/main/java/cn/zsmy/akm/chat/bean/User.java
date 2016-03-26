package cn.zsmy.akm.chat.bean;

import java.io.Serializable;

/**
 * Created by sanz on 2015/12/29 19:28
 */
public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;//用戶id
    private String icon;//用户头像
    private String nickName;//用户昵称
    private String account;//账户名   现在账户都是手机号注册的

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
