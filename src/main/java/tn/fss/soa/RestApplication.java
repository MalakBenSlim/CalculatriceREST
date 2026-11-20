package tn.fss.soa;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Active JAX-RS : l'API est exposée sous le chemin /api.
 * Jersey détecte automatiquement les ressources (@Path) du projet.
 */
@ApplicationPath("/api")
public class RestApplication extends Application {
}
