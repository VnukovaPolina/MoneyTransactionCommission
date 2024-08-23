package ru.netology

const val maxSumPerMonth = 600_000
const val maxSumPerDay = 150_000
const val MastercardMonthlyLimit = 75_000

fun main() {
    val amount = 5700.0
    val cardType = "МИР"
    val previousAmountThisMonth = 0.0
    val commission: Double

    commission = calculateCommission(cardType, previousAmountThisMonth, amount)
    if (commission >= 0) {
        println(commission)
    }
    else {
        println("Операция заблокирована")
    }
}

fun calculateCommission(cardType : String = "МИР", previousTransactionsAmount : Double = 0.0, amountForTransaction : Double) : Double {
    if (amountForTransaction > maxSumPerDay || previousTransactionsAmount > maxSumPerMonth || (previousTransactionsAmount + amountForTransaction) > maxSumPerMonth) {
        return -1.0
    }

    when (cardType) {
        "Mastercard" -> {
            when {
                previousTransactionsAmount > MastercardMonthlyLimit -> return amountForTransaction * 0.006 + 20

                (previousTransactionsAmount + amountForTransaction) > MastercardMonthlyLimit ->
                    return (amountForTransaction + previousTransactionsAmount - MastercardMonthlyLimit) * 0.006 + 20

                else -> return 0.0
            }
        }

        "Visa" -> return if (0.0075 * amountForTransaction > 35.0) 0.0075 * amountForTransaction else 35.0

        "МИР" -> return 0.0

        else -> return -1.0
    }
}