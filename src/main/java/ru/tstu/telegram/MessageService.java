package ru.tstu.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class MessageService extends JdbcDaoSupport {
    private DataSource dataSource;

    @Autowired
    public MessageService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() {
        setDataSource(dataSource);
    }

    public void saveMessage(TelegramMessage message) {

        getJdbcTemplate()
                .update("INSERT INTO MESSAGES(USER_ID, MESSAGE) VALUES(?, ?)"
                , message.getUserId(), message.getMessage());

    }
}
