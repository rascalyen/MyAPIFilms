# MyAPIFilms IMDB

Showcase common android implementation with [MyAPIFilms](http://api.myapifilms.com/index.do) IMDB API. This app shows “IN THEATERS” movie list on main page, and shows clicked movie details on next page.
<!--
<img height="380" src="https://github.com/rascalyen/ApiMovies/blob/master/screenshot/00.png" />
<img height="380" src="https://github.com/rascalyen/ApiMovies/blob/master/screenshot/01.png" />
<img height="380" src="https://github.com/rascalyen/ApiMovies/blob/master/screenshot/02.png" />
<br>
-->
[Click here for demo video](http://tinyurl.com/zcpotsl)


### Technical Features
From this project you should find useful examples like,

* Build android with <b>MVVM design pattern + [Data Binding Library](https://developer.android.com/topic/libraries/data-binding/index.html)</b>
* Use Dependency Injection ([Dagger2](http://google.github.io/dagger/)) to separate configuration (properties, imageClient, httpClient, etc.) and UI usage. In brief, configuration in application scope and UI usage in activity scope.
* Work [DataBindingComponent](https://developer.android.com/reference/android/databinding/DataBindingComponent.html) with Dagger2 to inject none-static [BindingAdapter](https://developer.android.com/reference/android/databinding/BindingAdapter.html) instances
* <s>Use [Butterknife](https://github.com/JakeWharton/butterknife) for view injection in Activity/Fragment</s> -> replace by data binding
* Use [Retrofit2](http://square.github.io/retrofit/) and [Jackson](https://github.com/FasterXML/jackson) to call RESTful API and parse returned JSON response
* [Picasso](http://square.github.io/picasso/) as image client
* [LeakCanary](https://github.com/square/leakcanary) to find memory leak EARLY
* Read properties from /res/raw resource
* How to save/restore Fragment's state
* Use RecyclerView and CardView


### References
- [Marshmallow Brings Data Bindings to Android](https://realm.io/news/data-binding-android-boyar-mount/)
- [Dependency Injection with Dagger 2](https://guides.codepath.com/android/Dependency-Injection-with-Dagger-2)
- ivacf's [Archi repository](https://github.com/ivacf/archi)


### TODO
- RxJava/RxAndroid
- Kotlin
