package com.the.classifiedsapp.activities.homeactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.the.classifiedsapp.R
import com.the.classifiedsapp.activities.homeactivity.adapters.HomeRecyclerAdapter
import com.the.classifiedsapp.activities.homeactivity.dto.ClassifiedDTO
import com.the.classifiedsapp.activities.homeactivity.dto.ClassifiedData
import com.the.classifiedsapp.activities.homeactivity.interfaces.OnItemTappedCallback
import com.the.classifiedsapp.activities.homedetailactivity.HomeDetailActivity
import com.the.classifiedsapp.utils.AppNetworkState
import com.the.classifiedsapp.utils.GlobalConstants

class HomeActivity : AppCompatActivity(), OnItemTappedCallback, View.OnClickListener {

    private lateinit var  homeViewModel : HomeViewModel
    private var rvHomeList : RecyclerView ?= null
    private var homeRecyclerAdapter : HomeRecyclerAdapter ?= null
    private var linearLayoutManager : LinearLayoutManager ?= null
    private var progressBar : ProgressBar ?= null
    private var swipeRefreshLayout: SwipeRefreshLayout?= null

    /******
     * Refresh Layout
     ****/
    private var btnRefresh: Button?=null
    private var layoutNoInternetConnection: ConstraintLayout?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        initViews()
        attachListenersToViews()
        onSwipeRefresh()
        initObserver()
        callClassifiedListService(false)

    }

    private fun attachListenersToViews() {
        btnRefresh?.setOnClickListener(this)
    }

    private fun initViews() {
        swipeRefreshLayout = findViewById(R.id.swipeRefresh_home)
        layoutNoInternetConnection = findViewById(R.id.layoutNoInternetConnection)
        btnRefresh = findViewById(R.id.btnRefresh)
        rvHomeList = findViewById(R.id.rvHomeList)
        progressBar = findViewById(R.id.progressBar)

    }

    private fun initObserver() {

        homeViewModel.observeClassifiedFeed().observe(this, Observer {

            if(it.isSuccess == true){
                passDataToRecyclerView(it.classifiedDTO)
            }

            else{
                Toast.makeText(this, getString(R.string.error_classified_feed), Toast.LENGTH_LONG).show()
            }

            progressBar?.visibility = View.GONE
            swipeRefreshLayout?.isRefreshing = false
        })

    }

    private fun passDataToRecyclerView(classifiedDTO: ClassifiedDTO?) {

        val dataList = classifiedDTO?.results
        val arrayList = ArrayList<ClassifiedData>(dataList)

        homeRecyclerAdapter = HomeRecyclerAdapter(this, this)
        homeRecyclerAdapter?.setData(arrayList)
        linearLayoutManager = LinearLayoutManager(this)
        rvHomeList?.layoutManager = linearLayoutManager
        rvHomeList?.adapter = homeRecyclerAdapter
    }

    override fun onClassifiedTapped(classifiedData: ClassifiedData?) {

        val intent = Intent(this, HomeDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(GlobalConstants.INTENT_CLASSIFIED_DATA, classifiedData)
        intent.putExtra(GlobalConstants.INTENT_BUNDLE, bundle)
        startActivity(intent)
    }


    private fun onSwipeRefresh() {
        swipeRefreshLayout?.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            callClassifiedListService(true)
        })
    }

    override fun onClick(view: View?) {

        when(view?.id){
            R.id.btnRefresh->{
                callClassifiedListService(false)
            }

        }

    }

    private fun callClassifiedListService(isRefreshRequired:Boolean) {

        if(AppNetworkState.checkConnection(applicationContext)){
            homeViewModel.callClassifiedFeed(isRefreshRequired)
            layoutNoInternetConnection?.visibility = View.GONE
        }else{
            layoutNoInternetConnection?.visibility = View.VISIBLE
        }
    }


}