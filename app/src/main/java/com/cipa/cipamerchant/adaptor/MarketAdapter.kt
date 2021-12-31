package com.cipa.cipamerchant.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cipa.cipamerchant.R
import com.cipa.cipamerchant.data.businessData.BMarket
import com.cipa.cipamerchant.databinding.ActivityLoginBinding
import com.cipa.cipamerchant.databinding.LayoutGeneralItem1Binding
import com.cipa.cipamerchant.databinding.LayoutGeneralItemBinding
import com.cipa.cipamerchant.memory.MemoryData
import com.cipa.cipamerchant.utils.StringUtils
import com.cipa.cipamerchant.utils.StringUtils.withCurrencyFormat
import com.cipa.cipamerchant.utils.StringUtils.withPersianDigits

class MarketAdapter(
     private val markets:List<BMarket>,
     private val onItemClicked: (position: Int, market:BMarket) -> Unit
) : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {
     private lateinit var context : Context
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
          context = parent.context

          var binding =LayoutGeneralItem1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
          return MarketViewHolder(binding)
     }

     override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
          val data = markets[position]
          holder.binding.tvName.text = data.merchantBDM.name
          holder.binding.ivImage.setImageResource(R.drawable.ic_store_96)
          val debt: Long = -1 * MemoryData.getSumDebtOfMarket(data.merchantBDM.id)
          holder.binding.tvDebt.text = debt.withCurrencyFormat
          if (debt < 0.0) {
               holder.binding.tvDebt.setTextColor(
                    ContextCompat.getColor(
                         context,
                         R.color.red_price
                    )
               )
          } else {
               holder.binding.tvDebt.setTextColor(
                    ContextCompat.getColor(
                         context,
                         R.color.green_price
                    )
               )
          }
          holder.itemView.setOnClickListener {
               onItemClicked(position, markets[position])
          }
     }

     class MarketViewHolder(var binding:    LayoutGeneralItem1Binding) : RecyclerView.ViewHolder(binding.root) {
     }

     override fun getItemCount(): Int {
          return markets.size
     }
}