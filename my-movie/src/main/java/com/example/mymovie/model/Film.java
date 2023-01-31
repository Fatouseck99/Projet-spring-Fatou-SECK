package com.example.mymovie.model;

public class Film {
    private String titre;
    private String realisateur;
    private Acteur acteurPrincipal;
    private Date dateDeSortie;

    public Film() {
    }

    public Film(String titre, String realisateur, Acteur acteurPrincipal, Date dateDeSortie) {
        super();
        this.titre = titre;
        this.realisateur = realisateur;
        this.acteurPrincipal = acteurPrincipal;
        this.dateDeSortie = dateDeSortie;
        acteurPrincipal.addFilm(this);
    }

    public String gettitre() {
        return titre;
    }

    public void settitre(String titre) {
        this.titre = titre;
    }

    public String getrealisateur() {
        return realisateur;
    }

    public void setrealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public Acteur getacteurPrincipal() {
        return acteurPrincipal;
    }

    public void setacteurPrincipal(Acteur acteurPrincipal) {
        this.acteurPrincipal = acteurPrincipal;
        acteurPrincipal.addFilm(this);
    }

    public Date getdateDeSortie() {
        return dateDeSortie;
    }

    public void setdateDeSortie(Date dateDeSortie) {
        this.dateDeSortie = dateDeSortie;
    }

    @Override
    public String toString() {
        return "Film [titre" + titre + ", realisateur=" + realisateur
                + ", ActeurPincipal=" + acteurPrincipal + ", dateDeSortie=" + dateDeSortie + "]";
    }
}