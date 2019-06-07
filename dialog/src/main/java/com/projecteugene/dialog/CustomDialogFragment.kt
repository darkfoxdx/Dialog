package com.projecteugene.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Eugene Low
 */
open class CustomDialogFragment<T: ViewDataBinding>(var binding: T? = null) : DialogFragment() {
    private val style: Int
        get() = arguments?.getInt(DialogConst.STYLE, 0)?:0

    private val view: Int
        get() = arguments?.getInt(DialogConst.VIEW, 0)?:0

    private val title: CharSequence?
        get() = arguments?.getCharSequence(DialogConst.TITLE)

    private val message: CharSequence?
        get() = arguments?.getCharSequence(DialogConst.MESSAGE)

    private val textPositiveButton: CharSequence?
        get() = arguments?.getCharSequence(DialogConst.TEXT_POSITIVE_BUTTON)

    private val textNeutralButton: CharSequence?
        get() = arguments?.getCharSequence(DialogConst.TEXT_NEUTRAL_BUTTON)

    private val textNegativeButton: CharSequence?
        get() = arguments?.getCharSequence(DialogConst.TEXT_NEGATIVE_BUTTON)

    private val items: Array<CharSequence>?
        get() = arguments?.getCharSequenceArray(DialogConst.ITEMS)

    private val itemsParcelable: ArrayList<Parcelable>?
        get() = arguments?.getParcelableArrayList(DialogConst.ITEMS_PARCELABLE)

    private val itemsString: ArrayList<String>?
        get() = arguments?.getStringArrayList(DialogConst.ITEMS_STRING)

//    private val listPosition: Int?
//        get() = arguments?.getInt(DialogConst.LIST_POSITION)

    private val defaultSingle: Int
        get() = arguments?.getInt(DialogConst.DEFAULT_SINGLE)?:-1

    private val defaultMulti: BooleanArray?
        get() = arguments?.getBooleanArray(DialogConst.DEFAULT_MULTI)

    private val cancelable: Boolean
        get() = arguments?.getBoolean(DialogConst.CANCELABLE, true)?:true

    private val hasBinding: Boolean
        get() = arguments?.getBoolean(DialogConst.HAS_BINDING, false)?:false

    val dialogTag: String
        get() = arguments?.getString(DialogConst.DIALOG_TAG, "")?:""

    private val mode: DialogConst.Mode?
        get() = arguments?.getSerializable(DialogConst.MODE) as DialogConst.Mode?

    data class DialogItemHolder(val dialog: DialogInterface, val which: Int = -1, val isChecked: Boolean = false)

    private val onDialogShownSubject: PublishSubject<Dialog> = PublishSubject.create<Dialog>()
    val onDialogShown: Observable<Dialog>
        get() = onDialogShownSubject

    private val onDialogDismissSubject: PublishSubject<DialogInterface> = PublishSubject.create<DialogInterface>()
    val onDialogDismiss: Observable<DialogInterface>
        get() = onDialogDismissSubject

    private val onPositiveClickSubject: PublishSubject<DialogInterface> = PublishSubject.create<DialogInterface>()
    val onPositiveClick: Observable<DialogInterface>
        get() = onPositiveClickSubject

    private val onNegativeClickSubject: PublishSubject<DialogInterface> = PublishSubject.create<DialogInterface>()
    val onNegativeClick: Observable<DialogInterface>
        get() = onNegativeClickSubject

    private val onNeutralClickSubject: PublishSubject<DialogInterface> = PublishSubject.create<DialogInterface>()
    val onNeutralClick: Observable<DialogInterface>
        get() = onNeutralClickSubject

    private val onItemClickSubject: PublishSubject<DialogItemHolder> = PublishSubject.create<DialogItemHolder>()
    val onItemClick: Observable<DialogItemHolder>
        get() = onItemClickSubject

    override fun onStart() {
        super.onStart()
        onDialogShownSubject.onNext(dialog)
        onDialogShownSubject.onComplete()
    }

    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the Builder class for convenient dialog construction
        val builder =
                if (style != 0)
                    AlertDialog.Builder(requireContext(), style)
                else
                    AlertDialog.Builder(requireContext())
        if (view != 0) {
            builder.setView(view)
        }
        if (binding != null) {
            builder.setView(binding?.root)
        }
        title?.let { builder.setTitle(it) }
        message?.let { builder.setMessage(it) }
        when (mode) {
            DialogConst.Mode.LIST ->
                items?.let {
                    builder.setItems(it) { dialog, which ->
                        onItemClickSubject.onNext(DialogItemHolder(dialog, which))
                    }
                }
            DialogConst.Mode.LIST_PARCELABLE ->
                itemsParcelable?.let {
                    val adapter = DialogListAdapter(requireContext(), it)
                    builder.setAdapter(adapter) { dialog, which ->
                        onItemClickSubject.onNext(DialogItemHolder(dialog, which))
                    }
                }
            DialogConst.Mode.LIST_STRING ->
                itemsString?.let {
                    val adapter = DialogListAdapter(requireContext(), it)
                    builder.setAdapter(adapter) { dialog, which ->
                        onItemClickSubject.onNext(DialogItemHolder(dialog, which))
                    }
                }
            DialogConst.Mode.SINGLE ->
                items?.let {
                    builder.setSingleChoiceItems(it, defaultSingle) { dialog, which ->
                        onItemClickSubject.onNext(DialogItemHolder(dialog, which))
                    }
                }
            DialogConst.Mode.MULTI ->
                items?.let {
                    builder.setMultiChoiceItems(it, defaultMulti) { dialog, which, isChecked ->
                        onItemClickSubject.onNext(DialogItemHolder(dialog, which, isChecked))
                    }
                }
        }

        textPositiveButton?.let {
            builder.setPositiveButton(it) {
                dialog, id -> onPositiveClickSubject.onNext(dialog)
            }
        }
        textNegativeButton?.let {
            builder.setNegativeButton(it) {
                dialog, id -> onNegativeClickSubject.onNext(dialog)
            }
        }
        textNeutralButton?.let {
            builder.setNeutralButton(it) {
                dialog, id -> onNeutralClickSubject.onNext(dialog)
            }
        }

        builder.setOnDismissListener {
            dialog->
            super.onDismiss(dialog)
            onDialogDismissSubject.onNext(dialog)
        }

        isCancelable = cancelable

        // Create the AlertDialog object and return it
        return builder.create()
    }

    companion object {
        val TAG = CustomDialogFragment::class.java.simpleName
    }

}
