package com.vezeeta.vezeetatask.domain.model

import android.os.Parcel
import android.os.Parcelable

data class Offer(
    val mainImage: String?,
    val offerKey: String?,
    val offerName: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(mainImage)
        parcel.writeString(offerKey)
        parcel.writeString(offerName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Offer> {
        override fun createFromParcel(parcel: Parcel): Offer {
            return Offer(parcel)
        }

        override fun newArray(size: Int): Array<Offer?> {
            return arrayOfNulls(size)
        }
    }
}