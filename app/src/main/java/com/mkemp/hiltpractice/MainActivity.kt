package com.mkemp.hiltpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

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
    }
}

@AndroidEntryPoint
class MyFragment: Fragment()
{
    @Inject
    lateinit var someClass: SomeClass
}

// Created at compile time
@ActivityScoped // @Singleton will also work but @FragmentScope will NOT
class SomeClass
@Inject
constructor()
{
    fun doAThing(): String
    {
        return "Look, I did a thing!"
    }
}
