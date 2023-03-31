package com.nistruct.cryptocurrency.domain.use_case.get_coin

import com.nistruct.cryptocurrency.common.Resource
import com.nistruct.cryptocurrency.data.remote.dto.toCoin
import com.nistruct.cryptocurrency.data.remote.dto.toCoinDetail
import com.nistruct.cryptocurrency.domain.model.Coin
import com.nistruct.cryptocurrency.domain.model.CoinDetail
import com.nistruct.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: CoinRepository) {


    operator fun invoke(coinId : String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit((Resource.Loading()))
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))

        } catch (e: HttpException) {

            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))

        } catch (e: IOException) {
            emit(Resource.Error("Could not reach server. Check your internet connection!"))
        }
    }
}