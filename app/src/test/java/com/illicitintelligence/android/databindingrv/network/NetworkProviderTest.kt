package com.illicitintelligence.android.databindingrv.network

import org.junit.Test

import org.junit.Assert.*

class NetworkProviderTest {

    @Test
    fun getPictures() {
        val pictures = NetworkProvider.getPictures().blockingFirst()
        assertNotNull(pictures)
        assertNotEquals(0, pictures.size)
    }
}