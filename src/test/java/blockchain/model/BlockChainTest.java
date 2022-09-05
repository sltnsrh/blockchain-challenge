package blockchain.model;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlockChainTest {
    private BlockChain blockChain;

    @BeforeEach
    public void setup() {
        blockChain = new BlockChain("path", new ArrayList<>(), new ArrayList<>());
    }

    @Test
    void addTransactionExpectTwoTransactionsInList() {
        blockChain.addTransaction(new Transaction(
                new Miner(1L, new MiningService(blockChain)),
                new Miner(2L, new MiningService(blockChain)),
                50,
                blockChain)
        );
        blockChain.addTransaction(new Transaction(
                new Miner(3L, new MiningService(blockChain)),
                new Miner(4L, new MiningService(blockChain)),
                20,
                blockChain)
        );
        Assertions.assertEquals(2, blockChain.getTransactions().size());
    }

    @Test
    void getAllTransactionInfoExpectNoTransactions() {
        String actual = blockChain.getAllTransactionsInfo();
        Assertions.assertEquals("no transactions", actual);
    }

    @Test
    void getAllTransactionsInfoExpectOneTransaction() {
        blockChain.addTransaction(new Transaction(
                new Miner(1L, new MiningService(blockChain)),
                new Miner(2L, new MiningService(blockChain)),
                        50,
                        blockChain)
        );
        String actual = blockChain.getAllTransactionsInfo();
        Assertions.assertEquals(1, blockChain.getTransactions().size());
        Assertions.assertEquals("Miner #1 sent 50 coins to miner #2", actual);
    }

    @Test
    void clearTransactionsListExpectEmptyList() {
        blockChain.addTransaction(new Transaction(
                new Miner(1L, new MiningService(blockChain)),
                new Miner(2L, new MiningService(blockChain)),
                50,
                blockChain)
        );
        blockChain.clearTransactionsList();
        Assertions.assertTrue(blockChain.getTransactions().isEmpty());
    }
}