package Server;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private final AccountRepo repo;

    AccountController(AccountRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/accounts")
    List<Account> all() {
        return repo.findAll();
    }

    @PostMapping("/accounts")
    Account newAccount(@RequestBody Account newAccount) {
        return repo.save(newAccount);
    }

    @GetMapping("/accounts/{id}")
    Account one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    @PutMapping("/accounts/{id}")
    Account replaceAccount(@RequestBody Account newAccount, @PathVariable Long id) {
        return repo.findById(id).map(account -> {
            account.setName(newAccount.getName());
            account.setBalance(newAccount.getBalance());
            return repo.save(account);
        })
        .orElseGet(() -> {
            newAccount.setId(id);
            return repo.save(newAccount);
        });
    }

    @DeleteMapping("/accounts/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
