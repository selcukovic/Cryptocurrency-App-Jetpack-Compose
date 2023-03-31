package com.nistruct.cryptocurrency.presentation.coin_detail

import com.nistruct.cryptocurrency.domain.model.Coin
import com.nistruct.cryptocurrency.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading : Boolean = false,
    val coins : CoinDetail? = null,
    val error : String = ""
)
