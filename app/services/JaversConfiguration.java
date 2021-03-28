package services;


import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.repository.sql.ConnectionProvider;
import org.javers.repository.sql.DialectName;
import org.javers.repository.sql.JaversSqlRepository;
import org.javers.repository.sql.SqlRepositoryBuilder;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JaversConfiguration {

    Connection dbConnection;
     {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl("jdbc:postgresql://localhost:5432/app_db");
        ds.setPassword("admin");
        ds.setUser("admin");
        try {
            dbConnection = ds.getConnection();
        } catch (SQLException e) {
            dbConnection = null;
        }
    }


    ConnectionProvider connectionProvider = new ConnectionProvider() {
        @Override
        public Connection getConnection() {
            return dbConnection;
        }
    };

    JaversSqlRepository sqlRepository = SqlRepositoryBuilder
            .sqlRepository()
            .withSchema("public")
            .withConnectionProvider(connectionProvider)
            .withDialect(DialectName.POSTGRES).build();
    Javers javers = JaversBuilder.javers().registerJaversRepository(sqlRepository).build();

    public Javers getJavers() {
        return javers;
    }
}
