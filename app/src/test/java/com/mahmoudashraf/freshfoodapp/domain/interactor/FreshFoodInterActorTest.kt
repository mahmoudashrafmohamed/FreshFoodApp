package com.mahmoudashraf.freshfoodapp.domain.interactor

import com.mahmoudashraf.freshfoodapp.data.entities.BuyersResponse
import com.mahmoudashraf.freshfoodapp.data.entities.ProductsResponse
import com.mahmoudashraf.freshfoodapp.domain.datasource.remote.FreshFoodRemoteDataSource
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.Mockito
import java.net.UnknownHostException


class FreshFoodInterActorTest {

    @Test
    fun `when call getFreshProducts() then return Single of ListOf product`() {
        // arrange
        val productsResponse = ProductsResponse()
        val remoteDataSource = Mockito.mock(FreshFoodRemoteDataSource::class.java)
        Mockito.`when`(remoteDataSource.getFreshProducts())
            .thenReturn(Single.just(productsResponse))
        val freshFoodInterActor = FreshFoodInterActor(remoteDataSource)
        // act
        val resultObserver = freshFoodInterActor.getFreshProducts().test()
        // assert
        resultObserver.assertValue(productsResponse)
        resultObserver.dispose()
    }

    @Test
    fun `when call getFreshProducts() then return Error`() {
        // arrange
        val exception = UnknownHostException()
        val remoteDataSource = Mockito.mock(FreshFoodRemoteDataSource::class.java)
        Mockito.`when`(remoteDataSource.getFreshProducts()).thenReturn(Single.error(exception))
        val freshFoodInterActor = FreshFoodInterActor(remoteDataSource)
        // act
        val resultObserver = freshFoodInterActor.getFreshProducts().test()
        // assert
        resultObserver.assertError(exception)
        resultObserver.dispose()
    }

    @Test
    fun `when call getBuyers() then return Single of ListOf buyers`() {
        // arrange
        val buyersResponse = BuyersResponse()
        val remoteDataSource = Mockito.mock(FreshFoodRemoteDataSource::class.java)
        Mockito.`when`(remoteDataSource.getBuyers())
            .thenReturn(Single.just(buyersResponse))
        val freshFoodInterActor = FreshFoodInterActor(remoteDataSource)
        // act
        val resultObserver = freshFoodInterActor.getBuyers().test()
        // assert
        resultObserver.assertValue(buyersResponse)
        resultObserver.dispose()
    }

    @Test
    fun `when call getBuyers() then return Error`() {
        // arrange
        val exception = UnknownHostException()
        val remoteDataSource = Mockito.mock(FreshFoodRemoteDataSource::class.java)
        Mockito.`when`(remoteDataSource.getBuyers()).thenReturn(Single.error(exception))
        val freshFoodInterActor = FreshFoodInterActor(remoteDataSource)
        // act
        val resultObserver = freshFoodInterActor.getBuyers().test()
        // assert
        resultObserver.assertError(exception)
        resultObserver.dispose()
    }

}