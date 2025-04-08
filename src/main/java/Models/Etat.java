package Models;

public class Etat {
    private int idPrevision;
    private String libellePrevision;
    private int montantPrevision;
    private int totalDepenses;
    private int reste;

    public Etat(int idPrevision, String libellePrevision, int montantPrevision, int totalDepenses) {
        this.idPrevision = idPrevision;
        this.libellePrevision = libellePrevision;
        this.montantPrevision = montantPrevision;
        this.totalDepenses = totalDepenses;
        this.reste = montantPrevision - totalDepenses;
    }

    public int getIdPrevision() {
        return idPrevision;
    }

    public void setIdPrevision(int idPrevision) {
        this.idPrevision = idPrevision;
    }

    public String getLibellePrevision() {
        return libellePrevision;
    }

    public void setLibellePrevision(String libellePrevision) {
        this.libellePrevision = libellePrevision;
    }

    public int getMontantPrevision() {
        return montantPrevision;
    }

    public void setMontantPrevision(int montantPrevision) {
        this.montantPrevision = montantPrevision;
    }

    public int getTotalDepenses() {
        return totalDepenses;
    }

    public void setTotalDepenses(int totalDepenses) {
        this.totalDepenses = totalDepenses;
    }

    public int getReste() {
        return reste;
    }

    public void setReste(int reste) {
        this.reste = reste;
    }

    // Getters
   
}