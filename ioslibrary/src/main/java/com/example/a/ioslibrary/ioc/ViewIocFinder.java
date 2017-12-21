package com.example.a.ioslibrary.ioc;

import android.app.Activity;
import android.view.View;

/**
 * Created by $chenzhikai on 2017/12/21.
 */

public class ViewIocFinder {
    private Object mObject;
    private View mView;
    private Activity mActivity;

    public ViewIocFinder(Object object, View view) {
        this.mObject = object;
        this.mView = view;
    }

    public ViewIocFinder(Activity activity) {
        this.mActivity = activity;
    }

    public ViewIocFinder(View view) {
        this.mView = view;
    }

    public View findViewById(int viewId){

        return  mActivity!=null ?mActivity.findViewById(viewId):mView.findViewById(viewId);
    }


}
