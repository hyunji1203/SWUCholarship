package com.example.main
//최현지
import android.os.Parcel
import android.os.Parcelable

class Exam constructor (var name: String,  var day: String, var value: Int, var typenum : Int) : Parcelable {
    /* : Parcelable에서 Alt + Enter하여 내용을 implement한다. */

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(day)
        parcel.writeInt(value)
        parcel.writeInt(typenum)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Exam> {
        override fun createFromParcel(parcel: Parcel): Exam {
            return Exam(parcel)
        }

        override fun newArray(size: Int): Array<Exam?> {
            return arrayOfNulls(size)
        }
    }
}