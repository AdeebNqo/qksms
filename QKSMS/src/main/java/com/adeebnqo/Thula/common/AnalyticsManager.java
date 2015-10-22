package com.adeebnqo.Thula.common;

import android.content.Context;
import android.util.Log;
import com.adeebnqo.Thula.R;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONObject;

public enum AnalyticsManager {
    INSTANCE;

    private final static String TAG = "AnalyticsManager";
    private final static boolean LOCAL_LOGV = false;

    /**
     * Event categories
     */
    public final static String CATEGORY_MESSAGES = "messages";
    // Note: For preferences events, the action will just be the preference value.
    public final static String CATEGORY_PREFERENCE_CHANGE = "preference_change";
    public final static String CATEGORY_PREFERENCE_CLICK = "preference_click";
    public final static String CATEGORY_REPORT = "report";

    /*

    Event fields

     */
    public final static String FIELD_ADDRESS = "address";

    /**
     * Event actions
     */
    public final static String ACTION_SEND_MESSAGE = "send_message";
    public final static String ACTION_ATTACH_IMAGE = "attach_image";
    public final static String ACTION_ATTACH_FROM_CAMERA = "attach_from_camera";
    public final static String ACTION_RECEIVED_SPAM = "recieved_spam";
    public final static String ACTION_RECEIVED_MSG = "recieved_new_msg";

    private boolean mNeedsInit = true;
    private Context mContext;
    MixpanelAPI mixpanel;

    public static AnalyticsManager getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        if (LOCAL_LOGV) Log.v(TAG, "init called. mNeedsInit: " + mNeedsInit);

        if (mNeedsInit) {
            mNeedsInit = false;
            mContext = context;

            String projectToken = context.getString(R.string.mixpanel_key);
            mixpanel = MixpanelAPI.getInstance(mContext, projectToken);
        }
    }

    public void sendEvent(String eventName, JSONObject details){
        if (mixpanel != null){
            mixpanel.track(eventName, details);
        }
    }
}
