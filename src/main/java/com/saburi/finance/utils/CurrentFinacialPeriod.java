/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saburi.finance.utils;

import com.saburi.finance.dbaccess.FinancialPeriodDA;

/**
 *
 * @author CLINICMASTER13
 */
public class CurrentFinacialPeriod {

    private static FinancialPeriodDA currentFinancialPeriodDA = FinancialPeriodDA.getCurrentFinancialPeriodDA();

    public static void setFinancialPeriodDA(FinancialPeriodDA currentFinancialPeriodDA) {
        CurrentFinacialPeriod.currentFinancialPeriodDA = currentFinancialPeriodDA;
    }

    public static String getCurrentPeriodNo() {
        return currentFinancialPeriodDA.getPeriodID();
    }

    public static FinancialPeriodDA getFinancialPeriodDA() {
        return CurrentFinacialPeriod.currentFinancialPeriodDA;
    }

}
