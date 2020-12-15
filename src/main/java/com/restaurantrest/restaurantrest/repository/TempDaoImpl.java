package com.restaurantrest.restaurantrest.repository;

import com.restaurantrest.restaurantrest.dao.TempDao;
import com.restaurantrest.restaurantrest.domain.Temp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class TempDaoImpl implements TempDao {

    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(TempDaoImpl.class);


    @Autowired
    public TempDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveTemp(long temp_id, LocalDate date, double temp) {
        try{
            Temp tempToSave = new Temp(temp_id, date, temp);
            String sql = "INSERT INTO temp_prague VALUES(?, ?, ?)";
            jdbcTemplate.update(sql, tempToSave.getTempId(), tempToSave.getDate(), tempToSave.getTemp());
        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void updateTemp(Temp newTemp) {
        String sql = "UPDATE temp_prague SET temp_prague.date=?, temp_prague.temp=? WHERE temp_prague.temp_id=?";
        jdbcTemplate.update(sql, newTemp.getDate(), newTemp.getTemp(), newTemp.getTempId());
    }

    @Override
    public List<Temp> findAll() {
        List<Temp> tempList = new ArrayList<>();
        String sql = "SELECT * FROM temp_prague";
        List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
        map.stream()
                .forEach(element -> tempList.add(new Temp(
                        Long.parseLong(String.valueOf(element.get("temp_id"))),
                        LocalDate.parse(String.valueOf(element.get("date"))),
                        Double.parseDouble(String.valueOf(element.get("temp"))))));
        return tempList;
    }

    @Override
    public boolean deleteTemp(long id) {
        Optional<Temp> tempOptional = findAll().stream()
                .filter(t -> t.getTempId() == id)
                .findFirst();
        if (tempOptional.isPresent()){
            String sql = "DELETE FROM temp_prague WHERE temp_prague.temp_id = ?";
            jdbcTemplate.update(sql, id);
            return true;
        }
        return false;
    }

    @Override
    public Temp findTempByDate(LocalDate date) {
        Optional<Temp> tempOptional = findAll().stream()
                .filter(t -> t.getDate().equals(date))
                .findFirst();
        if (tempOptional.isPresent()) {
            String sql = "SELECT * FROM temp_prague WHERE temp_prague.date = ?";
            return jdbcTemplate.queryForObject(sql, (resultSet, column) -> new Temp(
                    resultSet.getLong("temp_id"),
                    LocalDate.parse(resultSet.getString("date")),
                    resultSet.getDouble("temp")), date);
        }
        return new Temp();
    }
}
