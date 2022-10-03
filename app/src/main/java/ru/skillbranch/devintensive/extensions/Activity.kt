package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val view = currentFocus ?: View(this)
    val token = view.windowToken

    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(token, 0)
}

fun Activity.isKeyboardOpen(): Boolean {
    val rect = Rect()

    val rootView = findViewById<View>(android.R.id.content).rootView
    rootView.getWindowVisibleDisplayFrame(rect)

    val screenHeight = rootView.height
    val keyBoardHeight = screenHeight - rect.bottom

    return keyBoardHeight > screenHeight * 0.15
}

fun Activity.isKeyboardClosed(): Boolean {

    val rect = Rect()

    val rootView = findViewById<View>(android.R.id.content).rootView
    rootView.getWindowVisibleDisplayFrame(rect)

    val screenHeight = rootView.height
    val keyBoardHeight = screenHeight - rect.bottom

    return keyBoardHeight < screenHeight * 0.15

}