package com.example.yen.imdb.configs.dagger.component;

import com.example.yen.imdb.IMDBApplication;
import com.example.yen.imdb.data.web.api.IMDBService;
import com.example.yen.imdb.ui.navigation.Navigator;
import java.util.Properties;


public interface BaseAppComponent {

    void injectApplication(IMDBApplication application);

    Properties properties();
    IMDBService imdbService();
    Navigator navigator();
}