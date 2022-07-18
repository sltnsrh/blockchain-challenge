package blockchain.model;

import blockchain.Main;
import blockchain.util.ChainValidator;
import blockchain.util.ZeroOptimizer;
import blockchain.util.HashUtil;
import blockchain.util.SerializationUtils;
import java.util.concurrent.Callable;

public class Miner implements Callable<Integer> {
    private static final int FIRST_ID = 1;
    private static final int ZERO_HASH = 0;
    private static final int MINING_PAYMENT = 100;
    private final long id;
    private int account = 100;
    private final BlockChain blockChain;

    public Miner(long id, BlockChain blockChain) {
        this.id = id;
        this.blockChain = blockChain;
    }

    public void toAccount(int value) {
        this.account = account + value;
    }

    public boolean fromAccount(int value) {
        if (this.account >= value) {
            this.account = account - value;
            return true;
        }
        return false;
    }

    public long getId() {
        return this.id;
    }

    @Override
    public Integer call() {
        return mineBlock();
    }

    public Integer mineBlock() {
        long startGenerationTime = System.currentTimeMillis();
        Block block = new Block();
        if (blockChain.getChain().isEmpty()) {
            block.setId(FIRST_ID);
            block.setPrevHash(String.valueOf(ZERO_HASH));
        } else {
            block.setId(blockChain.getLast().getId() + 1);
            block.setPrevHash(blockChain.getLast().getHash());
        }
        block.setMinerId(this.id);
        block.setTimeStamp(System.currentTimeMillis());
        block.setZeroN(blockChain.getCountOfZeros());
        HashUtil.setMagicAndHash(block, blockChain);
        block.setGenerationTime(System.currentTimeMillis() - startGenerationTime);
        if (!ChainValidator.validate(block, blockChain)) {
            ZeroOptimizer.optimizeZeros(block, blockChain);
            return 0;
        }
        block.setBlockData(blockChain.getAllTransactionsInfo());
        blockChain.clearTransactionsList();
        ZeroOptimizer.optimizeZeros(block, blockChain);
        block.printBlockInfo(blockChain);
        blockChain.getChain().add(block);
        toAccount(MINING_PAYMENT);
        SerializationUtils.serialize(blockChain, Main.BLOCKCHAIN_FILE);
        return 1;
    }
}
