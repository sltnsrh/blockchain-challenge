package blockchain.model;

import static blockchain.util.Settings.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BlockChainProcessor {
    private final ExecutorService executorService = Executors.newFixedThreadPool(15);
    private final Random random = new Random();
    private final BlockChain blockChain = BlockChain.getInstance(BLOCKCHAIN_FILE_PATH);

    public BlockChainProcessor() {
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
                        throw new RuntimeException("Can't get a result of mining process execution.", e);
                    }
                }
            }
        }
        executorService.shutdownNow();
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
        int transactionValue = (int) Math.floor(Math.random() * MAX_TRANSACTION_VALUE + 1);
        executorService.submit(new Transaction(sender, receiver, transactionValue, blockChain));
    }

    public BlockChain getBlockChain() {
        return blockChain;
    }
}
