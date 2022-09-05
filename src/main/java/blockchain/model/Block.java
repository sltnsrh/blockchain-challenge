package blockchain.model;

import java.io.Serializable;

public class Block implements Serializable {
    private int id;
    private long minerId;
    private long timeStamp;
    private int magic;
    private String prevHash;
    private String hash;
    private String blockData;
    private long generationTime;
    private int requiredZerosInHash;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getMagic() {
        return this.magic;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setBlockData(String blockData) {
        this.blockData = blockData;
    }

    public void setGenerationTime(long generationTime) {
        this.generationTime = generationTime;
    }

    public long getGenerationTime() {
        return generationTime;
    }

    public long getMinerId() {
        return minerId;
    }

    public void setMinerId(long minerId) {
        this.minerId = minerId;
    }

    public int getRequiredZerosInHash() {
        return requiredZerosInHash;
    }

    public void setRequiredZerosInHash(int requiredZerosInHash) {
        this.requiredZerosInHash = requiredZerosInHash;
    }

    public synchronized void printBlockInfo(BlockChain blockChain) {
        String zeroStatus = "N stays the same" + System.lineSeparator();
        int blockChainCountOfZeros = blockChain.getCountOfZeros();
        if (this.requiredZerosInHash > blockChainCountOfZeros) {
            zeroStatus = "N was decreased to "
                    + blockChainCountOfZeros + System.lineSeparator();
        }
        if (this.requiredZerosInHash < blockChainCountOfZeros) {
            zeroStatus = "N was increased to "
                    + blockChainCountOfZeros + System.lineSeparator();
        }
        System.out.println(this + System.lineSeparator() + zeroStatus);
    }

    @Override
    public String toString() {
        return "Block:" + System.lineSeparator()
                + "Created by miner # " + this.minerId + System.lineSeparator()
                + "miner#" + this.minerId + " gets 100 VC" + System.lineSeparator()
                + "Id: " + this.id + System.lineSeparator()
                + "Timestamp: " + this.timeStamp + System.lineSeparator()
                + "Magic number: " + this.magic + System.lineSeparator()
                + "Hash of the previous block:" + System.lineSeparator()
                + this.prevHash + System.lineSeparator()
                + "Hash of the block:" + System.lineSeparator()
                + this.hash + System.lineSeparator()
                + "Block data: " + System.lineSeparator()
                + this.blockData + System.lineSeparator()
                + "Block was generating for " + this.generationTime + " milliseconds";
    }
}
