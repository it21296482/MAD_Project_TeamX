package com.example.reviews

class MainModel {
    var name: String = ""
        get() = field
        set(value) {
            field = value
        }

    var email: String = ""
        get() = field
        set(value) {
            field = value
        }

    var imgUrl: String = ""
        get() = field
        set(value) {
            field = value
        }

    var description: String = ""
        get() = field
        set(value) {
            field = value
        }

    constructor()

    constructor(name: String, email: String, imgUrl: String, description: String) {
        this.name = name
        this.email = email
        this.imgUrl = imgUrl
        this.description = description
    }
}
