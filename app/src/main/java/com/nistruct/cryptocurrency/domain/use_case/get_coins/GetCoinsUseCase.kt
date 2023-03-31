package com.nistruct.cryptocurrency.domain.use_case.get_coins

import com.nistruct.cryptocurrency.common.Resource
import com.nistruct.cryptocurrency.data.remote.dto.toCoin
import com.nistruct.cryptocurrency.domain.model.Coin
import com.nistruct.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {


    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit((Resource.Loading()))
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))

        } catch (e: HttpException) {

            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))

        } catch (e: IOException) {
            emit(Resource.Error("Could not reach server. Check your internet connection!"))
        }
    }
}