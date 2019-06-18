package com.projecteugene.dialog

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.ArrayRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager


/**
 * Created by Eugene Low
 */
class DialogBuilder(private val context: Context,
                    private val fragmentManager: FragmentManager?,
                    private val dialogTag: String) {
    private val bundle: Bundle = Bundle()

    init {
        setTextNegativeButton(android.R.string.ok)
    }

    fun setStyle(@StyleRes style: Int) = apply {
        bundle.putInt(DialogConst.STYLE, style)
    }

    fun setView(@LayoutRes view: Int) = apply {
        bundle.putInt(DialogConst.VIEW, view)
    }
    fun setTitle(title: CharSequence) = apply {
        bundle.putCharSequence(DialogConst.TITLE, title)
    }
    fun setTitle(@StringRes title: Int) = apply {
        bundle.putCharSequence(DialogConst.TITLE, context.getString(title))
    }
    fun setMessage(message: CharSequence) = apply {
        bundle.putCharSequence(DialogConst.MESSAGE, message)
    }
    fun setMessage(@StringRes message: Int) = apply {
        bundle.putCharSequence(DialogConst.MESSAGE, context.getString(message))
    }
    fun setTextPositiveButton(textPositiveButton: CharSequence) = apply {
        bundle.putCharSequence(DialogConst.TEXT_POSITIVE_BUTTON, textPositiveButton)
    }
    fun setTextPositiveButton(@StringRes textPositiveButton: Int) = apply {
        bundle.putCharSequence(DialogConst.TEXT_POSITIVE_BUTTON, context.getString(textPositiveButton))
    }
    fun setTextNeutralButton(textNeutralButton: CharSequence) = apply {
        bundle.putCharSequence(DialogConst.TEXT_NEUTRAL_BUTTON, textNeutralButton)
    }
    fun setTextNeutralButton(@StringRes textNeutralButton: Int) = apply {
        bundle.putCharSequence(DialogConst.TEXT_NEUTRAL_BUTTON, context.getString(textNeutralButton))
    }
    fun setTextNegativeButton(textNegativeButton: CharSequence) = apply {
        bundle.putCharSequence(DialogConst.TEXT_NEGATIVE_BUTTON, textNegativeButton)
    }
    fun setTextNegativeButton(@StringRes textNegativeButton: Int) = apply {
        bundle.putCharSequence(DialogConst.TEXT_NEGATIVE_BUTTON, context.getString(textNegativeButton))
    }
    fun <T: Parcelable> setList(items: ArrayList<T>) = apply {
        bundle.putParcelableArrayList(DialogConst.ITEMS_PARCELABLE, items)
        bundle.putSerializable(DialogConst.MODE, DialogConst.Mode.LIST_PARCELABLE)
    }

    fun setStringList(items: ArrayList<String>) = apply {
        bundle.putStringArrayList(DialogConst.ITEMS_STRING, items)
        bundle.putSerializable(DialogConst.MODE, DialogConst.Mode.LIST_STRING)
    }
//    fun setListPosition(position: Int) = apply {
//        bundle.putInt(DialogConst.LIST_POSITION, position)
//    }
    fun setList(items: Array<CharSequence>) = apply {
        bundle.putCharSequenceArray(DialogConst.ITEMS, items)
        bundle.putSerializable(DialogConst.MODE, DialogConst.Mode.LIST)
    }
    fun setList(@ArrayRes items: Int) = apply {
        bundle.putCharSequenceArray(DialogConst.ITEMS, context.resources.getTextArray(items))
        bundle.putSerializable(DialogConst.MODE, DialogConst.Mode.LIST)
    }
    fun setSingle(items: Array<CharSequence>, default: Int = -1) = apply {
        bundle.putCharSequenceArray(DialogConst.ITEMS, items)
        bundle.putSerializable(DialogConst.MODE, DialogConst.Mode.SINGLE)
        bundle.putInt(DialogConst.DEFAULT_SINGLE, default)
    }
    fun setSingle(@ArrayRes items: Int, default: Int = -1) = apply {
        bundle.putCharSequenceArray(DialogConst.ITEMS, context.resources.getTextArray(items))
        bundle.putSerializable(DialogConst.MODE, DialogConst.Mode.SINGLE)
        bundle.putInt(DialogConst.DEFAULT_SINGLE, default)
    }
    fun setMulti(items: Array<CharSequence>, default: BooleanArray? = null) = apply {
        bundle.putCharSequenceArray(DialogConst.ITEMS, items)
        bundle.putSerializable(DialogConst.MODE, DialogConst.Mode.MULTI)
        bundle.putBooleanArray(DialogConst.DEFAULT_MULTI, default)
    }
    fun setMulti(@ArrayRes items: Int, default: BooleanArray? = null) = apply {
        bundle.putCharSequenceArray(DialogConst.ITEMS, context.resources.getTextArray(items))
        bundle.putSerializable(DialogConst.MODE, DialogConst.Mode.MULTI)
        bundle.putBooleanArray(DialogConst.DEFAULT_MULTI, default)
    }
    fun setCancelable(cancelable: Boolean) = apply {
        bundle.putBoolean(DialogConst.CANCELABLE, cancelable)
    }

    fun <T: ViewDataBinding> show(binding: T? = null): CustomDialogFragment<T> {
        val dialogFragment = CustomDialogFragment(binding)
        dialogFragment.arguments = bundle
        dialogFragment.show(fragmentManager, dialogTag)
        return dialogFragment
    }

    fun show(): CustomDialogFragment<Nothing> {
        val dialogFragment = CustomDialogFragment(null)
        dialogFragment.arguments = bundle
        dialogFragment.show(fragmentManager, dialogTag)
        return dialogFragment
    }
}