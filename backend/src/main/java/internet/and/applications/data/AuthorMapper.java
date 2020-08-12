package internet.and.applications.data;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {

      Author author = new Author();
      author.setname(rs.getString("authors"));
      author.setnumber(rs.getInt("number"));
      return author;

    }
}
