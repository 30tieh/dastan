package models.entities.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by leonard on 17.08.15.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    @Column private String name;
    @Column private int rating;
    @Column(unique = true) private String username;
    @Column private String password;
    @Column private int balance;

    public User(final String name, final int rating, final String username, final String password, final int balance) {
        this.name = name;
        this.rating = rating;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public User(final String name, final String username, final String password) {
        this.name = name;
        this.rating = 0;
        this.username = username;
        this.password = password;
        this.balance = 0;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }
}
