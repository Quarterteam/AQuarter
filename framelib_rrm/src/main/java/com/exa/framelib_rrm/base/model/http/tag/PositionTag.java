package com.exa.framelib_rrm.base.model.http.tag;

import java.util.LinkedHashMap;

/**
 * Created by acer on 2017/7/16.
 * 在父类的基础上，还可以标记在多级列表中对应的位置
 */
public class PositionTag extends BaseTag{

    public byte tag;
    public LinkedHashMap<String, Integer> positions;

    public PositionTag(byte tag, LinkedHashMap<String, Integer> positions) {
        super(tag);
        this.positions = positions;
    }

}
