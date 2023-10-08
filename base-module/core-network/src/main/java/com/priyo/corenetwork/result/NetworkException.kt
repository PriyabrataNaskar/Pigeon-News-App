package com.priyo.corenetwork.result

class NetworkException<T : Any>(val throwable: Throwable) : NetworkResult<T>
