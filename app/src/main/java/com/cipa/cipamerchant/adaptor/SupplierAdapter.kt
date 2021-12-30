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
import com.cipa.cipamerchant.data.businessData.BMarket
import com.cipa.cipamerchant.data.businessData.BSupplier
import com.cipa.cipamerchant.databinding.LayoutGeneralItemBinding
import com.cipa.cipamerchant.databinding.LayoutSupplierItemBinding
import com.cipa.cipamerchant.memory.MemoryData
import com.cipa.cipamerchant.utils.StringUtils
import com.cipa.cipamerchant.utils.StringUtils.withCurrencyFormat

class SupplierAdapter(
     private val suppliers:List<BSupplier>,
     private val onItemClicked: (position: Int, supplier:BSupplier) -> Unit
) : RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder>() ,Filterable {
     private lateinit var context : Context
     private var filteredSuppliers = suppliers
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierViewHolder {
          context = parent.context
          var binding =
               LayoutGeneralItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
          return SupplierViewHolder(binding)
     }

     override fun onBindViewHolder(holder: SupplierViewHolder, position: Int) {
          val data = filteredSuppliers[position]
          holder.binding.tvName.text = data.name
          holder.binding.ivImage.setImageResource(R.drawable.ic_supplier)
          val debt: Double = -1 * data.credit!!.debt
          holder.binding.tvDebt.text = debt.withCurrencyFormat
          if (debt < 0.0) {
               holder.binding.tvDebt.setTextColor(
                    ContextCompat.getColor(
                         context,
                         R.color.red_price
                    )
               )
               holder.binding.tvDebtTitle.text = "بدهکار"
          } else {
               holder.binding.tvDebt.setTextColor(
                    ContextCompat.getColor(
                         context,
                         R.color.green_price
                    )
               )
               holder.binding.tvDebtTitle.text = "بستانکار"
          }
          holder.itemView.setOnClickListener {
               onItemClicked(position, suppliers[position])
          }
     }

     class SupplierViewHolder(var binding: LayoutGeneralItemBinding) : RecyclerView.ViewHolder(binding.root) {
     }

     override fun getItemCount(): Int {
          return filteredSuppliers.size
     }

     override fun getFilter(): Filter {
          return object : Filter() {
               override fun publishResults(constraint: CharSequence, results: FilterResults) {
                    filteredSuppliers = results.values as List<BSupplier>
                    notifyDataSetChanged()
               }

               override fun performFiltering(constraint: CharSequence): FilterResults {
                    var filteredResults: List<BSupplier?>? = null
                    if (constraint.length == 0) {
                         filteredResults = suppliers
                    } else {
                         filteredResults = getFilteredResults(constraint.toString())
                    }
                    val results = FilterResults()
                    results.values = filteredResults
                    return results
               }
          }
     }
     private fun getFilteredResults(constraint: String?): List<BSupplier>? {
          val results: MutableList<BSupplier> = ArrayList()
          for (item in suppliers) {
               if (item.name.contains(constraint!!, true)) {
                    results.add(item)
               }
          }
          return results
     }
}

