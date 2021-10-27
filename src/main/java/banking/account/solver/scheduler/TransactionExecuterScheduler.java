package banking.account.solver.scheduler;

import banking.account.solver.rest.client.AccountCurrentRestClient;
import banking.account.solver.rest.client.TransactionRestClient;
import banking.commons.dto.types.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TransactionExecuterScheduler {

    @Autowired
    private AccountCurrentRestClient restClient;

    @Autowired
    private TransactionRestClient transactionRestClient;

    public void pocessTransactions(){
        log.info("pick transaction to process ...");

        //get all transactions not executed
        List<String> transactionIdsToProcess = transactionRestClient.getAllTransactionsWithStatus(List.of(TransactionStatus.NEW));

        //for each transaction execute it
        for (String transactionId: transactionIdsToProcess) {
            transactionRestClient.executeTransaction(transactionId);
        }
    }

}
