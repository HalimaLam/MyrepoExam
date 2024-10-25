package ensa.examgi3.Service;

import ensa.exam1.model.CD;
import ensa.exam1.model.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class EmpruntService {

    @PersistenceContext
    private EntityManager em;

    public List<CD> listerCDDisponibles() {
        return em.createQuery("SELECT cd FROM CD cd WHERE cd.disponible = true", CD.class)
                .getResultList();
    }

    public void emprunterLivre(Long livreId) {
        Livre livre = em.find(Livre.class, livreId);
        if (livre != null && livre.isDisponible()) {
            livre.setDisponible(false);
            em.merge(livre);
        }
    }

    public void retournerLivre(Long livreId) {
        Livre livre = em.find(Livre.class, livreId);
        if (livre != null && !livre.isDisponible()) {
            livre.setDisponible(true);
            em.merge(livre);
        }
    }

    public void emprunterCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && cd.isDisponible()) {
            cd.setDisponible(false);
            em.merge(cd);
        }
    }

    public void retournerCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && !cd.isDisponible()) {
            cd.setDisponible(true);
            em.merge(cd);
        }
    }
}

