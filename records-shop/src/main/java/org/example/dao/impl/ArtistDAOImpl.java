package org.example.dao.impl;

import org.example.config.DBUtil;
import org.example.dao.ArtistDAO;
import org.example.exception.DataAccessException;
import org.example.model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistDAOImpl implements ArtistDAO{

    @Override
    public void save(Artist artist) {
        String sql = "INSERT INTO artists (name) VALUES (?)";

        try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, artist.getName());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) artist.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to save artist", e);
        }
    }

    @Override
    public Optional<Artist> findById(int id) {
        String sql = "SELECT * FROM artists WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to find artist " + id, e);
        }
    }

    @Override
    public List<Artist> findAll() {
        List<Artist> artists = new ArrayList<>();
        String sql = "SELECT * FROM artists ORDER BY id";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) artists.add(mapRow(rs));
        } catch (SQLException e) {
            throw new DataAccessException("Failed to list artists", e);
        }
        return artists;
    }

    @Override
    public void update(Artist artist) {
        String sql = "UPDATE artists SET name = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, artist.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to update artist " + artist.getId(), e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM artists WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete artist " + id, e);
        }
    }

    private Artist mapRow(ResultSet rs) throws SQLException {
        Artist ar = new Artist();
        ar.setId(rs.getInt("id"));
        ar.setName(rs.getString("name"));
        return ar;
    }
}
