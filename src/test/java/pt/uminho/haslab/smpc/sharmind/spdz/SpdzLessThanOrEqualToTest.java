package pt.uminho.haslab.smpc.sharmind.spdz;

import dk.alexandra.fresco.framework.value.SInt;
import pt.uminho.haslab.smpc.spdzImpl.FPEvaluator;
import pt.uminho.haslab.smpc.spdzImpl.SpdzSSF;

import java.math.BigInteger;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class SpdzLessThanOrEqualToTest extends DoubleSPDZProtocolTest  {

    public SpdzLessThanOrEqualToTest(int nbits, List<BigInteger> firstValues,
                         List<BigInteger> secondValues) {
        super(nbits, firstValues, secondValues);

    }


    @Override
    public List<SInt> runProtocol(List<SInt> firstShares,
                                  List<SInt> secondShares, FPEvaluator evaluator) {
        return SpdzSSF.lessThanOrEqualTo(firstShares, secondShares, evaluator);
    }


    @Override
    public void condition(SpdzDB db1, SpdzDB db2, SpdzDB db3) {

        for(int i  = 0; i < db1.getResult().size(); i++){
            SInt[] results = new SInt[3];
            results[0] = db1.getResult().get(i);
            results[1] = db2.getResult().get(i);
            results[2] =  db3.getResult().get(i);
            int result = dealer.unshare(results);
            int comparisonResult = this.firstValues.get(i).compareTo(this.secondValues.get(i));
            int expectedResult = comparisonResult == 0 || comparisonResult == -1
                    ? 1
                    : 0;
            assertEquals(expectedResult, result);

        }
    }
}
