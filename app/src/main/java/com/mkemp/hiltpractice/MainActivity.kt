package com.mkemp.hiltpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint // No need to declare main activity in the component. Just use this annotation.
class MainActivity : AppCompatActivity()
{
    @Inject // Field injection
    lateinit var someClass: SomeClass
    
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        println(someClass.doAThing())
        println(someClass.doSomeOtherThing())
    }
}

// Created at compile time
class SomeClass
@Inject
constructor(private val someOtherClass: SomeOtherClass) // Constructor injection
{
    fun doAThing(): String
    {
        return "Look, I did a thing!"
    }
    
    fun doSomeOtherThing(): String
    {
        return someOtherClass.doSomeOtherThing()
    }
}

class SomeOtherClass
@Inject
constructor()
{
    fun doSomeOtherThing(): String
    {
        return "Look, I did some other thing!"
    }
}