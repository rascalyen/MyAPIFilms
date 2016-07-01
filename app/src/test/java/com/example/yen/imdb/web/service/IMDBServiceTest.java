package com.example.yen.imdb.web.service;

import com.example.yen.imdb.RobolectricTestCase;
import com.example.yen.imdb.data.response.IMDBResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;


public class IMDBServiceTest extends RobolectricTestCase {

    @Mock IMDBService imdbService;
    @Mock Call<IMDBResponse> call;
    @Mock Throwable throwable;
    @Mock IMDBResponse imdbResponse;
    private Response<IMDBResponse> response;  // Mockito wouldn't mock final class
    @Captor ArgumentCaptor<Callback<IMDBResponse>> callbackCaptor;
    private IMDBClientStub imdbClient;


    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);
        imdbClient = new IMDBClientStub(imdbService);
        createMockResponse();
    }

    private void createMockResponse() {
        response = Response.success(imdbResponse);
    }

    @Test public void getInTheaters() {
        given(imdbService.getInTheaters("")).willReturn(call);

        imdbClient.getInTheaters("");

        verify(imdbService).getInTheaters(eq(""));
        verify(imdbService.getInTheaters("")).enqueue(callbackCaptor.capture());
        assertNull(imdbClient.getResponse());
        assertNull(imdbClient.getThrowable());

        callbackCaptor.getValue().onResponse(call, response);
        assertThat(imdbClient.getResponse(), is(equalTo(response)));

        callbackCaptor.getValue().onFailure(call, throwable);
        assertThat(imdbClient.getThrowable(), is(equalTo(throwable)));
    }

    @After public void tearDown() {
        imdbService = null;
        response = null;
        throwable = null;
        callbackCaptor = null;
        imdbClient = null;
        call = null;
        imdbResponse = null;
    }

}