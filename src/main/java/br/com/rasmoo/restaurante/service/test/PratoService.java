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

        Prato salmao = new Prato();
        salmao.setNome("Salmão ao molho de maracuja");
        salmao.setDescricao("Salmão grelhado ao molho de maracuja");
        salmao.setDisponivel(true);
        salmao.setValor(BigDecimal.valueOf(60.00));

        EntityManager entityManager = JPAUtil.getEntityManagerRasFood();
        PratoDao pratoDao = new PratoDao(entityManager);

        entityManager.getTransaction().begin();
        pratoDao.cadastrar(ristoto);
        pratoDao.cadastrar(salmao);
        System.out.println("O prato consultado foi " + pratoDao.consultar(1));

        pratoDao.excluir(salmao);
        System.out.println(pratoDao.consultar(2));

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
