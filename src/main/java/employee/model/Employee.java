package employee.model;

import java.util.Objects;

public class Employee {
    private long id;
    private String name;

    private Employee manager;

    /**
     * temporarilyMarked and marked are used to check cycle in DAG (directed acyclic graph)
     */
    private boolean temporarilyMarked;
    private boolean marked;

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!Employee.class.isInstance(obj)) {
            return false;
        }
        return this.getId() == ((Employee) obj).getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return String.valueOf(getId());
    }

    public boolean isTemporarilyMarked() {
        return temporarilyMarked;
    }

    public void setTemporarilyMarked(boolean temporarilyMarked) {
        this.temporarilyMarked = temporarilyMarked;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

}
