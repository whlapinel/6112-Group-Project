package com.example.a6112_final_project_kotlin

import android.os.Parcel
import android.os.Parcelable

class Item(
    var name: String,
    var description: String,
    var unitQuantity: Int,
    var currQuantity: Int,
    var required: Int,
    var lowStockAlarm: Int,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(unitQuantity)
        parcel.writeInt(currQuantity)
        parcel.writeInt(required)
        parcel.writeInt(lowStockAlarm)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}