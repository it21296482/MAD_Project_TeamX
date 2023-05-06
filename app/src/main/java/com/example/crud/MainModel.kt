class MainModel {
    var name: String = ""
        get() = field
        set(value) {
            field = value
        }

    var licenseNo: String = ""
        get() = field
        set(value) {
            field = value
        }

    var phone: String = ""
        get() = field
        set(value) {
            field = value
        }

    var hdUrl: String = ""
        get() = field
        set(value) {
            field = value
        }

    constructor()

    constructor(name: String, licenseNo: String, phone: String, hdUrl: String) {
        this.name = name
        this.licenseNo = licenseNo
        this.phone = phone
        this.hdUrl = hdUrl
    }
}
