package com.definex.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CustomType(
    val name: String,
    val surname: String
): Parcelable
