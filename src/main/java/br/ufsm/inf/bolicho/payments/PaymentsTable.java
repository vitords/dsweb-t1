package br.ufsm.inf.bolicho.payments;

import br.ufsm.inf.bolicho.beans.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hstefan
 * Date: 06/01/13
 * Time: 18:05
 * To change this template use File | Settings | File Templates.
 */
public class PaymentsTable {
    public static final double MIN_INTEREST = 1.08;
    public static final double MONTHLY_INTEREST = 1.03; //this will be applied over base interest
    public static final int MIN_PAYMENTS_FOR_INTEREST = 6;

    public List<String> getPaymentsTable(Product product) {
        int maxPayments = getMaxPayments(product.getPrice());
        List<String> payments = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < maxPayments; ++i) {
            builder.append(i);
            builder.append("x de ");
            if (i > MIN_PAYMENTS_FOR_INTEREST) {
                double accumulativeInterest = 1 - Math.pow(MONTHLY_INTEREST, i - MIN_PAYMENTS_FOR_INTEREST);
                double monthlyInterest = MIN_INTEREST + accumulativeInterest;
                double interest = Math.pow(monthlyInterest, (i + 1) - MIN_PAYMENTS_FOR_INTEREST);
                double finalAmount = product.getPrice()* interest;
                builder.append(finalAmount/i);
                builder.append(" R$ (");
                builder.append((1 - monthlyInterest)*100);
                builder.append("% a.m.)");
            } else {
                builder.append(product.getPrice()/i);
                builder.append(" R$ (sem juros)"); //I'm assuming we'll use BRL (we should probably work on some kind of localization
                                                   //for the final project
            }
            payments.add(builder.toString());
            builder.setLength(0);
        }

        return payments;
    }

    private static int getMaxPayments(double price)
    {
        return 12; //this will be pretty much hard-coded for now
    }
}
