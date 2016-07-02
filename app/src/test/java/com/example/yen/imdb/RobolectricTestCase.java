package com.example.yen.imdb;

import android.os.Build;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.M, manifest = "./AndroidManifest.xml")
public class RobolectricTestCase {}