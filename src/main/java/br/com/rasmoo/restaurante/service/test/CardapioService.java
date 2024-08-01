package br.com.rasmoo.restaurante.service.test;

import br.com.rasmoo.restaurante.dao.CaradapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CardapioService {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManagerRasFood();
        cadastrarProdutoCardapio(entityManager, cadastrarCategoria(entityManager));

    }

    private static Categoria cadastrarCategoria(EntityManager entityManager){
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        Categoria pratoPrincipal = new Categoria("Prato principal");

        entityManager.getTransaction().begin();
        categoriaDao.cadastrar(pratoPrincipal);
        entityManager.getTransaction().commit();
        entityManager.clear();

        return pratoPrincipal;
    }

    private static void cadastrarProdutoCardapio(EntityManager entityManager, Categoria categoria){
        Cardapio ristoto = new Cardapio();
        ristoto.setNome("Risoto de frutos do mar");
        ristoto.setDescricao("Risoto acompanhado de lula, polvo e mariscos");
        ristoto.setDisponivel(true);
        ristoto.setCategoria(categoria);
        ristoto.setValor(BigDecimal.valueOf(88.50));

        Cardapio salmao = new Cardapio();
        salmao.setNome("Salmão ao molho de maracuja");
        salmao.setDescricao("Salmão grelhado ao molho de maracuja");
        salmao.setDisponivel(true);
        salmao.setCategoria(categoria);
        salmao.setValor(BigDecimal.valueOf(60.00));


        CaradapioDao caradapioDao = new CaradapioDao(entityManager);

        entityManager.getTransaction().begin();

        caradapioDao.cadastrar(ristoto);
        caradapioDao.cadastrar(salmao);
        //System.out.println("O prato consultado foi " + caradapioDao.consultarPorId(1));

        //System.out.println(caradapioDao.consultarPorId(2));

        caradapioDao.consultarTodos().forEach(elemento -> System.out.println("O prato consultado foi : " + elemento));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
