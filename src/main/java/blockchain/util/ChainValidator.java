package blockchain.util;

import blockchain.model.Block;
import blockchain.model.BlockChain;

public final class ChainValidator {
    private static final String ZERO_HASH = "0";
    private static final int MAX_GENERATION_TIME = 250;
    private static final int SINGLE_BLOCK = 1;
    private static final int ZERO_MAGIC = 0;

    public static boolean validate(Block block, BlockChain blockChain) {
        if (block.getHash().isEmpty() && block.getMagic() == ZERO_MAGIC
                || block.getGenerationTime() > MAX_GENERATION_TIME) {
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
}
