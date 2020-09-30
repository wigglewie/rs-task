package me.angrybyte.util

import android.os.Parcel
//import com.nhaarman.mockito_kotlin.mock
//import com.nhaarman.mockito_kotlin.whenever

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever

import org.mockito.ArgumentMatchers.*
import org.mockito.invocation.InvocationOnMock

/**
 * Wraps around the [Parcel] and stores the values in-memory.
 */
class MockParcel {

    companion object {
        @JvmStatic
        fun obtain(): Parcel {
            return MockParcel().mockedParcel
        }
    }

    private var position = 0
    private var store = mutableListOf<Any>()
    private var mockedParcel = mock<Parcel>()

    init {
        setupWrites()
        setupReads()
        setupOthers()
    }

    // uncomment when needed for the first time
    private fun setupWrites() {
        val answer = { i: InvocationOnMock -> with(store) { add(i.arguments[0]); get(lastIndex) } }
        whenever(mockedParcel.writeByte(anyByte())).thenAnswer(answer)
        whenever(mockedParcel.writeInt(anyInt())).thenAnswer(answer)
        whenever(mockedParcel.writeString(anyString())).thenAnswer(answer)
        // whenever(mockedParcel.writeLong(anyLong())).thenAnswer(answer)
        // whenever(mockedParcel.writeFloat(anyFloat())).thenAnswer(answer)
        // whenever(mockedParcel.writeDouble(anyDouble())).thenAnswer(answer)
    }

    // uncomment when needed for the first time
    private fun setupReads() {
        val answer = { _: InvocationOnMock -> store[position++] }
        whenever(mockedParcel.readByte()).thenAnswer(answer)
        whenever(mockedParcel.readInt()).thenAnswer(answer)
        whenever(mockedParcel.readString()).thenAnswer(answer)
        // whenever(mockedParcel.readLong()).thenAnswer(answer)
        // whenever(mockedParcel.readFloat()).thenAnswer(answer)
        // whenever(mockedParcel.readDouble()).thenAnswer(answer)
    }

    private fun setupOthers() {
        val answer = { i: InvocationOnMock -> position = i.arguments[0] as Int; null }
        whenever(mockedParcel.setDataPosition(anyInt())).thenAnswer(answer)
    }

}
