package se.tiny.bank

class TinyBankController {
    private static final String FROM_EMAIL = "no-reply@tinybank.se"

    def mailService

    def transactions() {}

    def listTransactions() {}

    def pay() {}

    def doPayment() {
        Account dr = Account.get(params.dr as long)
        Account cr = Account.get(params.cr as long)
        long amount = params.amt as long

        if (cr.balance > amount) {
            dr.balance += amount
            cr.balance -= amount
            Transaction tr = new Transaction(debit: dr, credit: cr, value: amount).save()
            saveAll(dr, cr, tr)

            sendDebitConfirmation(tr)
            sendCreditConfirmation(tr)
        }

        request.message = "Transfer complete"
        render(view: "pay")
    }

    private void saveAll(Object... data) {
        data.each {
            it.save(flush: true)
        }
    }

    private void sendCreditConfirmation(Transaction transaction) {
        mailService.sendMail {
            to transaction.credit.email
            from FROM_EMAIL
            subject "Credit transaction"
            body "${transaction.value} was transferred from your account to ${transaction.debit.name}"
        }
    }

    private void sendDebitConfirmation(Transaction transaction) {
        mailService.sendMail {
            to transaction.debit.email
            from FROM_EMAIL
            subject "Credit transaction"
            body "${transaction.value} was transferred from your account to ${transaction.credit.name}"
        }
    }
}
