package com.yns.wallet.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.luck.picture.lib.language.LanguageConfig;
import com.yns.wallet.R;
import com.yns.wallet.base.BaseApplication;
import com.yns.wallet.base.Constant;
import com.yns.wallet.bean.LanguageBean;
import com.yns.wallet.io.SpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 多语言切换的帮助类
 *
 */
public class LanguageUtils {

    private static final String TAG = "MultiLanguageUtil";
    private static LanguageUtils instance;

    private Locale filipinoLocale = new Locale("tl","");

    private String nowLanguage = "";

    public static LanguageUtils getInstance() {
        if (instance == null) {
            synchronized (LanguageUtils.class) {
                if (instance == null) {
                    instance = new LanguageUtils();
                }
            }
        }
        return instance;
    }

    private LanguageUtils() {
    }

    /**
     * 如果不是英文、日文、中文其中一种，则默认英文
     *
     * @return
     */
    public Locale getLanguageLocale() {
        Locale locale = Locale.ENGLISH;
        if (getLanguage().equals(Locale.ENGLISH.getLanguage())) {
            locale = Locale.ENGLISH;
        }  else if (getLanguage().equals(Locale.CHINESE.getLanguage())) {
            locale = Locale.CHINESE;
        }
        return locale;
    }



    public String saveSystemCurrentLanguage() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        if(!locale.getLanguage().equals(Locale.ENGLISH.getLanguage())
                &&!locale.getLanguage().equals(Locale.CHINESE.getLanguage())){
            locale = Locale.ENGLISH;
        }
        Log.i("languageTest","当前系统语言"+locale.getLanguage());
        return locale.getLanguage();

    }

    /**
     * 更新语言
     * @param context
     * @param language
     */
    public void updateLanguage(Context context, String language) {
        SpUtil.put(Constant.APP_LANGUAGE,language);
        nowLanguage = language;
        setConfiguration(context);
    }

    public String getLanguageName(Context context) {
        if (getLanguage().equals(Locale.ENGLISH.getLanguage())) {
            return context.getString(R.string.language_english);
        } else if (getLanguage().equals(Locale.CHINESE.getLanguage())) {
            return context.getString(R.string.language_chinese);
        }
        return context.getString(R.string.language_english);
    }

    /**
     * 获取到用户保存的语言类型
     *
     * @return
     */
    public String getLanguage() {
        if(!TextUtils.isEmpty(nowLanguage)){
            return nowLanguage;
        }
        nowLanguage = (String) SpUtil.get(Constant.APP_LANGUAGE,saveSystemCurrentLanguage());
        Log.i("languageTest","当前保存的语言"+nowLanguage);
        return nowLanguage;
    }

    public static Context attachBaseContext(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context);
        } else {
            LanguageUtils.getInstance().setConfiguration(context);
            return context;
        }
    }

    /**
     * 设置语言
     */
    public void setConfiguration(Context context) {
        if (context == null) {
            Log.e(TAG, "No context, MultiLanguageUtil will not work!");
            return;
        }
        Context appContext = context.getApplicationContext();
        Log.e(TAG, "setConfiguration " + context);
        Locale targetLocale = getLanguageLocale();
        Locale.setDefault(targetLocale);
        Configuration configuration = appContext.getResources().getConfiguration();
        configuration.setLocale(targetLocale);
        context.createConfigurationContext(configuration);
        Resources resources = appContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);//语言更换生效的代码!
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = getInstance().getLanguageLocale();
        Log.d(TAG, "getLanguage ${getLanguage(locale)}");
        LocaleList localeList = new LocaleList(locale);
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    /**
     * 设置语言类型
     */
    public void setApplicationLanguage(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        Locale locale = getLanguageLocale();
        config.locale = locale;
        Log.e(TAG, "setApplicationLanguage  " + getLanguageName(context));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            config.setLocales(localeList);
            context.createConfigurationContext(config);
            Locale.setDefault(locale);
        }
        resources.updateConfiguration(config, dm);
    }

    public List<LanguageBean> getLanguageList(){
        List<LanguageBean> list = new ArrayList<>();
        list.add(new LanguageBean(BaseApplication.globalContext().getString(R.string.language_english), Locale.ENGLISH,false));
        list.add(new LanguageBean(BaseApplication.globalContext().getString(R.string.language_chinese), Locale.CHINESE,false));
        return list;
    }

    public int getPictureSelectorLanguage(){
        if(getLanguageLocale().getLanguage().equals(Locale.JAPANESE.getLanguage())){
            return LanguageConfig.JAPAN;
        }else if(getLanguageLocale().getLanguage().equals(Locale.CHINESE.getLanguage())){
            return LanguageConfig.CHINESE;
        }else{//Locale.ENGLISH
            return LanguageConfig.ENGLISH;
        }
    }

}
