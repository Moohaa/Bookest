package com.example.books.data.Remote.DTO.Converters

import androidx.room.TypeConverter
import com.example.books.data.Remote.DTO.BuyLink

class BuyLinksConverter {

    @TypeConverter
    fun toBuyLink(BuyLinks: String?): BuyLink {

        return BuyLink("hh","ff")

    }
}