package com.fxsw.models;

<<<<<<< HEAD
/**
 * Created by 1 on 2015/10/26.
 */
public class Message {
    private final static int SEND=0;
    private  final static int RECEIVE=1;
    private String sender;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
=======
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Administrator on 2015/10/27.
 */
public class Message {
    private final int SEND=0;
    private final int RECEIVE=1;
    private String sender;

    public Message(int type, String content) {
        this.type = type;
        this.content = content;
        if(type==0){
            this.sender= "我";
        }
        else {
            this.sender="小明";
        }
>>>>>>> f243f11de772e94ae1fdb24f29ae7159e91a8429
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

<<<<<<< HEAD
=======
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

>>>>>>> f243f11de772e94ae1fdb24f29ae7159e91a8429
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private int type;
    private String content;
}
