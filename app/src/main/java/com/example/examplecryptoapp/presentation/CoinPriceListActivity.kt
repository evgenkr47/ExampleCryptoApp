package com.example.examplecryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.examplecryptoapp.R
import com.example.examplecryptoapp.databinding.ActivityCoinPrceListBinding
import com.example.examplecryptoapp.domain.CoinInfo
import com.example.examplecryptoapp.presentation.adapter.CoinInfoAdapter

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private val binding by lazy {
        ActivityCoinPrceListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                if(isOnePaneMode()){
                    launchDetailActivity(coinPriceInfo.fromSymbol)
                }
                else {
                    launchDetailFragment(coinPriceInfo.fromSymbol)
                }
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun isOnePaneMode() = binding.fragmentContainer == null

    private fun launchDetailActivity(fromSymbol: String){
        val intent = CoinDetailActivity.newIntent(
            this@CoinPriceListActivity,
                fromSymbol
        )
        startActivity(intent)
    }

    private fun launchDetailFragment(fromSymbol: String){
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }
}
