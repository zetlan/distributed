package net.zetlan.examples.db;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BaseEntity)) {
            return false;
        }

        BaseEntity otherEntity = (BaseEntity) other;
        return Objects.equals(this.id, otherEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
