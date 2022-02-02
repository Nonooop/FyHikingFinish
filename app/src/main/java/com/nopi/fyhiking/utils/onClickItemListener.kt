package com.nopi.fyhiking.utils

import com.nopi.fyhiking.model.ModelNote

interface onClickItemListener {
    fun onClick(modelNote: ModelNote, position: Int)
}