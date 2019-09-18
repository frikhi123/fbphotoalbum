package com.frikhi.test.myfbalbum.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;



@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}