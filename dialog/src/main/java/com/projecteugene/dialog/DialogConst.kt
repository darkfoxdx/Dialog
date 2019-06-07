package com.projecteugene.dialog

import android.content.DialogInterface

/**
 * Created by Eugene Low
 */
object DialogConst {
    const val HAS_BINDING = "hasBinding"
    const val VIEW = "view"
    const val TITLE = "title"
    const val MESSAGE = "message"
    const val CANCELABLE = "cancelable"
    const val TEXT_POSITIVE_BUTTON = "textPositiveButton"
    const val TEXT_NEUTRAL_BUTTON = "textNeutralButton"
    const val TEXT_NEGATIVE_BUTTON = "textNegativeButton"
    const val LIST_POSITION = "listPosition"
    const val LIST_POSITION_ITEM = "listPositionItem"
    const val ITEMS = "items"
    const val ITEMS_PARCELABLE = "itemsParcelable"
    const val ITEMS_STRING = "itemsString"
    const val MODE = "mode"
    const val DEFAULT_SINGLE = "defaultSingle"
    const val DEFAULT_MULTI = "defaultMulti"
    const val DIALOG_TAG = "dialogTag"
    const val STYLE = "theme"

    enum class Mode {
        LIST, SINGLE, MULTI, LIST_PARCELABLE, LIST_STRING
    }
}