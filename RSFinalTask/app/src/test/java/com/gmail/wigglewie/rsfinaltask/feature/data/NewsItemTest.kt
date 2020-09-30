package com.gmail.wigglewie.rsfinaltask.test

//import io.reactivex.rxjava3.core.Single

//import org.junit.Test

import android.os.Bundle
import android.os.Parcel
import com.gmail.wigglewie.rsfinaltask.feature.data.NewsItem
import me.angrybyte.util.MockParcel
import org.junit.Assert.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

class NewsItemTestSpec: Spek({
    describe("Parcel") {
        it("should write data to parcel") {
            val origItem1 = NewsItem("http://www.1.com", "Item Title 1", "Item Description 1", "http://image.jpg", "2020-01-01")
            val origItem2 = NewsItem("http://www.2.com", "Item Title 2", "Item Description 2", "http://image.jpg", "2020-01-01")

            val parcel1 = MockParcel.obtain()
            val parcel2 = MockParcel.obtain()

            origItem1.writeToParcel(parcel1, 0)
            origItem2.writeToParcel(parcel2, 0)

            parcel1.setDataPosition(0)
            parcel2.setDataPosition(0)

            // Reconstruct object from parcel and asserts:
            val newItem1: NewsItem = NewsItem.CREATOR.createFromParcel(parcel1)
            val newItem2: NewsItem = NewsItem.CREATOR.createFromParcel(parcel2)

            assertEquals(origItem1, newItem1)
            assertEquals(origItem2, newItem2)
            assertNotEquals(newItem1, newItem2)
            assertNotEquals(newItem1, newItem2)
        }
    }
})
