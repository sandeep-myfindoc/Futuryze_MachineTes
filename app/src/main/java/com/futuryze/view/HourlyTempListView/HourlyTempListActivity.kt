package com.futuryze.view.HourlyTempListView

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.futuryze.R
import com.futuryze.broadcastreceiver.ConnectivityReceiver
import com.futuryze.databinding.ActivityMainBinding
import com.futuryze.util.CommonUtility
import com.futuryze.view.HourlyTempListView.SuperActivity
import com.futuryze.viewmodel.HourlyTempListViewModel
import com.google.android.material.snackbar.Snackbar



class HourlyTempListActivity : SuperActivity(){
    private var binding: ActivityMainBinding? = null
    private var viewModel: HourlyTempListViewModel? = null
    private var adapter: HourlyTempListAdapter? = null
    private var receiver: ConnectivityReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(HourlyTempListViewModel::class.java)
        binding?.viewModel = viewModel
        receiver = ConnectivityReceiver()
        updateToolbarWithoutBackButton(resources.getString(R.string.title_top_rated_movies))
        initRecyclerView()
        binding!!.swipeLayout.setOnRefreshListener(onRefreshListener)
        if (!CommonUtility(this).checkInternetConnection()) {
            val snackbar = Snackbar
                    .make(binding!!.coordinatorLayout, resources.getString(R.string.msg_offline), Snackbar.LENGTH_LONG)
            snackbar.show()
        }
        binding?.swipeLayout?.isRefreshing = true
        fetchTopRatedMoviesList()
    }




    private fun initRecyclerView() {
        adapter = HourlyTempListAdapter(this)
        val layoutManager = LinearLayoutManager(this)
        binding?.rvPlayers?.layoutManager = layoutManager
        binding?.rvPlayers?.adapter = adapter
    }

    //add observer
    private fun fetchTopRatedMoviesList() {
        viewModel?.topRatedMoviePagedList?.observe(this, Observer { players ->
            binding?.swipeLayout?.isRefreshing = false
            adapter?.submitList(players)
            if (binding!!.swipeLayout.isRefreshing) {
                binding!!.swipeLayout.isRefreshing = false
            }
        })
    }



    var onRefreshListener = OnRefreshListener {
        binding?.swipeLayout?.isRefreshing=false
    }
}