package com.example.recyclev

import android.os.Parcel
import android.os.Parcelable

class TodoList(var contents: String?) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(contents)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TodoList> {
        override fun createFromParcel(parcel: Parcel): TodoList {
            return TodoList(parcel)
        }

        override fun newArray(size: Int): Array<TodoList?> {
            return arrayOfNulls(size)
        }
    }

}
