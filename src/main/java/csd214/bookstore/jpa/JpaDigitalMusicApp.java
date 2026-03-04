package csd214.bookstore.jpa;

import csd214.bookstore.entities.BookEntity;
import csd214.bookstore.entities.DigitalMusicEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.UUID;

public class JpaDigitalMusicApp {
    public static void main(String[] args) {
        // 1. Initialize the Engine
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore-pu");
        EntityManager em = emf.createEntityManager();

        try {
            // --- CREATE ---
            System.out.println("\n[Step 1] Creating a new DigitalMusic...");
            em.getTransaction().begin();
            // String name, double price, String make, String model, int year, int mileage, double towingCapacity
            DigitalMusicEntity myDigitalMusic = new DigitalMusicEntity(
                    53.00,
                    "Back to Black",
                    "Amy Winehouse",
                    "Contemporary R&B",
                    2006,
                    "local-library.xwz/files/amy-winehouse/keNkPch2S3ThT8Lq",
                    "Digital Music: Back to Black");
            em.persist(myDigitalMusic); // Tells Hibernate to save the object
            em.getTransaction().commit();
            System.out.println("DigitalMusic saved with Database ID: " + myDigitalMusic.getId());

            // --- READ (List All) ---
            listDigitalMusics(em, "[Step 2] Current Inventory:");

            // --- UPDATE (Find & Edit) ---
            System.out.println("\n[Step 3] Editing DigitalMusic Price...");
            em.getTransaction().begin();

            // We use the ID to find the specific record
            DigitalMusicEntity digitalMusicToEdit = em.find(DigitalMusicEntity.class, myDigitalMusic.getId());
            if (digitalMusicToEdit != null) {
                digitalMusicToEdit.setPrice(49.99); // Change the Java field
                // Note: We don't call "update". Hibernate detects the change
                // automatically when we commit (Dirty Checking).
            }

            em.getTransaction().commit();
            listDigitalMusics(em, "[Step 4] After Price Update:");

            // --- DELETE ---
//            System.out.println("\n[Step 5] Deleting the DigitalMusic...");
//            em.getTransaction().begin();
//
//            DigitalMusicEntity digitalMusicToDelete = em.find(DigitalMusicEntity.class, myDigitalMusic.getId());
//            if (digitalMusicToDelete != null) {
//                em.remove(digitalMusicToDelete); // Tells Hibernate to delete the row
//            }
//
//            em.getTransaction().commit();
//            listDigitalMusics(em, "[Step 6] Final Inventory (should be empty):");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Always close your resources
            em.close();
            emf.close();
        }
    }

    /**
     * Helper method to run a JPQL query and print results
     */
    private static void listDigitalMusics(EntityManager em, String header) {
        System.out.println("\n" + header);
        List<DigitalMusicEntity> digitalMusics = em.createQuery("SELECT v FROM DigitalMusicEntity v", DigitalMusicEntity.class).getResultList();
        if (digitalMusics.isEmpty()) {
            System.out.println("No digitalMusics found in database.");
        } else {
            for (DigitalMusicEntity v : digitalMusics) {
                System.out.println("Found: " + v);
            }
        }
    }
}