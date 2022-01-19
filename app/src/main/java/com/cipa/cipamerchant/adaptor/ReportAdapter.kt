package com.cipa.cipamerchant.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cipa.cipamerchant.R
import com.cipa.cipamerchant.data.businessData.BMarket
import com.cipa.cipamerchant.databinding.LayoutDriverItemBinding
import com.cipa.cipamerchant.databinding.LayoutGeneralItem1Binding
import com.cipa.cipamerchant.databinding.LayoutReportItemBinding
import com.cipa.cipamerchant.memory.MemoryData
import com.cipa.cipamerchant.utils.StringUtils.withCurrencyFormat

class ReportAdapter(
     private val reports:List<String>,
     private val onItemClicked: (position: Int, name:String) -> Unit
) : RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {
     private lateinit var context : Context
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
          context = parent.context

          var binding =
               LayoutReportItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
          return ReportViewHolder(binding)
     }

     override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
          val data = reports[position]
          holder.binding.tvName.text = data
          holder.itemView.setOnClickListener {
               onItemClicked(position, reports[position])
          }
     }

     class ReportViewHolder(var binding:    LayoutReportItemBinding) : RecyclerView.ViewHolder(binding.root) {
     }

     override fun getItemCount(): Int {
          return reports.size
     }
}