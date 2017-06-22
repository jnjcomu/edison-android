package com.jnjcomu.edison.model

import com.google.gson.annotations.SerializedName

/**
 * @author CodeRi <ruto1924></ruto1924>@gmail.com>
 * *
 * @since 2017-05-06
 */

class User {
    var id: Int = 0
    var name: String? = null
    var grade: Int = 0
    var clazz: Int = 0
    var number: Int = 0

    constructor() {}

    constructor(id: Int, name: String, grade: Int, clazz: Int, number: Int) {
        this.id = id
        this.name = name
        this.grade = grade
        this.clazz = clazz
        this.number = number
    }
}
