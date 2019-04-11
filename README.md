# MyAPIFilms IMDB

Showcase common android implementation with [MyAPIFilms](http://api.myapifilms.com/index.do) IMDB API. This app shows “IN THEATERS” movie list on main page, and shows clicked movie details on next page.
<p align="left">
<img height="380" src="https://github.com/rascalyen/ApiMovies/blob/master/screenshot/00.png" />
<img height="380" src="https://github.com/rascalyen/ApiMovies/blob/master/screenshot/01.png" />
<img height="380" src="https://github.com/rascalyen/ApiMovies/blob/master/screenshot/02.png" />
</p><br>

[Click here for demo video](http://tinyurl.com/zcpotsl)


### Technical Features
From this project you should find useful examples like,

* Build android with MVVM design pattern + [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html) + [LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html)
* Use Dependency Injection ([Dagger2](http://google.github.io/dagger/)) to separate configuration (properties, imageClient, httpClient, etc.) and UI usage
* Use [Butterknife](https://github.com/JakeWharton/butterknife) for view injection in Activity/Fragment
* Use [Retrofit2](http://square.github.io/retrofit/) and [Moshi](https://github.com/square/moshi) to call RESTful API and parse returned JSON response
* [RxJAVA](https://github.com/ReactiveX/RxJava) as Retrofit2 call adapter
* [Glide](https://github.com/bumptech/glide) as image client
* Define productFlavors in gradle script
* Read properties from /res/raw resource (However, BuildConfig is preferable)
* <s>How to save/restore Fragment's state</s> -> No need thanks to ViewModel
* Use RecyclerView and CardView

### Debug Environment
* Two different Dagger setups for debug and release
* [LeakCanary](https://github.com/square/leakcanary) to find memory leak EARLY
* [OkHttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
* [Stetho](http://facebook.github.io/stetho/) - A debug bridge to Chrome browser

### Quality Assurance
* [Checkstyle](http://checkstyle.sourceforge.net/), [Findbugs](http://findbugs.sourceforge.net/), [PMD](https://pmd.github.io/) and [Lint](https://developer.android.com/studio/write/lint.html) for static code analysis
* Unit test with [JUnit](http://junit.org/), [Mockito](http://mockito.org/) and [Robolectric](http://robolectric.org/) in JVM

#### Run "check" task from Gradle

In this project, run check to ensure code quality in the following order: Checkstyle -> Findbugs -> Lint -> PMD -> Unit Tests.  You should find all configuration files under config/quality/..

```
./gradlew check
```

### References
- [Introduction to MVP on Android](https://github.com/konmik/konmik.github.io/wiki/Introduction-to-Model-View-Presenter-on-Android)
- [Architecting Android… The clean way?](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)
- [Dependency Injection with Dagger 2](https://guides.codepath.com/android/Dependency-Injection-with-Dagger-2)

### Dagger2 gotcha
* [Keeping the Daggers Sharp](https://medium.com/square-corner-blog/keeping-the-daggers-sharp-%EF%B8%8F-230b3191c3f)
* [Dagger 2 on production — reducing methods count](https://medium.com/azimolabs/dagger-2-on-production-reducing-methods-count-5a13ff671e30)
* [Dagger 2. Custom scopes, Subcomponents](https://proandroiddev.com/dagger-2-part-ii-custom-scopes-component-dependencies-subcomponents-697c1fa1cfc)

### TODO
- <s>MVP</s> -> see branch [mvp](https://github.com/rascalyen/MyAPIFilms/tree/mvp)
- <s>MVVM + Data binding</s>  -> see branch [dataBinding](https://github.com/rascalyen/ApiMovies/tree/dataBinding)
- <s>RxJava/RxAndroid</s>
- Kotlin + Coroutines
