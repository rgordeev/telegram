package ru.tstu.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MessagesServiceImpl extends JdbcDaoSupport implements MessagesService {

    private DataSource dataSource;

    @Autowired
    public MessagesServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() {
        setDataSource(dataSource);
    }

    @Override
    public void save(TelegramMessage message) {
        getJdbcTemplate()
                .update("INSERT INTO MESSAGES(USER_ID, PAYLOAD) VALUES(?, ?)",
                        message.getUserId(),
                        message.getPayload());
    }

    @Override
    public List<String> listReplies() {
        return getJdbcTemplate().query("SELECT MESSAGE FROM REPLIES",
                (rs, rowNum) -> rs.getString("message"));
    }
}
