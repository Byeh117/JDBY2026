package org.example.dao.impl;

import org.example.config.DBUtil;
import org.example.dao.AlbumDAO;
import org.example.exception.DataAccessException;
import org.example.model.Album;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AlbumDAOImpl implements AlbumDAO {

    @Override
    public void save(Album album) {
        String sql = "INSERT INTO albums (title, catalogue_id, artist_id, available) " +
                "VALUES (?, ?, ?, ?)"; //protects from SQL injection

        try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, album.getTitle());
            ps.setString(2, album.getCatalogueId());
            ps.setString(3, String.valueOf(album.getArtistId()));
            ps.setBoolean(4, album.isAvailable());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) album.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to save album", e);
        }
    }

    @Override
    public Optional<Album> findById(int id) {
        String sql = "SELECT * FROM albums WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to find album " + id, e);
        }
    }

    @Override
    public Optional<Album> findByCatalogueId(String catalogueId) {
        String sql = "SELECT * FROM albums WHERE catalogue_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, catalogueId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to find album with catalogue id " + catalogueId, e);
        }
    }

    @Override
    public List<Album> findAll() {
        List<Album> albums = new ArrayList<>();
        String sql = "SELECT * FROM albums ORDER BY id";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) albums.add(mapRow(rs));
        } catch (SQLException e) {
            throw new DataAccessException("Failed to list albums", e);
        }
        return albums;
    }

    @Override
    public void update(Album album) {
        String sql = "UPDATE albums SET title = ?, catalogue_id = ?, artist_id = ?, available = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, album.getTitle());
            ps.setString(2, album.getCatalogueId());
            ps.setString(3, String.valueOf(album.getArtistId()));
            ps.setBoolean(4, album.isAvailable());
            ps.setString(5, String.valueOf(album.getId()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to update album " + album.getId(), e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM albums WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete album " + id, e);
        }
    }

    private Album mapRow(ResultSet rs) throws SQLException {
        Album a = new Album();
        a.setId(rs.getInt("id"));
        a.setTitle(rs.getString("title"));
        a.setCatalogueId(rs.getString("catalogue_id"));
        a.setArtistId(rs.getInt("artist_id"));
        a.setAvailable(rs.getBoolean("available"));
        return a;
    }

}
