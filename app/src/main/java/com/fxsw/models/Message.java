package com.fxsw.models;

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
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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
