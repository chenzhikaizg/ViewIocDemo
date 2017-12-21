package com.example.a.ioslibrary.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by $chenzhikai on 2017/12/21.
 */

//METHOD 在方法上 ，FIELD代表属性 TYPE类上
@Target(ElementType.METHOD)
//什么时候生效 CLASS编译时 RUNTIME运行时
@Retention(RetentionPolicy.RUNTIME)
public @interface OnClick {
    //表示可以传一个数组}
    int[] value();
}
