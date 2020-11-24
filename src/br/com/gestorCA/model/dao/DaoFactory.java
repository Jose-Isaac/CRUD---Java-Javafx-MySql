package br.com.gestorCA.model.dao;

import br.com.gestorCA.connection.ConnectionFactory;
import br.com.gestorCA.model.dao.impl.AssociationDaoJDBC;
import br.com.gestorCA.model.dao.impl.MinuteDaoJDBC;
import br.com.gestorCA.model.dao.impl.PartnerDaoJDBC;

public class DaoFactory {
    public static PartnerDao createPartnerdao() {return new PartnerDaoJDBC(ConnectionFactory.getConnection()); }

    public static AssociationDao createAssociationDao() {
        return new AssociationDaoJDBC(ConnectionFactory.getConnection());
    }

    public static MinuteDao createMinuteDao() {
        return new MinuteDaoJDBC(ConnectionFactory.getConnection());
    }
}
