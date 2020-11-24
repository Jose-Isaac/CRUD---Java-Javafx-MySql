package br.com.gestorCA.model.dao;

import br.com.gestorCA.model.entities.Association;
import br.com.gestorCA.model.entities.Partner;

import java.util.List;

public interface PartnerDao {
    void insert(Partner partner);
    void update(Partner partner);
    void deleteById(int id);
    Partner findById(int id);
    Partner findByUsername(String username);
    Partner findByCpf(String cpf);
    List<Partner> findAll();
    List<Partner> findByAssociation(int associationId);
}
