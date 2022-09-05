BLOCKCHAIN SIMULATOR

It is a multi-threaded application that simulates a simple blockchain system.
Transactions between system participants and the generation of chain blocks take place in parallel.
By default every miner gets 100 coins on his account.
If senders coins not enough the transaction will not occure.
After each generated block, the miner receives a certain amount of coins to his account as a payment for a job.
Information about current transactions that have created from the moment of previous block generation, writes to the last block to the field "blockData". Validation of blocks compares previous block hash and appropriate current block field. Also, if the block is generated for too long, such an operation will be canceled.

For the test purpose created a pool of 100 miners which take participate in providing transactions and blocks generation.
You can change a size of miners pool, a chain size,  a maximal transaction value, and count of executor threads manually at MiningService.java constants.
