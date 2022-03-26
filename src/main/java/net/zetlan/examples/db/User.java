package net.zetlan.examples.db;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import java.security.Principal;

@Entity
public class User extends BaseEntity implements Principal {
    private String name = RandomStringUtils.randomAlphabetic(12).toLowerCase();

    @Override
    public String getName() {
        return this.name.toLowerCase();
    }

    public void setName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException("Username cannot be blank");
        }
        this.name = name;
    }
}
