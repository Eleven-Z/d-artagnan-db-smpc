package pt.uminho.haslab.smpc.sharemindImp.BigInteger;

import pt.uminho.haslab.smpc.exceptions.InvalidNumberOfBits;
import pt.uminho.haslab.smpc.interfaces.Dealer;

import java.math.BigInteger;

public abstract class AbstractSharemindDealer implements Dealer {

    protected final int nbits;

    /* The modulus ring (2^(n+1)) */
    protected BigInteger power;

    public AbstractSharemindDealer(int nbits) throws InvalidNumberOfBits {

        if (nbits <= 0) {
            String message = "The number of bits must be greater than 0.";
            message += " Inserted nbits was " + nbits + ".";
            throw new InvalidNumberOfBits(message);
        }
        this.nbits = nbits;
    }

}
