simple exemple de site avec contenu bidon juste pour avoir une idée du rendu.

Pour les modifs simples de layout, voir `site.xml`

Pour les modifs de css, voir `site.css`

A noter que si utilisation d'asciidoc, on ne bénéficie pas de layout du maven-site.


# Build

```bash
mvn clean package -Dmaven.test.skip exec:java -Dexec.mainClass="org.parisjug.Main" site:run
```

# Manage Application Context

Bon, parce que je n'ai pas voulu batailler avec l'applicationContext et parce que faire différentes configurations par rapport à l'env qui run était un peu overdesign, tout est en dur dans le fichier `ConstantUtils` et surtout nuisait à la visibilité du code, pour l'instant, il est setté pour githubpage. 

Pour des tests en local, il suffit de mettre `public static final String APPLICATION_CONTEXT = "foo-site";` à `public static final String APPLICATION_CONTEXT = "";`.
   

# Deploy sur github page

Mettre à jour son `.settings.xml`:

```xml
<settings>
  <servers>

  <server>
      <id>github</id>
      <username>xxx</username>
      <password>xxx</password>
  </server>
  </servers>
</settings>
```

Activer le profil `deploy` et lancer :

```bash
mvn clean package -Dmaven.test.skip exec:java -Dexec.mainClass="org.parisjug.Main" site -Pdeploy
```

 
# Deployer un eventbrite

__!! In Progress !!__

Pour l'instant voir: `MainEventbrite`

Positionner la variable systeme `EVENTBRITE_OAUTH_PERSONAL_TOKEN` avec le jeton Oauth2 eventbrite qui va bien.

Lancer le `main()`. 

__Ne pas oublier de mettre à jour le yaml avec l'id eventbrite remonté__

__TODO__ : 

* faire plus propre ;)
* Si `main` il y a, faire une bonne récupération des paramètres ;)
* Automatiser la publication pour ts les événements futurs si ils ont les infos minimales et si ils n'ont pas déjà été créer? Si c'est le cas, faire un check de modifications avec ce qui a déjà été publié et le ré-émettre?
* Automatiser la mise à jour du Yaml? (désolé, j'ai pas réussi... :'( ) 

__ATTENTION__:
pour mettre des tag HTML, il faut qu'il n'y a pas de caratere avant le ligne
au caratère "&" dans les description

# Besoins Model

http://freemarker.org/docs/ref_directive_if.html

## Infos Speakers

* nom
* prenom
* [tweet]
* [list<url>]
* [description]
* [list talks ]
* [photo]

## Infos Talks

* titre
* date (au format yyyy/mm/dd par example 2013/2/12)
* heureDebut
* heureFin
* description
* [list url  (slideshare/...)]
* [list speaker ]


## Infos Soiree

* titre
* date
* description
* heureDebutPause
* heureFinPause
* [list talks ]
* [sponsorBuffet]
* [flickR url]
* lieu
* [eventbrite]

## Info Sponsors

* nom
* sponsoringyear
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


