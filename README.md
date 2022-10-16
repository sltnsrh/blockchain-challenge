BLOCKCHAIN SIMULATOR

It is a multithreaded application that simulates a simple blockchain system.
Transactions between system participants and the generation of chain blocks take place in parallel.
By default, every miner gets 100 coins on his account.
If senders coins not enough the transaction will not occur.
After each generated block, the miner receives a certain amount of coins to his account as a payment for a job.
Information about current transactions that have created from the moment of previous block generation, writes to the 
last block to the field "blockData". Validation of blocks compares previous block hash and appropriate current block field. 
Also, if the block is generated for too long, such an operation will be canceled.

For the test purpose created a pool of 100 miners which take participate in providing transactions and blocks generation.
You can change a size of miners pool, a maximal transaction value, a chain size, and count of executor threads manually 
at MiningService.java constants.

In Settings class your can setup:
- MINERS_LIMIT - max size of miners pool;
- MAX_TRANSACTION_VALUE - max limit of transactions value in blockchain system;
- CHAIN_STOP_LIMIT - max limit of generating blocks in a chain;
- BLOCKCHAIN_FILE_PATH - path to the file with a serialized data;
- MIN_GENERATION_TIME - min time needed for a block generation;
- MAX_GENERATION_TIME - max allowed time for a block generation;
- MINING_PAYMENT - a value of payment for a miner's work;
