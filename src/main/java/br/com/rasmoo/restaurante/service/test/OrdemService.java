package br.com.rasmoo.restaurante.service.test;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.entity.Cliente;
import br.com.rasmoo.restaurante.entity.Endereco;
import br.com.rasmoo.restaurante.entity.Ordem;
import br.com.rasmoo.restaurante.entity.OrdensCardapio;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;

public class OrdemService {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManagerRasFood();

        entityManager.getTransaction().begin();

        CargaDeDadosUtil.cadastarCategorias(entityManager);
        CargaDeDadosUtil.cadastrarProdutosCardapio(entityManager);

        CardapioDao cardapioDao = new CardapioDao(entityManager);
        ClienteDao clienteDao = new ClienteDao(entityManager);
        OrdemDao ordemDao = new OrdemDao(entityManager);


        Endereco endereco = new Endereco("0000000", "rua aaaaa", "casa", "PR", "beltrao");
        Cliente matheus = new Cliente("0000000", "matheus");
        matheus.addEndereco(endereco);

        Ordem ordem = new Ordem(matheus);
        ordem.addOrdensCardapio(new OrdensCardapio(ordem, cardapioDao.consultarPorId(1), 2));

        clienteDao.cadastrar(matheus);
        ordemDao.cadastrar(ordem);
        System.out.println(ordem.getOrdensCardapioList());
        ordemDao.consultarItensMaisVendidos().forEach(item  -> System.out.println("Item: " + item[0]+ "\t-Quantidade: " + item[1]));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
