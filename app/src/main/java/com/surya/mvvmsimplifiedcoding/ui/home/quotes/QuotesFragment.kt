package com.surya.mvvmsimplifiedcoding.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.surya.mvvmsimplifiedcoding.R
import com.surya.mvvmsimplifiedcoding.data.db.entities.Quote
import com.surya.mvvmsimplifiedcoding.util.Coroutines
import com.surya.mvvmsimplifiedcoding.util.toast
import com.surya.mvvmsimplifiedcoding.util.verticalListStyle
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(),KodeinAware {

    override val kodein by kodein()
    private  val factory: QuotesViewModelFactory by instance()
    private lateinit var viewModel : QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(QuotesViewModel::class.java)

        Coroutines.main {
            val quotes = viewModel.quotes.await()


            quotes.observe(this, Observer {
                setupNewsList(it)

            })
        }
    }

    private fun setupNewsList(data: List<Quote>) {
        rec_quote.apply {
            verticalListStyle()
            adapter = QuotesAdapter(data)
        }
    }

}
