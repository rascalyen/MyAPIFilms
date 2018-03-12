package com.example.yen.imdb.configs.dagger.scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import javax.inject.Scope;


@Scope
@Retention(RUNTIME)
public @interface PerActivity {}