package com.cooperativajsf.entity;
// Generated 2/05/2021 08:07:30 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Coop.Moneda generated by hbm2java
 */
@Entity
@Table(name = "COOP$_MONEDA",
         schema = "SYSTEM"
)
public class Coop$Moneda implements java.io.Serializable {

    @Id
    @Column(name = "IDMONEDA", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AutoIdMoneda")
    private BigDecimal idmoneda;

    @Column(name = "DESCRIPCION", length = 5)
    private String descripcion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "coop$Moneda")
    private Set coop$Cuentas = new HashSet(0);

    public Coop$Moneda() {
    }

    public Coop$Moneda(BigDecimal idmoneda) {
        this.idmoneda = idmoneda;
    }

    public Coop$Moneda(BigDecimal idmoneda, String descripcion, Set coop$Cuentas) {
        this.idmoneda = idmoneda;
        this.descripcion = descripcion;
        this.coop$Cuentas = coop$Cuentas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.idmoneda);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coop$Moneda other = (Coop$Moneda) obj;
        if (!Objects.equals(this.idmoneda, other.idmoneda)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coop$Moneda{" + "idmoneda=" + idmoneda + ", descripcion=" + descripcion + ", coop$Cuentas=" + coop$Cuentas + '}';
    }

    public BigDecimal getIdmoneda() {
        return this.idmoneda;
    }

    public void setIdmoneda(BigDecimal idmoneda) {
        this.idmoneda = idmoneda;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set getCoop$Cuentas() {
        return this.coop$Cuentas;
    }

    public void setCoop$Cuentas(Set coop$Cuentas) {
        this.coop$Cuentas = coop$Cuentas;
    }

}