package aqcxbom.xposedhooktarget;

import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by AqCxBoM on 2016/12/12.
 */

public class MyClass {
    private final static String TAG = "AqCxBoM";
    public static void helloWorld(int n, String str)    {
        LOGI(n + " - " + str);
    }
    public static boolean fun1(String[][] strAry, Map mp1, Map<String,String> mp2,
                               Map<Integer, String> mp3,
                               ArrayList<String> al1, ArrayList<Integer> al2,
                               ArgClass ac)    {
        LOGI("fun1 working!!!");
        return true;
    }
    private String fun2(String[][] strAry, Map mp1, Map<String,String> mp2, Map<Integer, String> mp3)    {
        LOGI("fun2 working!!!");
        return "This is fun2 ret!";
    }
    protected static Class fun3(String[][] strAry, Map mp1, Map<String,String> mp2, Map<Integer, String> mp3)    {
        LOGI("fun3 working!!!");
        return Map.class;
    }
    public static void LOGI(String context)    {
        Log.i(TAG, context);
    }
}
