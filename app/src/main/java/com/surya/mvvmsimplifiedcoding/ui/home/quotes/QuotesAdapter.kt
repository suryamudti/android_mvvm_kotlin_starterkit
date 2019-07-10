package com.surya.mvvmsimplifiedcoding.ui.home.quotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surya.mvvmsimplifiedcoding.data.db.entities.Quote
import com.surya.mvvmsimplifiedcoding.databinding.QuotesItemBinding

class QuotesAdapter(
    private val data: List<Quote>

) : RecyclerView.Adapter<QuotesAdapter.ItemHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(QuotesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindItem(data.get(position))
    }


    class ItemHolder(private val quotesItemBinding : QuotesItemBinding ):
        RecyclerView.ViewHolder(quotesItemBinding.root){


        fun bindItem(quote: Quote){

            quotesItemBinding.apply {
                this.quote = quote

                executePendingBindings()
            }

        }




    }


}