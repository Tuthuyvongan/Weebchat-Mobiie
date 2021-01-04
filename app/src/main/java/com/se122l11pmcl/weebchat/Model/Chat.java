package com.se122l11pmcl.weebchat.Model;

public class Chat {

    private String sender;
    private String receiver;
    private String message;
    private boolean isView;

    public Chat(String sender, String receiver, String message, boolean isView) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.isView = isView;
    }

    public Chat() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIsView() {
        return isView;
    }

    public void setView(boolean view) {
        isView = view;
    }
}
