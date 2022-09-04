package blockchain;

import blockchain.model.BlockChain;
import blockchain.model.BlockChainProcessor;

public class Main {
    public static final String BLOCKCHAIN_FILE = "src/main/resources/chain.ser";

    public static void main(String[] args) {
        BlockChain blockChain = BlockChain.getInstance(BLOCKCHAIN_FILE);
        BlockChainProcessor blockChainProcessor = new BlockChainProcessor(blockChain);
        blockChainProcessor.start();
    }
}
