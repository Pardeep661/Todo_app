package com.pardeep.todo_app

import android.icu.text.Transliterator.Position

interface ActivityInterface {
    fun update(position : Int)
    fun delete(position : Int)
}