package csd214.bookstore.jpa;

import csd214.bookstore.entities.BookEntity;
import csd214.bookstore.entities.VinylEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.UUID;

public class JpaVinylApp {
    public static void main(String[] args) {
        // 1. Initialize the Engine
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore-pu");
        EntityManager em = emf.createEntityManager();

        try {
            // --- CREATE ---
            System.out.println("\n[Step 1] Creating a new Vinyl...");
            em.getTransaction().begin();
            // String name, double price, String make, String model, int year, int mileage, double towingCapacity
            VinylEntity myVinyl = new VinylEntity(
                    UUID.randomUUID().toString(),
                    "VINYL",
                    67.48,
                    "Clube da Esquina",
                    "Milton Nascimento e Lo Borges",
                    "MPB",
                    1972,
                    "Black",
                    11);
            em.persist(myVinyl); // Tells Hibernate to save the object
            em.getTransaction().commit();
            System.out.println("Vinyl saved with Database ID: " + myVinyl.getId());

            // --- READ (List All) ---
            listVinyls(em, "[Step 2] Current Inventory:");

            // --- UPDATE (Find & Edit) ---
            System.out.println("\n[Step 3] Editing Vinyl Price...");
            em.getTransaction().begin();

            // We use the ID to find the specific record
            VinylEntity vinylToEdit = em.find(VinylEntity.class, myVinyl.getId());
            if (vinylToEdit != null) {
                vinylToEdit.setPrice(49.99); // Change the Java field
                // Note: We don't call "update". Hibernate detects the change
                // automatically when we commit (Dirty Checking).
            }

            em.getTransaction().commit();
            listVinyls(em, "[Step 4] After Price Update:");

            // --- DELETE ---
            System.out.println("\n[Step 5] Deleting the Vinyl...");
            em.getTransaction().begin();

            VinylEntity vinylToDelete = em.find(VinylEntity.class, myVinyl.getId());
            if (vinylToDelete != null) {
                em.remove(vinylToDelete); // Tells Hibernate to delete the row
            }

            em.getTransaction().commit();
            listVinyls(em, "[Step 6] Final Inventory (should be empty):");

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
    private static void listVinyls(EntityManager em, String header) {
        System.out.println("\n" + header);
        List<VinylEntity> vinyls = em.createQuery("SELECT v FROM VinylEntity v", VinylEntity.class).getResultList();
        if (vinyls.isEmpty()) {
            System.out.println("No vinyls found in database.");
        } else {
            for (VinylEntity v : vinyls) {
                System.out.println("Found: " + v);
            }
        }
    }
}