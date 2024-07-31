package br.com.rasmoo.restaurante.service.test;

import br.com.rasmoo.restaurante.dao.PratoDao;
import br.com.rasmoo.restaurante.entity.Prato;
import br.com.rasmoo.restaurante.util.JPAUtil;

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

        EntityManager entityManager = JPAUtil.getEntityManagerRasFood();
        PratoDao pratoDao = new PratoDao(entityManager);

        entityManager.getTransaction().begin();
        pratoDao.cadastrar(ristoto);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
