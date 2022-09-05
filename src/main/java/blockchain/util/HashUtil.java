package blockchain.util;

import blockchain.model.Block;
import blockchain.model.BlockChain;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.stream.IntStream;

public final class HashUtil {

    public static void setMagicAndHash(Block block, BlockChain blockChain, int maxGenerationTime) {
        long startTime = System.currentTimeMillis();
        String hash = "";
        int magic = 0;
        while (notEnoughZeros(hash, blockChain.getCountOfZeros())) {
            if (System.currentTimeMillis() - startTime > maxGenerationTime) {
                magic = 0;
                hash = "";
                break;
            }
            magic = (int) (Math.random() * 100000000);
            hash = applySha256(block.getId()
                    + block.getTimeStamp()
                    + block.getPrevHash()
                    + block.getMinerId()
                    + block.getRequiredZerosInHash()
                    + magic
            );
        }
        block.setMagic(magic);
        block.setHash(hash);
    }

    private static boolean notEnoughZeros(String hash, int countOfZeros) {
        if (hash.isEmpty()) {
            return true;
        }
        long count = IntStream.range(0, countOfZeros)
                .filter(i -> hash.charAt(i) == '0')
                .count();
        return count != countOfZeros;
    }

    private static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
