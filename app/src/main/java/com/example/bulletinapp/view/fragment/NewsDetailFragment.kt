package com.example.bulletinapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bulletinapp.R
import com.example.bulletinapp.databinding.FragmentNewsDetailBinding
import com.example.bulletinapp.di.application.BulletinApplication
import com.example.bulletinapp.viewmodel.NewsActivitySharedViewModel
import javax.inject.Inject

class NewsDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentNewsDetailBinding

    private lateinit var newsActivitySharedViewModel: NewsActivitySharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as BulletinApplication).appComponent
            .newsViewComponent().create().inject(this)
        super.onCreate(savedInstanceState)

        //{@link NewsActivitySharedViewModel}
        newsActivitySharedViewModel = ViewModelProvider(activity!!, viewModelFactory)
            .get(NewsActivitySharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_news_detail,
            container, false
        )

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.news = newsActivitySharedViewModel.selectedNews.value
    }


    companion object {

        /**
         * Use this factory method to create a new instance of this fragment
         */
        @JvmStatic
        fun newInstance() =  NewsDetailFragment()
    }
}
