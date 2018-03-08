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

* Build android with MVP design pattern
* Use Dependency Injection ([Dagger2](http://google.github.io/dagger/)) to separate configuration (properties, imageClient, httpClient, etc.) and UI usage
* Use [Butterknife](https://github.com/JakeWharton/butterknife) for view injection in Activity/Fragment
* Use [Retrofit2](http://square.github.io/retrofit/) and [Moshi](https://github.com/square/moshi) to call RESTful API and parse returned JSON response
* [Glide](https://github.com/bumptech/glide) as image client
* Define productFlavors in gradle script
* Read properties from /res/raw resource (However, productFlavor is preferable)
* How to save/restore Fragment's state
* Use RecyclerView and CardView
* [LeakCanary](https://github.com/square/leakcanary) to find memory leak EARLY


### Quality Assurance
* [Checkstyle](http://checkstyle.sourceforge.net/), [Findbugs](http://findbugs.sourceforge.net/), [PMD](https://pmd.github.io/) and [Lint](https://developer.android.com/studio/write/lint.html) for static code analysis
* Unit test with [JUnit](http://junit.org/), [Mockito](http://mockito.org/) and [Robolectric](http://robolectric.org/) in JVM
* Unit test asynchronous callback with Mockito

#### Run "check" task from Gradle

In this project, run check to ensure code quality in the following order: Checkstyle -> Findbugs -> PMD -> Lint -> Unit Tests.  You should find all configuration files under config/quality/..

```
./gradlew check
```


### References
- [Introduction to MVP on Android](https://github.com/konmik/konmik.github.io/wiki/Introduction-to-Model-View-Presenter-on-Android)
- [Architecting Android… The clean way?](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)
- [Dependency Injection with Dagger 2](https://guides.codepath.com/android/Dependency-Injection-with-Dagger-2)


### TODO
- <s>MVVM + Data binding</s>  -> see branch [dataBinding](https://github.com/rascalyen/ApiMovies/tree/dataBinding)
- <s>RxJava/RxAndroid</s> -> Overkill for this project. There's only one call...
- Kotlin
