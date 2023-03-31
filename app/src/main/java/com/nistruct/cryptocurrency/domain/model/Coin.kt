package com.nistruct.cryptocurrency.domain.model

import com.nistruct.cryptocurrency.data.remote.dto.CoinDto

data class Coin(
    val id: String,
    val is_active: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)


