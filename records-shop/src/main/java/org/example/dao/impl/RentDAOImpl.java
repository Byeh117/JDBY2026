package org.example.dao.impl;

import org.example.config.DBUtil;
import org.example.dao.RentDAO;
import org.example.exception.DataAccessException;
import org.example.model.Album;
import org.example.model.Rent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentDAOImpl implements RentDAO{

    @Override
    public void save(Rent rent) {
        String sql = "INSERT INTO rent (album_id, renter_name, loan_date, return_date) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, String.valueOf(rent.getAlbumId()));
            ps.setString(2, rent.getRenterName());
            ps.setDate(3, Date.valueOf(rent.getLoanDate()));
            ps.setDate(4, rent.getReturnDate() != null ? Date.valueOf(rent.getReturnDate()) : null);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) rent.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to save rent", e);
        }
    }

    @Override
    public Optional<Rent> findById(int id) {
        String sql = "SELECT * FROM rent WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to find rent " + id, e);
        }
    }

    @Override
    public List<Rent> findAll() {
        List<Rent> rent = new ArrayList<>();
        String sql = "SELECT * FROM rent ORDER BY id";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) rent.add(mapRow(rs));
        } catch (SQLException e) {
            throw new DataAccessException("Failed to list albums", e);
        }
        return rent;
    }

    @Override
    public void update(Rent rent) {
        String sql = "UPDATE rent SET album_id = ?, renter_name = ?, loan_date = ?, return_date = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, String.valueOf(rent.getAlbumId()));
            ps.setString(2, rent.getRenterName());
            ps.setDate(3, Date.valueOf(rent.getLoanDate()));
            ps.setDate(4, rent.getReturnDate() != null ? Date.valueOf(rent.getReturnDate()) : null);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to update rent " + rent.getId(), e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM rent WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete rent " + id, e);
        }
    }

    private Rent mapRow(ResultSet rs) throws SQLException {
        Rent r = new Rent();
        r.setId(rs.getInt("id"));
        r.setAlbumId(rs.getInt("album_id"));
        r.setRenterName(rs.getString("renter_name"));
        r.setLoanDate(rs.getDate("loan_date").toLocalDate());
        Date returnDate = rs.getDate("return_date");
        r.setReturnDate(returnDate != null ? returnDate.toLocalDate() : null);
        return r;
    }
}

