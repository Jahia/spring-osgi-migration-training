package fr.sample.jahia.training.services.beans;

/**
 * City
 * <pre>
 * {
 *     "nom": "L'Abergement-Clémenciat",
 *     "code":"01001",
 *     "codesPostaux":["01400"]
 * }
 * </pre>
 *
 * @author tleclere
 */
public class City {
    private String nom;
    private String code;
    private String[] codesPostaux;

    public String getNom() {
        return nom;
    }

    public String getCode() {
        return code;
    }

    public String[] getCodesPostaux() {
        return codesPostaux;
    }
}
