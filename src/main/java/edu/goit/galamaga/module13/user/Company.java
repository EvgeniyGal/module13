package edu.goit.galamaga.module13.user;

import java.util.Objects;

public class Company {

    private String name;
    private String catchPhrase;
    private String bs;

    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        return this.name.equals(((Company) o).name) && this.catchPhrase.equals(((Company) o).catchPhrase) &&
                this.bs.equals(((Company) o).bs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCatchPhrase(), getBs());
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", catchPhrase='" + catchPhrase + '\'' +
                ", bs='" + bs + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
