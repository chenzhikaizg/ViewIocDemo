package com.example.a.ioslibrary.ioc;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by $chenzhikai on 2017/12/21.
 */

public class ViewIocUtils {
    //注入的方法

    public static void inject(Activity activity) {
        inject(new ViewIocFinder(activity), activity);
    }

    public static void inject(View view) {
        inject(new ViewIocFinder(view), view);
    }

    public static void inject(View view, Object object) {
        inject(new ViewIocFinder(view), object);
    }

    public static void inject(ViewIocFinder finder, Object object) {

        injectFiled(finder, object);
        injectEvent(finder, object);
    }

    /**
     * 属性注入
     *
     * @param finder
     * @param object
     */
    private static void injectFiled(ViewIocFinder finder, Object object) {
        //获取CLASS
        Class<?> clazz = object.getClass();
        //获取所有的属性包括共有和私有
        Field[] files = clazz.getDeclaredFields();
        //获取ViewById的value值
        for (Field field : files) {
            ViewById viewById = field.getAnnotation(ViewById.class);
            if (viewById != null) {
                //获取注解里面的id值
                int value = viewById.value();
                //通过value来获取view
                View view = finder.findViewById(value);
                if (view != null) {
                    //能够注入所有的修饰符
                    field.setAccessible(true);
                    try {
                        //动态的注入找到view里
                        field.set(object, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 事件注入
     *
     * @param finder
     * @param object
     */
    private static void injectEvent(ViewIocFinder finder, Object object) {
        Class<?> clazz = object.getClass();
        //获取类里面的所有方法
        Method[] mMethods = clazz.getDeclaredMethods();

        //获取onclick里面的value值
        for (Method method : mMethods) {
            OnClick onclick = method.getAnnotation(OnClick.class);
            if (onclick != null) {
                int[] valueIds = onclick.value();
                for (int id : valueIds) {
                    View view = finder.findViewById(id);
                    if (view != null) {
                        view.setOnClickListener(new DeclaredOnClickListener(method, object));
                    }
                }
            }
        }
    }
    private static class DeclaredOnClickListener implements View.OnClickListener {
        private Method mMethod;
        private Object mObject;
        public DeclaredOnClickListener(Method method, Object object) {
            this.mMethod = method;
            this.mObject = object;
        }
        @Override
        public void onClick(View v) {
            mMethod.setAccessible(true);
            try {
                mMethod.invoke(mObject, v);
            } catch (Exception e) {
                try {
                    mMethod.invoke(mObject, null);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}
