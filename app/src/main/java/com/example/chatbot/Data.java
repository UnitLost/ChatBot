package com.example.chatbot;

public class Data {
    private int imgId;
    public int leftRight;//0_left;1_right
    private String content;

    public Data() {}

    public Data(int imgId, String content,int leftRight) {
        this.imgId = imgId;
        this.content = content;
        this.leftRight = leftRight;
    }

    public int getImgId() {
        return imgId;
    }

    public int getLeftRight() {
        return leftRight;
    }

    public String getContent() {
        return content;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setLeftRight(int leftRight) {
        this.leftRight = leftRight;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isMe(){
        if(this.leftRight==1) return true;
        else return false;
    }
}