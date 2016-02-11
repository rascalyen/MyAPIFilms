package com.example.yen.rottentomato.ui.dependency.scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import javax.inject.Scope;

/**
 * Created by yenhuang on 2/10/16.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {}