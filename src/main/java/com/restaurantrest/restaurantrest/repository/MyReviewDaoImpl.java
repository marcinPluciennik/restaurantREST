package com.restaurantrest.restaurantrest.repository;

import com.restaurantrest.restaurantrest.dao.MyReviewDao;
import com.restaurantrest.restaurantrest.domain.MyReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MyReviewDaoImpl implements MyReviewDao {

    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(TempDaoImpl.class);

    @Autowired
    public MyReviewDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(String reviewText, int rating) {
        MyReview reviewToSave = new MyReview(reviewText, rating);
        String sql = "INSERT INTO reviews VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, reviewToSave.getReviewId(), reviewToSave.getRating(),
                reviewToSave.getReviewText());
    }

    @Override
    public void saveExistingReviews(List<MyReview> reviews) {
        try{
            String sql = "INSERT INTO reviews VALUES(?, ?, ?)";
            for (int i = 0; i < reviews.size(); i++) {
                jdbcTemplate.update(sql, reviews.get(i).getReviewId(),
                        reviews.get(i).getRating(), reviews.get(i).getReviewText());
            }
        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void updateMyReview(MyReview newMyReview) {
        String sql = "UPDATE reviews SET reviews.review_text=?, reviews.rating=? WHERE reviews.review_id=?";
        jdbcTemplate.update(sql, newMyReview.getReviewText(), newMyReview.getRating(), newMyReview.getReviewId());
    }

    @Override
    public List<MyReview> findAll() {
        List<MyReview> myReviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews";
        List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
        map.stream()
                .forEach(element -> myReviews.add(new MyReview(
                        Long.parseLong(String.valueOf(element.get("review_id"))),
                        String.valueOf(element.get("review_text")),
                        Integer.parseInt(String.valueOf(element.get("rating"))))));
        return myReviews;
    }

    @Override
    public boolean deleteMyReviewById(long id) {
        Optional<MyReview> myReviewOptional = findAll().stream()
                .filter(r -> r.getReviewId() == id)
                .findFirst();
        if (myReviewOptional.isPresent()){
            String sql = "DELETE FROM reviews WHERE reviews.review_id = ?";
            jdbcTemplate.update(sql, id);
            return true;
        }
        return false;
    }

    @Override
    public MyReview findMyReviewById(long id) {
        Optional<MyReview> myReviewOptional = findAll().stream()
                .filter(r -> r.getReviewId() == id)
                .findFirst();
        if (myReviewOptional.isPresent()) {
            String sql = "SELECT * FROM reviews WHERE reviews.review_id = ?";
            return jdbcTemplate.queryForObject(sql, (resultSet, column) -> new MyReview(
                    resultSet.getLong("review_id"),
                    resultSet.getString("review_text"),
                    resultSet.getInt("rating")), id);
        }
        return new MyReview();
    }
}
