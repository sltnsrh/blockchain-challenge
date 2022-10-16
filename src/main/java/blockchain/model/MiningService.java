package blockchain.model;

import static blockchain.util.Settings.*;

import blockchain.util.HashUtil;
import blockchain.util.SerializationUtils;

public class MiningService {
    private static final String ZERO_HASH = "0";
    private static final int SINGLE_BLOCK = 1;
    private static final int ZERO_MAGIC = 0;
    private static final int FIRST_ID = 1;
    private final BlockChain blockChain;

    public MiningService(BlockChain blockChain) {
        this.blockChain = blockChain;
    }

    public Integer mineBlock(Miner miner) {
        long startGenerationTime = System.currentTimeMillis();
        Block block = new Block();
        setIdAndHash(block);
        block.setMinerId(miner.getId());
        block.setTimeStamp(System.currentTimeMillis());
        block.setRequiredZerosInHash(blockChain.getCountOfZeros());
        HashUtil.setMagicAndHash(block, blockChain, MAX_GENERATION_TIME);
        block.setGenerationTime(System.currentTimeMillis() - startGenerationTime);
        if (!validate(block)) {
            optimizeZeros(block);
            return 0;
        }
        block.setBlockData(blockChain.getAllTransactionsInfo());
        blockChain.clearTransactionsList();
        optimizeZeros(block);
        block.printBlockInfo(blockChain);
        blockChain.getChain().add(block);
        miner.toBalance(MINING_PAYMENT);
        SerializationUtils.serialize(blockChain, blockChain.getChainStoragePath());
        return 1;
    }

    private void setIdAndHash(Block block) {
        if (blockChain.getChain().isEmpty()) {
            block.setId(FIRST_ID);
            block.setPrevHash(ZERO_HASH);
        } else {
            block.setId(blockChain.getLast().getId() + 1);
            block.setPrevHash(blockChain.getLast().getHash());
        }
    }

    private boolean validate(Block block) {
        if (block.getHash().isEmpty() && block.getMagic() == ZERO_MAGIC) {
            return false;
        }
        if (blockChain.getChain().isEmpty()) {
            return true;
        }
        if (blockChain.getChain().size() == SINGLE_BLOCK) {
            return blockChain.getLast().getPrevHash().equals(ZERO_HASH);
        }
        return  blockChain.getLast().getHash().equals(block.getPrevHash());
    }

    private synchronized void optimizeZeros(Block block) {
        int actualCountOfZeros = blockChain.getCountOfZeros();
        if (block.getGenerationTime() < MIN_GENERATION_TIME) {
            blockChain.setCountOfZeros(++actualCountOfZeros);
        }
        if (block.getGenerationTime() > MAX_GENERATION_TIME && blockChain.getCountOfZeros() > 0) {
            blockChain.setCountOfZeros(--actualCountOfZeros);
        }
    }
}
