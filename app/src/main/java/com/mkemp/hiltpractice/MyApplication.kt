package com.mkemp.hiltpractice

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // Necessary to use Hilt ... creates AppComponent
class MyApplication : Application()
{

}