package com.baul.banking_backend.enums;

public class Enums {

    public enum AccountType {
        Savings,
        Current
    }

    public enum CardType {
        DEBIT,
        CREDIT,
        PREPAID
    }

    public enum DepositType {
        Fixed,
        Recurring,
    }

    public enum UserRoles{
        ADMIN,
        USER
    }

}
