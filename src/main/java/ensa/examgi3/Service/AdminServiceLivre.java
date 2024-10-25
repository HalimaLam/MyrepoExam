package ensa.examgi3.Service;

import ensa.examgi3.model.CD;
import ensa.examgi3.model.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import javax.ejb.Stateful;

@Stateful
public class AdminServiceLivre {

    @PersistenceContext
    private EntityManager em;

    public void ajouterLivre(Livre livre) {
        em.persist(livre);
    }

    public void supprimerLivre(Long livreId) {
        Livre livre = em.find(Livre.class, livreId);
        if (livre != null) {
            em.remove(livre);
        }
    }

    public void ajouterCD(CD cd) {
        em.persist(cd);
    }

    public void supprimerCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            em.remove(cd);
        }
    }

    public void modifierLivre(Livre livre) {
        em.merge(livre);
    }

    public void modifierCD(CD cd) {
        em.merge(cd);
    }
}
