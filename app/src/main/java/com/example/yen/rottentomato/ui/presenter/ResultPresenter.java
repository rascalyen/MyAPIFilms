package com.example.yen.rottentomato.ui.presenter;

import android.support.annotation.NonNull;
import com.example.yen.rottentomato.data.model.SearchResult;
import com.example.yen.rottentomato.ui.view.ResultView;
import com.example.yen.rottentomato.utils.StringUtils;
import com.example.yen.rottentomato.web.TomatoClient;
import java.util.Properties;
import javax.inject.Inject;

/**
 * Created by yenhuang on 2/10/16.
 */
public class ResultPresenter implements Presenter {

    private TomatoClient tomatoClient;
    private Properties properties;
    private ResultView resultView;


    @Inject public ResultPresenter(TomatoClient tomatoClient, Properties properties) {
        this.tomatoClient = tomatoClient;
        this.properties = properties;
    }

    public void setResultView(@NonNull ResultView resultView) {
        this.resultView = resultView;
    }

    public void initialize(String query) {
        resultView.showProgress();



    }

    @Override
    public void resume() {}

    @Override
    public void pause() {}


}
