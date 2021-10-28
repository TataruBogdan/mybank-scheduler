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


    public AccountCurrentDTO getAccountCurrentByIban(String iban) {
        AccountCurrentDTO accountCurrentDTO = currentRestTemplate.getForObject("http://localhost:8200/accounts-currents"  + iban,
                AccountCurrentDTO.class);

        return accountCurrentDTO;
    }

    public ResponseEntity<AccountCurrentDTO> creditAccountCurrent(String iban, Double amount) {

        AmountDTO amountDTO = new AmountDTO();
        amountDTO.setAmount(amount);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AmountDTO> requestEntity = new HttpEntity<>(amountDTO, headers);

        ResponseEntity<AccountCurrentDTO> accountCurrentCreditDTO = currentRestTemplate.exchange("http://localhost:8200/account-current/credit" + iban, HttpMethod.PATCH, requestEntity, AccountCurrentDTO.class);

        return accountCurrentCreditDTO;

    }



    public ResponseEntity<AccountCurrentDTO> debitAccountCurrent(String iban, Double amount) {

                                                            // ???
        String body = "{\"iban\":\"CURR-\",\"amountDTO\":\"amountDTO\"}";
        AmountDTO amountDTO = new AmountDTO();
        amountDTO.setAmount(amount);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AmountDTO> requestEntity = new HttpEntity<>(amountDTO, headers);

        ResponseEntity<AccountCurrentDTO> accountCurrentDebitDTO = currentRestTemplate.exchange("http://localhost:8200/account-currents/debit" + iban, HttpMethod.PATCH, requestEntity, AccountCurrentDTO.class);

        return accountCurrentDebitDTO;

    }
}
