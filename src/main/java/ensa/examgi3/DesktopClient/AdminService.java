package ensa.examgi3.DesktopClient;

import javax.ejb.Stateful;

import ensa.exam1.model.Emprunt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class AdminService {

    @PersistenceContext
    private EntityManager em;

    public void ajouterEmprunt(Emprunt emprunt) {
        em.persist(emprunt);
    }

    public void modifierEmprunt(Emprunt emprunt) {
        em.merge(emprunt);
    }

    public void supprimerEmprunt(Long empruntId) {
        Emprunt emprunt = em.find(Emprunt.class, empruntId);
        if (emprunt != null) {
            em.remove(emprunt);
        }
    }

    public List<Emprunt> consulterEmprunts() {
        return em.createQuery("SELECT e FROM Emprunt e", Emprunt.class).getResultList();
    }
}

