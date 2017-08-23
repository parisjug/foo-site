simple exemple de site avec contenu bidon juste pour avoir une idée du rendu.

Pour les modifs simples de layout, voir `site.xml`

Pour les modifs de css, voir `site.css`

A noter que si utilisation d'asciidoc, on ne bénéficie pas de layout du maven-site.

# Build

```bash
mvn clean package -Dmaven.test.skip exec:java -Dexec.mainClass="org.parisjug.Main" site:run
```

# Besoins Model

## Infos Speakers

* nom
* prenom
* [tweet]
* [list<url>]
* [description]
* [list<talks>]
* [photo]

## Infos Talks

* titre
* date
* heureDebut
* heureFin
* description
* [list<url> (slideshare/...)]
* [list<speaker>]


## Infos Soiree

* titre
* date
* description
* heureDebutPause
* heureFinPause
* [list<talks>]
* [sponsorBuffet]
* [flickR url]
* lieu
* [eventbrite]

## Info Sponsors

* nom
* description
* logo
* [autre?]

# Besoins fonctionnels

## Templating

Afin d'éviter la duplication d'infos, vu ques les infos se recoupent et qu'on est sur un site static, il faut qu'on puisse avoir une page par speaker/soiree mais également une page de speakers/soiree globale.

Du coup, faire un combo avec du templating pour générer une page avec ts les speakers/soirée en fonction d'une nomenclature à faire sur la structure projet.

Eventuellement cela peut être fait avec du json.

A noter qu'il peut être plus simple d'avoir une seule soirée/talk/speaker par type de fichiers afin de permettre le linkage direct sans avoir à ouvrir un fichier énorme... En outre, ca permettra au speaker de se retrouver plus facilement.

## Automatisation

### Eventbrite

Prévoir la génération de l'event qui nécessite également le rendering de la page pour le "copier/coller".

Une fois l'event créé, mettre à jour la page 

### Envoie mail

Prévoir l'envoie de l'email qui nécessite également le rendering de la page pour le "copier/coller".

### Envoie Tweet


