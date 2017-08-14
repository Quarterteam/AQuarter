package com.exa.framelib_rrm.base.model.http.tag;

/**
 * Created by acer on 2017/8/11.
 * 在父类的基础上，还可以标记在列表中对应的位置
 */
public class SinglePositionTag extends BaseTag{

    public byte tag;
    public int position;

    public SinglePositionTag(byte tag, int position) {
        super(tag);
        this.position = position;
    }

}
