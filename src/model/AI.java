package model;

import model.Client;

public class AI {
    // do math
    public static long calculateFees(Client client) {
        return Math.round(Math.random() * 10) * 1000;
    }

    public static long calculateWithdrawalLimit(Client client) {
        return Math.round(Math.random() * 10) * 1000;
    }

    public static long calculateMinimumBalance(Client client) {
        return Math.round(Math.random() * 10) * 1000;
    }

    public static long calculateCreditLimit(Client client) {
        return Math.round(Math.random() * 10) * 1000;
    }

    public static long calculateOverdraftLimit(Client client) {
        return Math.round(Math.random() * 10) * 100;
    }
}
