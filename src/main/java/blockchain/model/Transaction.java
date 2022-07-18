package blockchain.model;

public class Transaction implements Runnable {
    private final Miner sender;
    private final Miner receiver;
    private final int value;
    private final BlockChain blockChain;

    public Transaction(Miner sender, Miner receiver, int value, BlockChain blockChain) {
        this.sender = sender;
        this.receiver = receiver;
        this.value = value;
        this.blockChain = blockChain;
    }

    @Override
    public void run() {
        if (sender.fromAccount(value))  {
            receiver.toAccount(value);
            blockChain.addTransaction(this);
        }
    }

    @Override
    public String toString() {
        return "Miner #" + this.sender.getId() + " sent " + value
                + " coins to miner #" + this.receiver.getId();
    }
}
