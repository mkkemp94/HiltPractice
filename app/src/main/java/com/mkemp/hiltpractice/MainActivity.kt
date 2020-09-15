package com.mkemp.hiltpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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