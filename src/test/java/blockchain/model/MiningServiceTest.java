package blockchain.model;

import blockchain.service.MiningService;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MiningServiceTest {
    private MiningService miningService;
    private BlockChain blockChain;

    @BeforeEach
    public void setup() {
        blockChain = new BlockChain("path", new ArrayList<>(), new ArrayList<>());
        miningService = new MiningService(blockChain);
    }

    @Test
    void mineBlockForFirstBlock() {
        miningService.mineBlock(new Miner(1, miningService));
        Assertions.assertEquals(1, blockChain.getChain().get(0).getId());
        Assertions.assertEquals("0", blockChain.getChain().get(0).getPrevHash());
    }

    @Test
    void mineBlockForSecondBlock() {
        Block firstBlock = new Block();
        firstBlock.setId(1);
        firstBlock.setHash("hashOfFirstBlock");
        firstBlock.setPrevHash("0");
        blockChain.getChain().add(firstBlock);
        miningService.mineBlock(new Miner(1, miningService));
        Assertions.assertEquals(2, blockChain.getChain().get(1).getId());
        Assertions.assertEquals("hashOfFirstBlock", blockChain.getChain().get(1).getPrevHash());
    }

    @Test
    void mineBlockWithLongGenerationTimeExpectNoNewBlock() {
        Block firstBlock = new Block();
        firstBlock.setId(1);
        firstBlock.setHash("hashOfFirstBlock");
        firstBlock.setPrevHash("0");
        blockChain.getChain().add(firstBlock);
        blockChain.setCountOfZeros(20);
        miningService.mineBlock(new Miner(1, miningService));
        Assertions.assertEquals(1, blockChain.getChain().size());
    }
}