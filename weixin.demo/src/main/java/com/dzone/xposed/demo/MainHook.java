package com.dzone.xposed.demo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * 微信7.0.8
 *
 * @author z.houbin
 */
public class MainHook implements IXposedHookLoadPackage {
    private Activity focusActivity;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        String loadPackageName = lpparam.packageName;
        String loadProcessName = lpparam.processName;

        Log.d(getTag(), "XC_LoadPackage.LoadPackageParam.packageName " + loadPackageName);
        Log.d(getTag(), "XC_LoadPackage.LoadPackageParam.processName " + loadProcessName);

        //只处理微信
        if ("com.tencent.mm".equals(loadPackageName)) {
            onMmHook(lpparam);
        }
    }

    private void onMmHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        Log.d(getTag(), "onMmHook");

        //获取Context,注册Activity生命周期监听
        XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new ApplicationAttachMethod());

        //我界面,隐藏个人微信号
        XposedBridge.hookAllMethods(TextView.class, "setText", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                String text = String.valueOf(param.args[0]);
                Log.e(getTag(), text);
                if (text.startsWith("微信号：")) {
                    param.args[0] = "微信号：已隐藏";
                }
            }
        });
    }


    private class ApplicationAttachMethod extends XC_MethodHook {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            super.beforeHookedMethod(param);
        }

        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            super.afterHookedMethod(param);

            Application context = (Application) param.thisObject;
            //注册广播
            IntentFilter filter = new IntentFilter("com.mm.test");
            context.registerReceiver(new MMBroadcast(), filter);
            //生命周期监听
            context.registerActivityLifecycleCallbacks(new AbstractLifecycleCallbacks() {
                @Override
                public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                    super.onActivityCreated(activity, savedInstanceState);
                }

                @Override
                public void onActivityResumed(@NonNull Activity activity) {
                    super.onActivityResumed(activity);
                    focusActivity = activity;
                    showToast("onActivityResumed: " + activity.getLocalClassName());
                    //打印当前登录微信号
                    Log.d(getTag(), "LoginId: " + getLoginWeiXinId());
                }

                @Override
                public void onActivityDestroyed(@NonNull Activity activity) {
                    super.onActivityDestroyed(activity);
                    showToast("onActivityDestroyed: " + activity.getLocalClassName());
                }
            });
        }
    }

    private String getLoginWeiXinId() {
        if (focusActivity != null) {
            SharedPreferences sharedPreferences = focusActivity.getSharedPreferences("com.tencent.mm_preferences", Context.MODE_PRIVATE);
            return sharedPreferences.getString("login_weixin_username", "");
        } else {
            return "";
        }
    }

    private static String getTag() {
        return MainHook.class.getSimpleName();
    }

    private void showToast(final String message) {
        if (focusActivity != null) {
            focusActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(focusActivity, message, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
