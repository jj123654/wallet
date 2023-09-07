package com.yns.wallet.io;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.List;


/**
 * 存储通用类，用来存储各种需要缓存的数据
 *
 * @author admin
 */
public class SpUtil {

    public static SharedPreferences sp;

    // 单例
    private static SpUtil spUtil = null;

    public static final String SP_NAME = "SP_NAME";

    public static final String USERVO = "USERVO";
    public static final String LIST = "LIST";
    public static final String ACCOUNT_NUM = "ACCOUNT_NUM";
    public static final String TAB_LIST = "TAB_LIST";
    public static final String PAY_CODE_GUID = "PAY_CODE_GUID";
    public static final String CLUB_PHOTO = "CLUB_PHOTO";
    public static final String IS_SHOW_CLUB_HIGHLIGHT = "IS_SHOW_CLUB_HIGHLIGHT";
    public static final String IS_SHOW_ACCOUNT_HIGHLIGHT = "IS_SHOW_ACCOUNT_HIGHLIGHT";
    public static final String DELIVER_CITY_LIST = "DELIVER_CITY_LIST";
    public static final String SIMULATION_TEST_WRONG_LIST = "SIMULATION_TEST_WRONG_LIST";
    public static final String APP_VERSION = "APP_VERSION";//App版本号


    public static void initSp(Context context) {
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }


    /**
     * 存入某个key对应的value值
     *
     * @param key
     * @param value
     * @return
     */
    public static String put(String key, Object value) {
        SharedPreferences.Editor edit = sp.edit();
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        }
        edit.apply();
        return key;
    }

    /**
     * 得到某个key对应的值
     *
     * @param key
     * @param defValue
     * @return
     */
    @Nullable
    public static Object get(String key, Object defValue) {
        if (defValue instanceof String) {
            return sp.getString(key, (String) defValue);
        } else if (defValue instanceof Integer) {
            return sp.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Float) {
            return sp.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Long) {
            return sp.getLong(key, (Long) defValue);
        }
        return null;
    }

    /**
     * 存实体类
     *
     * @param key
     * @param t
     * @param <T>
     */
    public static <T> void putObject(String key, T t) {
        if (null != t) {
            JsonUtils.saveObjectToSharePreferences(t, key);
        } else {
            SharedPreferences.Editor editor = sp.edit();
            editor.remove(key);
            editor.apply();
        }
    }

    /**
     * 获取实体类
     */
    public static <T> T getObject(String key, Class<T> cls) {
        return JsonUtils.getObjectFromSharePreferences(
                key, cls);
    }

    /**
     * 存列表
     *
     * @param key
     * @param t
     * @param <T>
     */
    public static <T> void putList(String key, List<T> t) {
        if (null != t) {
            JsonUtils.saveListToSharePreferences(t, key);
        } else {
            SharedPreferences.Editor editor = sp.edit();
            editor.remove(key);
            editor.commit();
        }
    }

    /**
     * 获取列表
     */
    public static <T> List<T> getList(String key, Class<T> cls) {
        Type type = new ListParameterizedType(cls);
        return JsonUtils.getListFromSharePreferences(
                key, type);

    }

    public static void remove(String key) {
        if (sp.contains(key)) {
            sp.edit().remove(key).apply();
        }
    }


}
