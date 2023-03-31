package com.nistruct.cryptocurrency.data.repository

import com.nistruct.cryptocurrency.data.remote.CoinPaprikaApi
import com.nistruct.cryptocurrency.data.remote.dto.CoinDetailDto
import com.nistruct.cryptocurrency.data.remote.dto.CoinDto
import com.nistruct.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api : CoinPaprikaApi) : CoinRepository{
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
      return api.getCoinById(coinId)
    }

}