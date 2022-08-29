package blockchain;

import blockchain.model.BlockChain;
import blockchain.model.Processor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static final String BLOCKCHAIN_FILE = "src/main/resources/chain.ser";
    public static final ExecutorService executorService = Executors.newFixedThreadPool(15);

    public static void main(String[] args) {
        BlockChain blockChain = BlockChain.getInstance(BLOCKCHAIN_FILE);
        Processor processor = new Processor(executorService, blockChain);
        processor.start();
        executorService.shutdownNow();
    }
}
