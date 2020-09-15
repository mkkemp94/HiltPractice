package com.mkemp.hiltpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.AppComponentFactory
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    @Inject
    lateinit var someClass: SomeClass
    
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        println(someClass.doAThing())
    }
}

class SomeClass
@Inject
constructor(
        private val someInterfaceImpl: SomeInterface, // Cannot do constructor injection when injecting an interface...
        private val gson: Gson // Can't inject this because I don't own it!!
)
{
    fun doAThing(): String
    {
        return "Look I got: ${someInterfaceImpl.getAThing()}"
    }
}

class SomeInterfaceImpl
@Inject
constructor() : SomeInterface
{
    override fun getAThing(): String
    {
        return "A Thing"
    }
}

interface SomeInterface
{
    fun getAThing(): String
}

@InstallIn(ApplicationComponent::class)
@Module
class MyModule
{
    @Singleton
    @Provides
    fun provideSomeInterfaceImpl(): SomeInterface
    {
        return SomeInterfaceImpl()
    }
    
    @Singleton
    @Provides
    fun provideGson(): Gson
    {
        return Gson()
    }
}