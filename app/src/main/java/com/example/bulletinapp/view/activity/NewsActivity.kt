package com.example.bulletinapp.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.bulletinapp.R
import com.example.bulletinapp.databinding.ActivityNewsListBinding
import com.example.bulletinapp.di.application.BulletinApplication
import com.example.bulletinapp.view.fragment.NewsDetailFragment
import com.example.bulletinapp.view.fragment.NewsListFragment
import com.example.bulletinapp.viewmodel.events.OnEvent
import com.example.bulletinapp.viewmodel.events.OnNewsItemSelected
import javax.inject.Inject

class NewsActivity : BaseActivity() , FragmentManager.OnBackStackChangedListener{


    private lateinit var binding: ActivityNewsListBinding

    @Inject
    lateinit var onEventObserver: MutableLiveData<OnEvent<*>>

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BulletinApplication).appComponent.newsViewComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_list)

        supportFragmentManager.addOnBackStackChangedListener(this)

        //FragmentManager automatically saves and restores fragments over configuration changes,
        // so we only need to add the fragment if the savedInstanceState is null.
        if (savedInstanceState == null) {
            initFragment()
        }else{
            setHomeAsUpEnabled()
        }

        onEventObserver.observe(this, Observer {
            handleEvent(it)
        })
    }


    /**
     *  Handles events, it may be network error or any other event
     */
    private fun handleEvent(onEvent: OnEvent<*>) {
        when (onEvent.consumeEvent()) {
            is OnNewsItemSelected -> {
                openDetailFragment()
            }
        }
    }


    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, NewsListFragment.newInstance())
            .commit()
    }

    private fun openDetailFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, NewsDetailFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    // shows back button on action bar if more than one fragment is in back stack
    private fun setHomeAsUpEnabled(){
        val upEnabled = supportFragmentManager.backStackEntryCount > 0
        supportActionBar?.setDisplayHomeAsUpEnabled(upEnabled)
    }

    override fun onBackStackChanged() {
        setHomeAsUpEnabled()
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }
}
