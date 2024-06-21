package com.example.HibernateAssigment.Dao;

import com.example.HibernateAssigment.Artist;
import com.example.HibernateAssigment.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ArtistDao {
    public void saveArtist(Artist artist) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(artist);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateArtist(Artist artist) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(artist);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                e.printStackTrace();
            }
        }
    }

    public Artist gerArtistById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Artist.class, id);
        }
    }

    public List<Artist> getAllArtists() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from artist", Artist.class).list();
        }
    }

    public List<Artist> getAllArtistsFormedAfterYear(int year) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Artist where yearFormed > :year", Artist.class)
                    .setParameter("year", year).list();
        }
    }

    public List<Artist> getArtistsByType(String type) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Artist where type = :type", Artist.class)
                    .setParameter("type", type)
                    .list();
        }
    }

    public void deleteArtist(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Artist artist = session.get(Artist.class, id);
            if (artist != null) {
                session.delete(artist);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
