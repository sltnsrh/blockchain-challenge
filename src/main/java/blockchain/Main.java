package blockchain;

import blockchain.model.BlockChain;
import blockchain.model.Miner;
import blockchain.model.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static final String BLOCKCHAIN_FILE = "src/main/resources/chain.ser";
    public static final Random random = new Random();
    public static final ExecutorService executorService = Executors.newFixedThreadPool(15);
    public static final int MINERS_LIMIT = 100;
    public static final int MAX_TRANSACTION = 50;
    public static int chainStopLimit = 30;

    public static void main(String[] args) {
        BlockChain blockChain = BlockChain.getInstance(BLOCKCHAIN_FILE);
        List<Miner> minersPool = getMinersPool(blockChain);
        process(minersPool, blockChain);
        executorService.shutdownNow();
    }

    public static void process(List<Miner> minersPool, BlockChain blockChain) {
        while (chainStopLimit > 0) {
            for (int i = 0; i < 5; i++) {
                if (chainStopLimit > 0) {
                    executeNewTransaction(minersPool, blockChain);
                    Miner miner = minersPool.get(random.nextInt(100));
                    Future<Integer> result = executorService.submit(miner);
                    try {
                        chainStopLimit -= result.get();
                    } catch (ExecutionException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static List<Miner> getMinersPool(BlockChain blockChain) {
        int minerId = 1;
        List<Miner> minersPool = new ArrayList<>();
        while (minerId <= MINERS_LIMIT) {
            minersPool.add(new Miner(minerId++, blockChain));
        }
        return minersPool;
    }

    public static void executeNewTransaction(List<Miner> minersPool, BlockChain blockChain) {
        Miner sender = null;
        Miner receiver = null;
        while (Objects.equals(sender, receiver)) {
            sender = minersPool.get(random.nextInt(MINERS_LIMIT));
            receiver = minersPool.get(random.nextInt(MINERS_LIMIT));
        }
        int transactionValue = (int) Math.floor(Math.random() * MAX_TRANSACTION + 1);
        executorService.submit(new Transaction(sender, receiver, transactionValue, blockChain));
    }
}
