package blockchain.model;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BlockChainProcessorTest {
    private final BlockChainProcessor blockChainProcessor = new BlockChainProcessor();

    @Test
    void startAssert30BlocksAtFinish() {
        int startBlockChainSize = blockChainProcessor.getBlockChain().getChain().size();
        blockChainProcessor.start();
        Assertions.assertEquals(startBlockChainSize + 30,
                blockChainProcessor.getBlockChain().getChain().size());
    }

    @Test
    void getMinerPoolAssert100Miners() {
        List<Miner> actual = blockChainProcessor.getMinersPool();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(100, actual.size());
    }
}