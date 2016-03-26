package cn.zsmy.akm.entity;

import android.graphics.Bitmap;

/**
 * 论坛帖子实体类
 * Created by wanjingyan
 * on 2015/12/7 11:34.
 */
public class ForumPosts {
    private String postsTitle;//帖子标题
    private String postsContent;//帖子内容
    private String postsImage;//帖子中的图片
    private Bitmap postedHeadImg;//发帖人头像
    private String postedName;//发帖人名字
    private String postTime;//发帖时间
    private int commentNumber;//收藏人数
    private int ExamineNumber;//查阅人数

    public String getPostsTitle() {
        return postsTitle;
    }

    public void setPostsTitle(String postsTitle) {
        this.postsTitle = postsTitle;
    }

    public String getPostsContent() {
        return postsContent;
    }

    public void setPostsContent(String postsContent) {
        this.postsContent = postsContent;
    }

    public String getPostsImage() {
        return postsImage;
    }

    public void setPostsImage(String postsImage) {
        this.postsImage = postsImage;
    }

    public Bitmap getPostedHeadImg() {
        return postedHeadImg;
    }

    public void setPostedHeadImg(Bitmap postedHeadImg) {
        this.postedHeadImg = postedHeadImg;
    }

    public String getPostedName() {
        return postedName;
    }

    public void setPostedName(String postedName) {
        this.postedName = postedName;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public int getExamineNumber() {
        return ExamineNumber;
    }

    public void setExamineNumber(int examineNumber) {
        ExamineNumber = examineNumber;
    }
}
