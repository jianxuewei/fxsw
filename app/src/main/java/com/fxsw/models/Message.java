package com.fxsw.models;

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
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private int type;
    private String content;
}
