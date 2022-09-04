package blockchain;

import blockchain.model.BlockChainProcessor;

public class Main {

    public static void main(String[] args) {
        BlockChainProcessor blockChainProcessor = new BlockChainProcessor();
        blockChainProcessor.start();
    }
}
