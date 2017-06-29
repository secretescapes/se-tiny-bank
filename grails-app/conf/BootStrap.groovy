import se.tiny.bank.*

class BootStrap {

    def init = { servletContext ->
        new Account(name: "Bob", email: "bob@mailinator.com").save()
        new Account(name: "John", email: "john@mailinator.com").save()
        new Account(name: "Kate", email: "kate@mailinator.com").save()
    }

    def destroy = {
    }
}
