package blockchain.model;

import blockchain.service.MiningService;
import java.util.concurrent.Callable;

public class Miner implements Callable<Integer> {
    private final long id;
    private final MiningService miningService;
    private int balance = 100;

    public Miner(long id, MiningService miningService) {
        this.id = id;
        this.miningService = miningService;
    }

    @Override
    public Integer call() {
        return miningService.mineBlock(this);
    }

    public void toBalance(int value) {
        this.balance = balance + value;
    }

    public boolean fromBalance(int value) {
        if (this.balance >= value) {
            this.balance = balance - value;
            return true;
        }
        return false;
    }

    public long getId() {
        return this.id;
    }

    public int getBalance() {
        return balance;
    }
}
