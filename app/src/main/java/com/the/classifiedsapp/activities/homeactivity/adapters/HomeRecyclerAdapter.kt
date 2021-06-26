package com.the.classifiedsapp.activities.homeactivity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.the.classifiedsapp.R
import com.the.classifiedsapp.activities.homeactivity.dto.ClassifiedData
import com.the.classifiedsapp.activities.homeactivity.interfaces.OnItemTappedCallback
import com.the.classifiedsapp.activities.homeactivity.viewholders.BlankViewHolder
import com.the.classifiedsapp.activities.homeactivity.viewholders.HomeViewHolder


class HomeRecyclerAdapter(val context: Context, onItemTappedCallback: OnItemTappedCallback) :
    androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    private var mContext: Context? = null
    private var onItemTappedCallback: OnItemTappedCallback ?= null
    private var dataList: ArrayList<ClassifiedData>? = null
    private val NORMAL_ITEM = 1
    private val BLANK_ITEM = 2

    init {
        mContext = context
        this.onItemTappedCallback = onItemTappedCallback

    }

    fun setData(dataList: ArrayList<ClassifiedData>?) {
        this.dataList = dataList
    }


    override fun getItemViewType(position: Int): Int {
        return NORMAL_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val mInflater = LayoutInflater.from(context)

        when (viewType) {
            NORMAL_ITEM -> {
                view = mInflater.inflate(R.layout.adapter_home_screen, parent, false)
                return HomeViewHolder(view)
            }

            else -> {
                view = mInflater.inflate(R.layout.blank_layout, parent, false)
                return BlankViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindNormalViewHolder(holder as HomeViewHolder, position)
    }

    private fun bindNormalViewHolder(viewHolder: HomeViewHolder, position: Int) {

        val data = dataList?.get(position)

        viewHolder.tvPrice.text = data?.price
        viewHolder.tvDescription.text = data?.name
        viewHolder.tvAddedOn.text = data?.parsedDate

        if(dataList?.get(position)?.imageUrlsThumbnails != null &&
            dataList?.get(position)?.imageUrlsThumbnails!!.size>=1)

            Glide.with(mContext!!)
                .load(dataList?.get(position)?.imageUrlsThumbnails?.get(0))
                .into(viewHolder.ivProducts)


        viewHolder.itemView.setOnClickListener {
            onItemTappedCallback?.onClassifiedTapped(data)
        }

    }


}