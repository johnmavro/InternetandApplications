package internet.and.applications.data;

import org.apache.commons.dbcp2.BasicDataSource;
import org.restlet.resource.ResourceException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Date;
import java.util.Optional;
import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.*;

public class DataAccess {

    private static final Object[] EMPTY_ARGS = new Object[0];

    private static final int MAX_TOTAL_CONNECTIONS = 16;
    private static final int MAX_IDLE_CONNECTIONS = 8;

    private JdbcTemplate jdbcTemplate;

    public void setup(String driverClass, String url, String user, String pass) throws SQLException {

        //initialize the data source
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(driverClass);
        bds.setUrl(url);
        bds.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        bds.setMaxIdle(MAX_IDLE_CONNECTIONS);
        bds.setUsername(user);
        bds.setPassword(pass);
        bds.setValidationQuery("SELECT 1");
        bds.setTestOnBorrow(true);
        bds.setDefaultAutoCommit(true);

        //check that everything works OK
        bds.getConnection().close();

        //initialize the jdbc template utility
        jdbcTemplate = new JdbcTemplate(bds);
    }

    public void accessDataCheck() throws DataAccessException {
        try {
            jdbcTemplate.query("select 1", ResultSet::next);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Optional<List<Author>> searchquery(String disease){
      String sqlQuery="select m.authors ,COUNT(*) as number from "+
                      "(select SUBSTRING_INDEX(authors,';',1) as authors from Metadata"+
                      " where title like ?) as m "+
                      "group by m.authors "+
                      "order by number desc "+
                      "limit 11";
      Object[] sqlParams = new Object[]{
        "%"+disease+"%"
      };
      try{
        List<Author> tmp = jdbcTemplate.query(sqlQuery, sqlParams,new AuthorMapper());
        return Optional.of(tmp);
      }catch(NoSuchElementException e){
        System.out.println("wrong");
        return Optional.empty();
      }
    }
  }
