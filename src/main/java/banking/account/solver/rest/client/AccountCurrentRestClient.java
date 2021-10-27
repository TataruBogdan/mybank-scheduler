package banking.account.solver.rest.client;

import banking.commons.dto.AccountCurrentDTO;
import banking.commons.dto.AmountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountCurrentRestClient {

    @Autowired
    private RestTemplate currentRestTemplate;

//    private ApplicationProperties applicationProperties;

    public AccountCurrentDTO getAccountCurrentByIban(String iban) {
        AccountCurrentDTO accountCurrentDTO = currentRestTemplate.getForObject(applicationProperties.getAccountCurrentUr() + iban,
                AccountCurrentDTO.class);

        return accountCurrentDTO;
    }

    public ResponseEntity<AccountCurrentDTO> debitAccountCurrent(String iban, AmountDTO amountDTO) {

                                                            // ???
        String body = "{\"iban\":\"CURR-\",\"amountDTO\":\"amountDTO\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<AccountCurrentDTO> accountCurrentDebitDTO = currentRestTemplate.exchange(applicationProperties.getAccountCurrentDebitUrl() + iban, HttpMethod.PATCH, requestEntity, AccountCurrentDTO.class);

        return accountCurrentDebitDTO;

    }
}
