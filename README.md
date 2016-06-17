# MyAPIFilms IMDB

Showcase common android implementation with [MyAPIFilms](http://api.myapifilms.com/index.do) IMDB API
<!--
<img height="380" src="https://github.com/rascalyen/ApiMovies/blob/master/screenshot/00.png" />
<img height="380" src="https://github.com/rascalyen/ApiMovies/blob/master/screenshot/01.png" />
<img height="380" src="https://github.com/rascalyen/ApiMovies/blob/master/screenshot/02.png" />
<br>
-->
[Click here for demo video](http://tinyurl.com/zcpotsl)


### Technical Features
From this project you should find useful examples like,

* Build android with MVP design pattern
* Use Dependency Injection ([Dagger2](http://google.github.io/dagger/)) to separate configuration (properties, imageClient, httpClient, etc.) and UI usage. In brief, configuration in application scope and UI usage in activity scope.
* Use [Butterknife](https://github.com/JakeWharton/butterknife) for view injection in Activity/Fragment.
* Use [Retrofit2](http://square.github.io/retrofit/) and [Jackson](https://github.com/FasterXML/jackson) to call RESTful API and parse returned JSON response
* [Picasso](http://square.github.io/picasso/) as image client
* [LeakCanary](https://github.com/square/leakcanary) to find memory leak EARLY.
* Read properties from /res/raw resource
* How to save/restore Fragment's state
* Use RecyclerView and CardView


### References
- [Introduction to MVP on Android](https://github.com/konmik/konmik.github.io/wiki/Introduction-to-Model-View-Presenter-on-Android)
- [Architecting Android… The clean way?](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)
- [Dependency Injection with Dagger 2](https://guides.codepath.com/android/Dependency-Injection-with-Dagger-2)


### TODO
- MVVM + Data binding
- RxJava/RxAndroid
- Kotlin
