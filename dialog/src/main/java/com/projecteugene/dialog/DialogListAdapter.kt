package com.projecteugene.dialog

import android.content.Context
import android.widget.ArrayAdapter

/**
 * Created by Eugene Low
 */

class DialogListAdapter<T>(context: Context, list: ArrayList<T>) :
        ArrayAdapter<T>(context, android.R.layout.simple_list_item_1, list)
