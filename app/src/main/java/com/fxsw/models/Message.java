package com.fxsw.models;

/**
 * Created by Administrator on 2015/10/27.
 */
public class Message {
    private final int SEND=0;
    private final int RECEIVE=1;
    private String sender;

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
