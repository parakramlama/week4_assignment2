package com.parakram.week4_assignment2.model

import android.os.Parcel
import android.os.Parcelable

data class Student( val studentID: String?, val studentName: String?,
                    val androidMarks: Int, val iotMarks: Int, val apiMarks: Int): Parcelable {
            var percentage = 0.0
            var calculated = false

            constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readInt()) {
                percentage = parcel.readDouble()
                calculated = parcel.readByte() != 0.toByte()
}

    fun percentage() : Double {
        if (calculated) return percentage
        percentage = (androidMarks + iotMarks + apiMarks) / 3.0
        calculated = true
        return percentage
    }

    override fun toString(): String {
        return "$studentName: $percentage"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(studentID)
        parcel.writeString(studentName)
        parcel.writeInt(androidMarks)
        parcel.writeInt(iotMarks)
        parcel.writeInt(apiMarks)
        parcel.writeDouble(percentage)
        parcel.writeByte(if (calculated) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }
}



