package pt.uminho.haslab.smpc.sharmind.batch;

import pt.uminho.haslab.smpc.interfaces.Player;
import pt.uminho.haslab.smpc.interfaces.SharedSecret;
import pt.uminho.haslab.smpc.sharemindImp.BigInteger.SharemindSecretFunctions;
import pt.uminho.haslab.smpc.sharemindImp.BigInteger.SharemindSharedSecret;
import pt.uminho.haslab.smpc.sharmind.helpers.BatchDbTest;

import java.math.BigInteger;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ReshareTest extends SingleBatchValueProtocolTest {

    public ReshareTest(int nbits, List<BigInteger> values) {
        super(nbits, values);
    }

    @Override
    public List<byte[]> runProtocol(List<byte[]> shares, Player player) {
        SharemindSecretFunctions ssf = new SharemindSecretFunctions(nbits);
        return ssf.reshare(shares, player);

    }

    @Override
    public void condition(BatchDbTest db1, BatchDbTest db2, BatchDbTest db3) {
        List<byte[]> db1Results = db1.getResult();
        List<byte[]> db2Results = db2.getResult();
        List<byte[]> db3Results = db3.getResult();

        assertEquals(db1Results.size(), db2Results.size());
        assertEquals(db2Results.size(), db3Results.size());

        for (int i = 0; i < db1Results.size(); i++) {
            BigInteger u1 = new BigInteger(db1Results.get(i));
            BigInteger u2 = new BigInteger(db2Results.get(i));
            BigInteger u3 = new BigInteger(db3Results.get(i));
            SharedSecret secret = new SharemindSharedSecret(nbits + 1, u1, u2,
                    u3);
            // System.out.println("Secret result "+ secret.unshare());
            // System.out.println("Original val "+ this.values.get(i));
            assertEquals(secret.unshare(), this.values.get(i));
        }
    }

}
