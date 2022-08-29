package blockchain.model;

import blockchain.util.SerializationUtils;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class BlockChain implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Block> blockChain;
    private final List<Transaction> transactions;
    private volatile transient int countOfZeros = 0;

    public static BlockChain getInstance(String filePath) {
        return (BlockChain) SerializationUtils.deserialize(filePath);
    }

    public BlockChain(List<Block> blockChain, List<Transaction> transactions) {
        this.blockChain = blockChain;
        this.transactions = transactions;
    }

    public String getAllTransactionsInfo() {
        return this.transactions.isEmpty()
                ? "no transactions"
                : this.transactions.stream()
                .map(Transaction::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public void clearTransactionsList() {
        this.transactions.clear();
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public int getCountOfZeros() {
        return countOfZeros;
    }

    public void setCountOfZeros(int countOfZeros) {
        this.countOfZeros = countOfZeros;
    }

    public Block getLast() {
        return blockChain.get(blockChain.size() - 1);
    }

    public List<Block> getChain() {
        return blockChain;
    }
}
