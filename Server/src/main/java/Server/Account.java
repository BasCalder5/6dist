package Server;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Account {

    private @Id @GeneratedValue Long id;
    private String name;
    private long balance;

    public Account() {
    }

    public Account(String name, long balance) {
        this.name = name;
        this.balance = balance;
    }
}
