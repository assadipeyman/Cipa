package com.cipa.cipamerchant.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cipa.cipamerchant.R
import com.cipa.cipamerchant.data.businessData.BMarket

class MarketAdapter(
     private val markets:List<BMarket>,
     private val onItemClicked: (position: Int, market:BMarket) -> Unit) : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
          val context = parent.context
          val inflater = LayoutInflater.from(context)
          val view = inflater.inflate(R.layout.layout_market_item, parent, false)
          return MarketViewHolder(view);
     }

     override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
          val data = markets[position]
          holder.tv_shopname.text = data.merchantBDM.name

          holder.itemView.setOnClickListener {
               onItemClicked(position, markets[position])
          }
     }

     class MarketViewHolder(view: View) : RecyclerView.ViewHolder(view) {
          val tv_shopname: TextView = view.findViewById(R.id.tv_shop_name)
          val tv_shop_info1: TextView = view.findViewById(R.id.tv_shop_info1)
          val tv_shop_info2: TextView = view.findViewById(R.id.tv_shop_info2)
          val tv_shop_info3: TextView = view.findViewById(R.id.tv_shop_info3)
     }

     override fun getItemCount(): Int {
          return markets.size
     }
}