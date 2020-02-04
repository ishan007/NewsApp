package com.example.bulletinapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bulletinapp.R
import com.example.bulletinapp.databinding.FragmentNewsListBinding
import com.example.bulletinapp.di.application.BulletinApplication
import com.example.bulletinapp.domain.entities.News
import com.example.bulletinapp.util.NetworkUtil
import com.example.bulletinapp.view.activity.NewsActivity
import com.example.bulletinapp.view.adapter.NewsListAdapter
import com.example.bulletinapp.viewmodel.NewsActivitySharedViewModel
import com.example.bulletinapp.viewmodel.NewsListViewModel
import com.example.bulletinapp.viewmodel.events.NewsObserver
import com.example.bulletinapp.viewmodel.events.OnEvent
import com.example.bulletinapp.viewmodel.events.OnNewsItemSelected
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Simple fragment to show the list of news
 */
class NewsListFragment : Fragment() {

    private lateinit var binding: FragmentNewsListBinding

    private lateinit var newsListViewModel: NewsListViewModel

    private lateinit var newsActivitySharedViewModel: NewsActivitySharedViewModel

    private val newsList: ArrayList<News> = arrayListOf()

    @Inject
    lateinit var disposable: CompositeDisposable

    @Inject
    lateinit var onEventObserver: MutableLiveData<OnEvent<*>>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var networkUtil: NetworkUtil


    private val adapter: NewsListAdapter by lazy {
        NewsListAdapter(newsList, NewsListAdapter.NewsSelectionListener {
            newsActivitySharedViewModel.onNewsSelection(it)
            onEventObserver.value = OnEvent(OnNewsItemSelected(NewsActivity::class.java))
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as BulletinApplication).appComponent
            .newsViewComponent().create().inject(this)
        super.onCreate(savedInstanceState)

        newsListViewModel =
            ViewModelProvider(this, viewModelFactory).get(NewsListViewModel::class.java)

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
            R.layout.fragment_news_list,
            container, false
        )

        initNewsListAdapter()
        initSwipeRefresh()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }


    private fun loadData(){
        if(networkUtil.isNetworkConnected()){
            newsListViewModel.newsList.observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : NewsObserver<List<News>>(onEventObserver, disposable){

                override fun onComplete() {
                    super.onComplete()
                    binding.swipeRefreshView.isRefreshing = false
                }
                override fun onNext(t: List<News>) {
                    super.onNext(t)
                    newsList.clear()
                    newsList.addAll(t)
                    adapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    binding.swipeRefreshView.isRefreshing = false
                    showOnLoadError()
                }
            })
        }else{
            showNoInternetConnectionError()
        }
    }

    override fun onStop() {
        disposable.clear()
        super.onStop()
    }


    private fun initNewsListAdapter() {
        binding.newsRv.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        binding.newsRv.layoutManager = layoutManager
    }


    private fun showNoInternetConnectionError(){
        Snackbar.make(
            binding.coordinatorLayout,
            getString(R.string.no_internet_error),
            Snackbar.LENGTH_LONG
        ).show()
    }


    private fun showOnLoadError(){
        Snackbar.make(
            binding.coordinatorLayout,
            getString(R.string.load_error),
            Snackbar.LENGTH_SHORT
        ).show()
    }


    // allows user to refresh delivery list on swipe gesture
    private fun initSwipeRefresh() {
        binding.swipeRefreshView.setOnRefreshListener {
            loadData()
        }
    }



    companion object {
        /**
         * Factory method to create a new instance of this fragment
         */
        @JvmStatic
        fun newInstance() = NewsListFragment()
    }
}
