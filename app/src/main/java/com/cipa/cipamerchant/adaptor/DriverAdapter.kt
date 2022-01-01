package com.cipa.cipamerchant.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filter.FilterResults
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cipa.cipamerchant.R
import com.cipa.cipamerchant.data.ServiceData.Driver
import com.cipa.cipamerchant.data.businessData.BMarket
import com.cipa.cipamerchant.data.businessData.BSupplier
import com.cipa.cipamerchant.databinding.LayoutDriverItemBinding
import com.cipa.cipamerchant.databinding.LayoutGeneralItemBinding
import com.cipa.cipamerchant.databinding.LayoutSupplierItemBinding
import com.cipa.cipamerchant.memory.MemoryData
import com.cipa.cipamerchant.utils.StringUtils
import com.cipa.cipamerchant.utils.StringUtils.withCurrencyFormat

class DriverAdapter(
     private val drivers:List<Driver>,
     private val onItemClicked: (position: Int, driver:Driver) -> Unit
) : RecyclerView.Adapter<DriverAdapter.DriverViewHolder>() ,Filterable {
     private lateinit var context : Context
     private var filteredDrivers = drivers
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
          context = parent.context
          var binding =
               LayoutDriverItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
          return DriverViewHolder(binding)
     }

     override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
          val data = filteredDrivers[position]
          holder.binding.tvName.text = data.Name

          holder.itemView.setOnClickListener {
               onItemClicked(position, drivers[position])
          }
     }

     class DriverViewHolder(var binding: LayoutDriverItemBinding) : RecyclerView.ViewHolder(binding.root) {
     }

     override fun getItemCount(): Int {
          return filteredDrivers.size
     }

     override fun getFilter(): Filter {
          return object : Filter() {
               override fun publishResults(constraint: CharSequence, results: FilterResults) {
                    filteredDrivers = results.values as List<Driver>
                    notifyDataSetChanged()
               }

               override fun performFiltering(constraint: CharSequence): FilterResults {
                    var filteredResults: List<Driver?>? = null
                    if (constraint.length == 0) {
                         filteredResults = drivers
                    } else {
                         filteredResults = getFilteredResults(constraint.toString())
                    }
                    val results = FilterResults()
                    results.values = filteredResults
                    return results
               }
          }
     }
     private fun getFilteredResults(constraint: String?): List<Driver>? {
          val results: MutableList<Driver> = ArrayList()
          for (item in drivers) {
               if (item.Name.contains(constraint!!, true)) {
                    results.add(item)
               }
          }
          return results
     }
}

