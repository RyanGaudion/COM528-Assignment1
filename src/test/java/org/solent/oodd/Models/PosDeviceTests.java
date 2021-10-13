package org.solent.oodd.Models;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PosDeviceTests {
    @Test
    public void setCardTest()
    {
        Card card = new Card();
        assertEquals(true, card.SetCardNumber("0000 0000 0000 0000"));
        assertEquals(true, card.SetCVV("111"));

        PosDevice pos = new PosDevice();
        assertEquals(true, pos.SetCard(card));

        assertEquals("0000000000000000", pos.GetCard().GetCardNumber());
        assertEquals("111", pos.GetCard().GetCVV());
    }

    @Test
    public void addTransactionTest()
    {
        Card toCard = new Card();
        assertEquals(true, toCard.SetCardNumber("0000 0000 0000 0000"));

        Card fromCard = new Card();
        assertEquals(true, fromCard.SetCardNumber("1111 1111 1111 1111"));

        Transaction t = new Transaction(fromCard, toCard, 10.00, TransactionType.PAYMENT);
        Transaction t2 = new Transaction(fromCard, toCard, 5.00, TransactionType.REFUND);

        PosDevice pos = new PosDevice();
        assertEquals(true, pos.AddTransaction(t));
        assertEquals(true, pos.AddTransaction(t2));


        assertEquals(2, pos.GetTransactions().size());

        assertEquals(true, pos.GetTransactions().contains(t));
        assertEquals(true, pos.GetTransactions().contains(t2));
    }
}
