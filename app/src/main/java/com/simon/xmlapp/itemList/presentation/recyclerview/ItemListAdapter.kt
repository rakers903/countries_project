package com.simon.xmlapp.itemList.presentation.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simon.xmlapp.R
import com.simon.xmlapp.databinding.CountryItemBinding
import com.simon.xmlapp.itemList.domain.model.CountryModel

class ItemListAdapter(
    private var data: List<CountryModel>
): RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: CountryItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        lateinit var name: TextView
        lateinit var region: TextView
        lateinit var code: TextView
        lateinit var capital: TextView
        fun bind(countryItem: CountryModel) {
            binding.apply {
                name = tvName
                region = tvRegion
                code = tvCode
                capital = tvCapital
            }
            name.text = countryItem.name
            region.text = countryItem.region
            code.text = countryItem.code
            capital.text = countryItem.capital
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CountryItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCountry = data[position]
        holder.bind(currentCountry)
    }
    fun updateData(newList: List<CountryModel>) {
        data = newList
        notifyDataSetChanged()
    }
}