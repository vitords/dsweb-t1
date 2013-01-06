package br.ufsm.inf.bolicho.payments;

import br.ufsm.inf.bolicho.beans.Product;

import java.text.DecimalFormat;
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
    public static final double MIN_INTEREST = 1.03;
    public static final double MONTHLY_INTEREST = 1.005; //this will be applied over base interest
    public static final int MIN_PAYMENTS_FOR_INTEREST = 6;

    public static List<String> getPaymentsTable(Product product) {
        int maxPayments = getMaxPayments(product.getPrice());
        List<String> payments = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        for (int i = 1; i <= maxPayments; ++i) {
            builder.append(i);
            builder.append("x de ");
            if (i > MIN_PAYMENTS_FOR_INTEREST) {
                double accumulativeInterest = Math.pow(MONTHLY_INTEREST, i - MIN_PAYMENTS_FOR_INTEREST) - 1;
                double monthlyInterest = MIN_INTEREST + accumulativeInterest;
                double interest = Math.pow(monthlyInterest, (i + 1) - MIN_PAYMENTS_FOR_INTEREST);
                double finalAmount = product.getPrice()* interest;
                builder.append(finalAmount/i);
                builder.append(" R$ (");
                builder.append(decimalFormat.format((monthlyInterest - 1)*100));
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
