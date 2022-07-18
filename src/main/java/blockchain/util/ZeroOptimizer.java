package blockchain.util;

import blockchain.model.Block;
import blockchain.model.BlockChain;

public final class ZeroOptimizer {
    private static final int MIN_GENERATION_TIME = 150;
    private static final int MAX_GENERATION_TIME = 250;


    public static synchronized void optimizeZeros(Block block, BlockChain blockChain) {
        int actualCountOfZeros = blockChain.getCountOfZeros();
        if (block.getGenerationTime() < MIN_GENERATION_TIME) {
            blockChain.setCountOfZeros(++actualCountOfZeros);
        }
        if (block.getGenerationTime() > MAX_GENERATION_TIME && blockChain.getCountOfZeros() > 0) {
            blockChain.setCountOfZeros(--actualCountOfZeros);
        }
    }

    public static int getMaxGenerationTime() {
        return MAX_GENERATION_TIME;
    }
}
