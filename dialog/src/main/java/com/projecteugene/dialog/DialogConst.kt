package com.projecteugene.dialog

import android.content.DialogInterface

/**
 * Created by Eugene Low
 */
object DialogConst {
    private const val URI = "com.projecteugene.dialog"
    const val HAS_BINDING = "$URI.hasBinding"
    const val VIEW = "$URI.view"
    const val TITLE = "$URI.title"
    const val MESSAGE = "$URI.message"
    const val CANCELABLE = "$URI.cancelable"
    const val DISMISSIBLE_POSITIVE_ON_CLICK = "$URI.dismissiblePositiveOnClick"
    const val DISMISSIBLE_NEGATIVE_ON_CLICK = "$URI.dismissibleNegativeOnClick"
    const val DISMISSIBLE_NEUTRAL_ON_CLICK = "$URI.dismissibleNeutralOnClick"
    const val TEXT_POSITIVE_BUTTON = "$URI.textPositiveButton"
    const val TEXT_NEUTRAL_BUTTON = "$URI.textNeutralButton"
    const val TEXT_NEGATIVE_BUTTON = "$URI.textNegativeButton"
    const val LIST_POSITION = "$URI.listPosition"
    const val LIST_POSITION_ITEM = "$URI.listPositionItem"
    const val ITEMS = "$URI.items"
    const val ITEMS_PARCELABLE = "$URI.itemsParcelable"
    const val ITEMS_STRING = "$URI.itemsString"
    const val MODE = "$URI.mode"
    const val DEFAULT_SINGLE = "$URI.defaultSingle"
    const val DEFAULT_MULTI = "$URI.defaultMulti"
    const val DIALOG_TAG = "$URI.dialogTag"
    const val STYLE = "$URI.theme"

    enum class Mode {
        LIST, SINGLE, MULTI, LIST_PARCELABLE, LIST_STRING
    }
}