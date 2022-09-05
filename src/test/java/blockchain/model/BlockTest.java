package blockchain.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlockTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void printBlockInfoAsserDecreaseZeroCount() {
        Block block = new Block();
        block.setRequiredZerosInHash(5);
        BlockChain blockChain = new BlockChain("path", new ArrayList<>(), new ArrayList<>());
        blockChain.setCountOfZeros(4);
        block.printBlockInfo(blockChain);
        Assertions.assertTrue(outputStreamCaptor.toString()
                .trim().contains("was decreased"));
    }

    @Test
    void printBlockInfoAsserIncreaseZeroCount() {
        System.setOut(new PrintStream(outputStreamCaptor));
        Block block = new Block();
        block.setRequiredZerosInHash(3);
        BlockChain blockChain = new BlockChain("path", new ArrayList<>(), new ArrayList<>());
        blockChain.setCountOfZeros(4);
        block.printBlockInfo(blockChain);
        Assertions.assertTrue(outputStreamCaptor.toString()
                .trim().contains("was increased"));
    }

    @Test
    void printBlockInfoAsserStaySame() {
        System.setOut(new PrintStream(outputStreamCaptor));
        Block block = new Block();
        block.setRequiredZerosInHash(4);
        BlockChain blockChain = new BlockChain("path", new ArrayList<>(), new ArrayList<>());
        blockChain.setCountOfZeros(4);
        block.printBlockInfo(blockChain);
        Assertions.assertTrue(outputStreamCaptor.toString()
                .trim().contains("stays the same"));
    }

    @Test
    void toStringAssertMatch() {
        Block block = new Block();
        block.setId(1);
        block.setPrevHash("prev hash");
        block.setHash("hash");
        block.setMagic(1234);
        block.setMinerId(1);
        block.setRequiredZerosInHash(4);
        block.setBlockData("transaction data");
        String actual = block.toString();
        Assertions.assertEquals("Block:\n" +
                "Created by miner # 1\n" +
                "miner#1 gets 100 VC\n" +
                "Id: 1\n" +
                "Timestamp: 0\n" +
                "Magic number: 1234\n" +
                "Hash of the previous block:\n" +
                "prev hash\n" +
                "Hash of the block:\n" +
                "hash\n" +
                "Block data: \n" +
                "transaction data\n" +
                "Block was generating for 0 milliseconds",
                actual
        );
    }
}
