/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saburi.finance.utils;

/**
 *
 * @author ClinicMaster13
 */
public class FinanceEnums {


    public enum AccountActions {
        Debit, Credit
    };

    public enum AccountReports {
        Balance_Sheet, Financial_Statement
    };

    public enum AccountTypes {
        Asset, Liability, Equity, Income, Expense
    };

   
    public enum DocumentTypes {
        Invoice, Receipt, Credit_Memo, Refund
    };

    public enum PostStatus {
        Posted, Pending
    };

    public enum YesNo {
        Yes, No
    };

    public enum InvoiceStatus {
        Pending, Invoiced, Cancelled
    }

    public enum InvoiceTypes {
        Direct, Ordered, Popup
    }

    public enum EntryPoints {
        Billing, Invoicing, Receipting, Registration
    }

    public enum PayModes {
        Cash, Bank
    }

    public enum BankAccountTypes {
        Cash, Bank
    }

    public enum ItemCategoryGroups {
        Inventory, Service
    }

    public enum UnitMeasureUsages {
        Invoicing, Purchasing
    }

    public enum AccountGroups {
        COA, Customer, Vender, Bank, Item
    }

    public enum JournalTypes {
        General, Sales, Purchases, Bank, Items
    }

    public enum RequestStatus {
        Pending, Processing, Approved, Cancelled, Concluded
    }

    public enum ApprovalModes {
        Auto, Manual
    }

}
