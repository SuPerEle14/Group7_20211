package subsystem_;

import entity.payment.CreditCard;

/**
 * interface of Interbank Subsystem
 * processTransaction : transaction confirmation
 * reset : reset bank account
 */
public interface IInterbank {
    String processTransaction(CreditCard card, int cost, String command, String content);

    void reset();
}
