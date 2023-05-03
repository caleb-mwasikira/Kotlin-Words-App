package com.example.wordsapp.accessibility

import android.os.Build
import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes

// Setup custom accessibility delegate to set the text read with
// an accessibility service
class Accessibility(@StringRes val stringResId: Int) : View.AccessibilityDelegate() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onInitializeAccessibilityNodeInfo(
        host: View,
        info: AccessibilityNodeInfo
    ) {
        super.onInitializeAccessibilityNodeInfo(host, info)
        // With `null` as the second argument to [AccessibilityAction], the
        // accessibility service announces "double tap to activate".
        // If a custom string is provided,
        // it announces "double tap to <custom string>".
        val customString = host.context?.getString(stringResId)
        val customClick =
            AccessibilityNodeInfo.AccessibilityAction(
                AccessibilityNodeInfo.ACTION_CLICK,
                customString
            )
        info.addAction(customClick)
    }
}