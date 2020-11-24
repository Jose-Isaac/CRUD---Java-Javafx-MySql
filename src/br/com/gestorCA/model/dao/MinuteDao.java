package br.com.gestorCA.model.dao;

import br.com.gestorCA.model.entities.Minute;

import java.util.List;

public interface MinuteDao {
    void insert(Minute minute);
    List<Minute> findByAssociation(int associationId);
}
