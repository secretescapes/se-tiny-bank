package se.tiny.bank

class Account {
    String name
    String email
    long balance = 200

    static constraints = {
        email email:true, blank:false
    }

}
