package com.nistruct.cryptocurrency.domain.repository

import com.nistruct.cryptocurrency.data.remote.dto.CoinDetailDto
import com.nistruct.cryptocurrency.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto
}