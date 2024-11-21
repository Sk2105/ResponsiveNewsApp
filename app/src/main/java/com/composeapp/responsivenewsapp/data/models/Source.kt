package com.composeapp.responsivenewsapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class Source(
    val id: String?= null,
    val name: String?
) : Parcelable