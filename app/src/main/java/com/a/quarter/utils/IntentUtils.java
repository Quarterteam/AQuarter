package com.a.quarter.utils;

import android.content.Context;
import android.content.Intent;

/**
 * desc:页面跳转的工具类
 * author:吴晓芳
 * date:
 */

public class IntentUtils {

    public void   setIntent(Context context, Class activity){
        Intent intent = new Intent(context,activity);
        context.startActivity(intent);

    }
}
