package com.example.retrofit.data.util

import com.example.retrofit.data.model.NetworkResult
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

suspend fun <T : Any> handleApi(execute: suspend () -> Response<T>) : NetworkResult<T>{
    return try {
        val response = execute()
        val body = response.body()
        if(response.isSuccessful && body != null){
            NetworkResult.Success(data = body)
        } else {
            NetworkResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException){
        NetworkResult.Error(code = e.code(), message = e.message())
    } catch(e: UnknownHostException){
        NetworkResult.Error(code = 404, message = "No known host")
    }catch (e: Throwable){
        NetworkResult.Exception(e)
    }
}