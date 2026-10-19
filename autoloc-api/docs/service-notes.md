# Atelier 4 : Couche Service, IoC, injection de dépendances

## A. Modes d'injection comparés

| Critère | Injection par constructeur | Injection par attribut (`@Autowired`) |
|---|---|---|
| Champ `final` possible | Oui | Non |
| Dépendance visible dans la signature | Oui (paramètre du constructeur) | Non (champ interne) |
| Utilisable hors conteneur (`new` sans dépendance null) | Oui | Non (dépendance null) |
| Signalé par SonarQube | Non | Oui, règle `java:S6813` |

## B. Services

| Service | Dépendance injectée | Mode | Justification |
|---|---|---|---|
| `AgenceServiceImpl` | `IAgenceRepository` | Constructeur (`@RequiredArgsConstructor` + `private final`) | Champ final, dépendance typée par interface |
| `EmployeServiceImpl` | `IEmployeRepository` | Constructeur (`@RequiredArgsConstructor` + `private final`) | Champ final, dépendance typée par interface |
| `VehiculeServiceImpl` | `IVehiculeRepository` | Constructeur (`@RequiredArgsConstructor` + `private final`) | Champ final, dépendance typée par interface |
| `EquipementServiceImpl` | `IEquipementRepository` | Constructeur (`@RequiredArgsConstructor` + `private final`) | Champ final, dépendance typée par interface |
| `ClientServiceImpl` | `IClientRepository` | Constructeur (`@RequiredArgsConstructor` + `private final`) | Champ final, dépendance typée par interface |
| `ReservationServiceImpl` | `IReservationRepository` | Constructeur (`@RequiredArgsConstructor` + `private final`) | Champ final, dépendance typée par interface |
| `ContratServiceImpl` | `IContratRepository` | Constructeur (`@RequiredArgsConstructor` + `private final`) | Champ final, dépendance typée par interface |
| `MaintenanceServiceImpl` | `IMaintenanceRepository` | Constructeur (`@RequiredArgsConstructor` + `private final`) | Champ final, dépendance typée par interface |
| `PaiementServiceImpl` | `IPaiementRepository` | Constructeur (`@RequiredArgsConstructor` + `private final`) | Lecture seule : expose uniquement `findById` et `findAll` |

## C. Messages d'erreur (étape 3)

### Message 1 : `NoSuchBeanDefinitionException`

- **Exception** : `NoSuchBeanDefinitionException`, enveloppée dans `UnsatisfiedDependencyException`.
- **Bean concerné** : `IContratService`, demandé par `ContratController`.
- **Causes** : `@Service` oublié sur `ContratServiceImpl`, ou classe placée hors du package `tn.esprit.autoloc` (donc non détectée par le scan).
- **Correction** : ajouter `@Service` sur `ContratServiceImpl`, ou déplacer la classe dans le package scanné.

### Message 2 : `NoUniqueBeanDefinitionException`

- **Exception** : `NoUniqueBeanDefinitionException`.
- **Beans concernés** : `emailNotificateur` et `smsNotificateur`, demandés par `NotificationController`.
- **Cause** : deux beans du même type existent, Spring ne sait pas lequel injecter.
- **Correction 1** : `@Primary` placé **sur la classe** du bean par défaut (par exemple `EmailNotificateur`).
- **Correction 2** : `@Qualifier("emailNotificateur")` placé **sur le paramètre du constructeur** de `NotificationController`.

### Message 3 : dépendance circulaire

- **Exception** : `BeanCurrentlyInCreationException` (dépendance circulaire).
- **Beans concernés** : `clientServiceImpl` et `reservationServiceImpl`.
- **Cause** : chaque service a besoin de l'autre dans son constructeur. Pour créer le premier il faut le second, et inversement : aucun des deux ne peut être créé en premier, le cycle est donc insoluble avec l'injection par constructeur.
- **Piste de conception** : extraire la responsabilité commune dans un troisième service, utilisé par les deux.

## D. Anomalies SonarQube for IDE

| Règle | Explication | Correction |
|---|---|---|
| `java:S6813` | Injection par attribut : dépendance cachée, champ non `final` | `@RequiredArgsConstructor` + `private final` |
| `java:S1128` | Import inutile (`java.util.Map`) dans `AgenceServiceImpl` | Import supprimé |
| `java:S112` | Exception générique (`RuntimeException`) levée dans `ContratServiceImpl.findById` | Remplacée par `ResourceNotFoundException` |

## E. Questions de compréhension

1. **Où est le `new` de `ContratServiceImpl` et qui l'exécute ?** Le conteneur Spring l'exécute au démarrage, après avoir détecté `@Service` grâce au scan. Aucun `new` n'est écrit dans le code.
2. **Pourquoi un contrôleur dépend-il de `IContratService` et non de `ContratServiceImpl` ?** Dépendre d'une abstraction permet de changer d'implémentation ou de fournir un remplaçant sans modifier le contrôleur.
3. **Pourquoi un service singleton doit-il rester sans état ?** Il est partagé par tous les threads. Un attribut modifiable (par exemple un « contrat courant ») serait écrasé par une requête concurrente : un utilisateur pourrait modifier le contrat d'un autre.
4. **Pourquoi `update` charge-t-il l'entité existante avant `save` ?** Passer directement l'objet reçu écraserait toutes les colonnes et, avec `orphanRemoval`, pourrait vider la collection de paiements.
5. **Différences et lecture seule.** `@Component` annote une classe que l'on possède ; `@Bean` s'écrit sur une méthode d'une classe `@Configuration`, pour une classe que l'on ne peut pas annoter (bibliothèque, JDK). `@Primary` se pose sur la classe et désigne le candidat par défaut ; `@Qualifier` se pose à l'injection et choisit un bean par son nom. `IPaiementService` est en lecture seule car les paiements passent par le Contrat (cascade).