package com.neeraj.gamechangedemo.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.neeraj.gamechangedemo.R

class DialogUtils {
    companion object{
        fun showNoticeDialog(mContext: Context?, buttonTextID: Int, msgID: Int, onClickListener: DialogInterface.OnClickListener?) {
            if(mContext==null) return
            AlertDialog.Builder(mContext, R.style.AlertDialog).setMessage(msgID).setNeutralButton(buttonTextID, onClickListener).setCancelable(false).show()
        }
    }
}