package cn.zsmy.akm.salon.bean;

import java.io.Serializable;

/**
 * 一个图片对象
 *
 * @author Administrator
 *
 */
public class ImageModule implements Serializable {
    public String imageId;
    public String thumbnailPath;
    public String imagePath;
    public boolean isSelected = false;
}
