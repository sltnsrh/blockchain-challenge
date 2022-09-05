package blockchain.model;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MinerTest {
    private Miner miner;

    @BeforeEach
    public void setup() {
        miner = new Miner(1L, new MiningService(
                new BlockChain("path", new ArrayList<>(), new ArrayList<>())
        ));
    }

    @Test
    void fromBalanceWithEnoughMoney() {
        Assertions.assertTrue(miner.fromBalance(100));
    }

    @Test
    void fromBalanceWithNotEnoughCoins() {
        Assertions.assertFalse(miner.fromBalance(200));
    }

    @Test
    void toBalanceExpect200() {
        miner.toBalance(100);
        Assertions.assertEquals(200, miner.getBalance());
    }
}