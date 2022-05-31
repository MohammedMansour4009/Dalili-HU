package com.buyin.dalili.core.common

import android.content.res.Resources
import android.graphics.Color
import android.net.Uri
import android.util.TypedValue
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

@BindingAdapter("imageUrl")
fun ImageView.imageUrl(url: String?) {
    load(url)
}

@BindingAdapter("imageUrl", "radius")
fun ImageView.imageUrl(url: String?, radius: Float) {
    load("https://picsum.photos/536/354/?blur&date=${System.nanoTime()}") {
        transformations(RoundedCornersTransformation(radius.toPx))
    }
}

@BindingAdapter("imageUri", "radius")
fun ImageView.imageUri(uri: Uri, radius: Float) {
    load(uri) {
        transformations(RoundedCornersTransformation(radius.toPx))
    }
}

@BindingAdapter("imageUri", "mock")
fun ImageView.imageUri(uri: Uri, isMock: Boolean? = false) {
    if (isMock == true) {
        load("https://picsum.photos/536/354/?blur&date=${System.nanoTime()}")
        return
    }
    load(uri)
}

@BindingAdapter("imageUri")
fun ImageView.imageUri(uri: Uri) {
    load(uri)
}

@BindingAdapter("imageUrl", "mock")
fun ImageView.imageUrl(url: String?, isMock: Boolean? = false) {
    if (isMock == true) {
        load("https://picsum.photos/536/354/?blur&date=${System.nanoTime()}")
        return
    }
    load(url)
}

@BindingAdapter("imageResource")
fun ImageView.imageResource(resourceId: Int) {
    this.setImageResource(resourceId)
}

@BindingAdapter("setShadeColor")
fun MaterialCardView.setShadeColor(color: String) {
    val colorAfterShading = "#54${color}"
    this.strokeColor = Color.parseColor("#${color}")
    this.setCardBackgroundColor(Color.parseColor(colorAfterShading))
}

@BindingAdapter("setShadeColor")
fun MaterialButton.setShadeColor(color: String) {
    this.setBackgroundColor(Color.parseColor("#${color}"))
}


val Number.toPx
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )
