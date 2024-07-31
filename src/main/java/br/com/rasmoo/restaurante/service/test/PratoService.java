package br.com.rasmoo.restaurante.service.test;

import br.com.rasmoo.restaurante.entity.Prato;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class PratoService {
    public static void main(String[] args) {

        Prato ristoto = new Prato();
        ristoto.setNome("Risoto de frutos do mar");
        ristoto.setDescricao("Risoto acompanhado de lula, polvo e mariscos");
        ristoto.setDisponivel(true);
        ristoto.setValor(BigDecimal.valueOf(88.50));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rasFood");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(ristoto);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
