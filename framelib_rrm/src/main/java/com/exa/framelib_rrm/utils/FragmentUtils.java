package com.exa.framelib_rrm.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.exa.framelib_rrm.R;
import com.exa.framelib_rrm.app.App;

import java.util.List;

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class FragmentUtils {

    private  static android.support.v4.app.FragmentTransaction transaction;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void setDefaultFragment(FragmentManager fragmentManager, int framlayout, List<Fragment> fragmentList) {
        transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            if (i == 0) {
                transaction.add(framlayout, fragmentList.get(i));

            } else {
                transaction.add(framlayout, fragmentList.get(i));
                transaction.hide(fragmentList.get(i));
            }

        }


        transaction.commit();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public  static void hideAndShow(FragmentManager manager,List<Fragment> list, Fragment fragment){
        FragmentTransaction transaction1 = manager.beginTransaction();

        for (int i = 0; i <list.size() ; i++) {
            transaction1.hide(list.get(i));
        }
        transaction1.show(fragment);
        transaction1.commit();

    }
}
