package blockchain;

import blockchain.service.BlockChainProcessor;

public class Main {

    public static void main(String[] args) {
        BlockChainProcessor blockChainProcessor = new BlockChainProcessor();
        blockChainProcessor.start();
    }
}
