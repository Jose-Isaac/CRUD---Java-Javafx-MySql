package br.com.gestorCA.model.dao;

import br.com.gestorCA.model.entities.Association;

public interface AssociationDao {
    Association findById(int id);
    Association findByCnpj(String cnpj);
    void insert(Association association);
}
