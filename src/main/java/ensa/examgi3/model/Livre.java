package ensa.examgi3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String auteur;
    private boolean disponible;

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean b) {
        this.disponible = disponible;
    }
}

