package banking.account.solver.scheduler;

import banking.account.solver.rest.client.AccountCurrentRestClient;
import banking.account.solver.rest.client.TransactionRestClient;
import banking.commons.dto.types.TransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TransactionExecuterScheduler {

    @Autowired
    private AccountCurrentRestClient restClient;

    @Autowired
    private TransactionRestClient transactionRestClient;

//    public void processTransactions(){
//        log.info("pick transaction to process ...");
//
//        //get all transactions not executed
//        List<String> transactionIdsToProcess = transactionRestClient.getAllTransactionsWithStatus(List.of(TransactionStatus.NEW));
//
//        //for each transaction execute it
//        for (String transactionId: transactionIdsToProcess) {
//            transactionRestClient.executeTransaction(transactionId);
//        }
//    }

}
