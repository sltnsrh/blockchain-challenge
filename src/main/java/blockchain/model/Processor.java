package blockchain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Processor {
    private static final int MINERS_LIMIT = 100;
    private static final int MAX_TRANSACTION = 50;
    private final Random random = new Random();
    private final ExecutorService executorService;
    private final BlockChain blockChain;
    private static int chainStopLimit = 30;

    public Processor(ExecutorService executorService, BlockChain blockChain) {
        this.executorService = executorService;
        this.blockChain = blockChain;
    }

    public void start() {
        List<Miner> minersPool = getMinersPool();
        while (chainStopLimit > 0) {
            for (int i = 0; i < 5; i++) {
                if (chainStopLimit > 0) {
                    executeNewRandomTransaction(minersPool);
                    Miner miner = minersPool.get(random.nextInt(MINERS_LIMIT));
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

    public List<Miner> getMinersPool() {
        int minerId = 1;
        List<Miner> minersPool = new ArrayList<>();
        while (minerId <= MINERS_LIMIT) {
            minersPool.add(new Miner(minerId++, new MiningService(blockChain)));
        }
        return minersPool;
    }

    public void executeNewRandomTransaction(List<Miner> minersPool) {
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
