package com.the.classifiedsapp.activities.homeactivity.viewholders

import android.view.View
import kotlinx.android.synthetic.main.adapter_home_screen.view.*
import kotlinx.android.synthetic.main.component_price_and_description.view.*

class HomeViewHolder (view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    var ivProducts =  view.ivProducts
    var tvPrice = view.tvPrice
    var tvDescription = view.tvDescription
    var tvAddedOn = view.tvAddedOn
}