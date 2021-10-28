package banking.account.solver.rest.client;

import banking.commons.dto.SearchTransactionsResponseDTO;
import banking.commons.dto.TransactionDTO;
import banking.commons.dto.TransactionSearchInputDTO;
import banking.commons.dto.types.TransactionStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class TransactionRestClient {

    private RestTemplate transactionRestTemplate;

    public ResponseEntity<TransactionDTO> createTransaction(String fromIban, String toIban, Double amount) {

        String body = "{\"fromIban\":\"fromIban\",\"toIban\":\"toIban\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(APPLICATION_JSON));
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("fromIban", fromIban);
        urlVariables.put("toIban", toIban);

        ResponseEntity<TransactionDTO> responseEntity = transactionRestTemplate.
                postForEntity("http://localhost:8500/transactions/fromIban/{fromIban}/toIban/{toIban}" + amount, requestEntity, TransactionDTO.class, urlVariables);

        return responseEntity;

    }

    //TODO - get all the transactions id's with status status
    public ResponseEntity<List<String>> getAllTransactionsWithStatus(List<TransactionStatus> statusList) {

        TransactionSearchInputDTO searchDTO = new TransactionSearchInputDTO();

        searchDTO.setStatusList(statusList);

        //set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");

        //setting the request body
        Map<String, Object> map = new HashMap<>();
        map.put("statusList", statusList);

        HttpEntity<SearchTransactionsResponseDTO> exchange = transactionRestTemplate.
                postForEntity("http://localhost:8500/transactions/search-by-status", HttpMethod.POST, SearchTransactionsResponseDTO.class);

        return ResponseEntity.ok(exchange.getBody().getIds());

    }

}
