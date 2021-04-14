package com.example.recyclev

import android.os.Parcel
import android.os.Parcelable

class TodoList() : Parcelable {
    var subject: String? = ""
    var contents: String? = ""

    constructor(parcel: Parcel) : this() {
        subject = parcel.readString()
        contents = parcel.readString()
    }

    constructor(subject: String?, contents: String?) : this() {
        this.subject = subject
        this.contents = contents
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(subject)
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
