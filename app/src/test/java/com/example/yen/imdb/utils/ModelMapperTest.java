package com.example.yen.imdb.utils;

import com.example.yen.imdb.RobolectricTestCase;
import com.example.yen.imdb.data.entity.MovieEntity;
import com.example.yen.imdb.data.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static com.example.yen.imdb.utils.ModelMapper.toMovieModel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class ModelMapperTest extends RobolectricTestCase {

    @Mock MovieEntity movieEntity;
    private String title;


    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test public void testToMovieModel() {
        given(movieEntity.getTitle()).willReturn("title");

        title = toMovieModel(movieEntity).getTitle();

        verify(movieEntity).getTitle();
        assertThat(toMovieModel(movieEntity), isA(Movie.class));
        assertThat(title, is(movieEntity.getTitle()));
    }

    @After public void tearDown() {
        movieEntity = null;
        title = null;
    }

}