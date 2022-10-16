package blockchain.util;

public abstract class Settings {
    public static final int MINERS_LIMIT = 100;
    public static final int MAX_TRANSACTION_VALUE = 50;
    public static int CHAIN_STOP_LIMIT = 30;
    public static final String BLOCKCHAIN_FILE_PATH = "src/main/resources/chain.ser";
    public static final int MIN_GENERATION_TIME = 150;
    public static final int MAX_GENERATION_TIME = 250;
    public static final int MINING_PAYMENT = 100;
}
