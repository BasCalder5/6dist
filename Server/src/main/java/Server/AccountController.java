package Server;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;


@RestController
public class AccountController {

    private final AccountRepo repo;
    private final AccountModelAssembler assembler;

    AccountController(AccountRepo repo, AccountModelAssembler assembler) {
        this.repo = repo;
        this.assembler = assembler;
    }

    @GetMapping("/accounts")
    CollectionModel<EntityModel<Account>> all() {
        List<EntityModel<Account>> accounts =  repo.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(accounts,
                linkTo(methodOn(AccountController.class).all()).withSelfRel());
    }

    @PostMapping("/accounts")
    Account newAccount(@RequestBody Account newAccount) {
        return repo.save(newAccount);
    }

    @GetMapping("/accounts/{id}")
    EntityModel<Account> one(@PathVariable Long id) {
        Account account =  repo.findById(id).orElseThrow(() -> new AccountNotFoundException(id));

        return assembler.toModel(account);
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
    ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        repo.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
