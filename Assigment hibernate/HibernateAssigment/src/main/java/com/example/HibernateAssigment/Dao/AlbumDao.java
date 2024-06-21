package com.example.HibernateAssigment.Dao;

import com.example.HibernateAssigment.Album;
import com.example.HibernateAssigment.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AlbumDao {

    public void saveAlbum(Album album) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(album);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateAlbum(Album album) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(album);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteAlbum(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Album album = session.get(Album.class, id);
            if (album != null) {
                session.delete(album);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Album getAlbumById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Album.class, id);
        }
    }

    public List<Album> getAlbumsByArtistId(Long artistId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Album where artist.id = :artistId", Album.class)
                    .setParameter("artistId", artistId)
                    .list();
        }
    }

    public List<Album> getAlbumsByRecordLabel(String recordLabel) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Album where recordLabel = :recordLabel", Album.class)
                    .setParameter("recordLabel", recordLabel)
                    .list();
        }
    }
}