package com.yns.wallet.util;




import com.yns.wallet.base.KeyValuePairVO;

import org.greenrobot.eventbus.EventBus;

/**
 * EventBus工具类
 *
 */

public class EventBusUtil {

    public static final String REGISTER_SUCCESS = "REGISTER_SUCCESS";

    public static final String CREATE_WALLET_SUCCESS = "CREATE_WALLET_SUCCESS";
    public static final String RESTORE_WALLET_SUCCESS = "RESTORE_WALLET_SUCCESS";
    public static final String MIGRATE_FROM_V1_SUCCESS = "MIGRATE_FROM_V1_SUCCESS";

    public static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";

    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(KeyValuePairVO event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(KeyValuePairVO event) {
        EventBus.getDefault().postSticky(event);
    }
}
