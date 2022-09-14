################################################
# FIN DU PROJET : 02/08/2018
# Ceci explique la pile logicielle datée
# REPRISE DU PROJET : 14/09/2022
################################################

Wilkommen !

Vous trouverez sur ce dossier l'essentiel (et plus encore) pour créer un projet RESTful avec le framework springboot sous l'IDE Netbeans, et ce à partir du tutoriel springresttemplate.pdf (que je vous invite à parcourir le temps de l'installation/copie/décompression/waiting for task ending/BSOD/insérerCauseD'AttenteIci)


La pile logicielle retenue pour l'exécution de ce projet est ~Netbeans 8.1 (fonctionne sous 8.2)~, Wilfly 12.0.0, PostGreSQL 9.2, ~springboot 2.0.1, jdk 1.8.0.172~ et maven 3.3.9.
UPDATE : Désormais le code va fonctionner avec Netbeans 15, openjdk 13.0.7 et springboot 3.1

Je vais considérer votre JDK bien installé et votre IDE configuré sur elle, ainsi qu'avec maven.

################################################
# Tout part donc de Netbeans.
################################################

Dans Tools/Plugins, onglet Downloaded, vous pourrez ajouter le plugin 1520272942_nb-springboot-plugin-2.0.nbm
Il lui permet de créer/ouvrir des projets basés sur springboot.

Les projets BasicSpringbootProject et BasicSpringbootClient sont dans des conteneurs .7z, extrayez-les dans votre workspace netbeans.

De là, la mise à jour de votre repository Maven va devenir rapidement une nécessité, le fichier repository.7z est là pour ça.
1. Copiez-le à l'emplacement de votre dossier "repository" actuel
2. Clic-droit "extraire ici" avec 7-zip
3. Fusionner les dossiers
4. Ne pas réécrire sur l'existant (pour gain de temps)
5. Dans tout ce répertoire repository, recherche puis suppression des fichiers remote.repositories s'il en reste.
6. Ajouter le répertoire example dans le répertoire com de votre repository
7. Réécrire par-dessus les fichiers déjà présents
8. re-indexer le repository.

Là, vous devriez être prêt à compiler le projet sans heurt.

################################################
# Vagrant
################################################

1.	Extrayez le contenu du répertoire vagrant.7z dans le répertoire de ce projet.
2.	Dans le fichier Vagrantfile, ligne 15, remplacer la valeur actuelle de config.vm.box par "centos-7.4-IAP7.box"
3.	Dans le vagrant\.vagrant\machines\postgresql\virtualbox, ajouter les fichiers contenus dans le répertoire virtualbox qui est fourni
4. 	Téléchargez le fichier "centos-7.4-IAP7.box" à partir du NAS (outils/Isos/boxes_vagrant) et copiez-le à la racine du vagrantfile

De là, avec BillsKitchen, vagrant up, ça fait la provision et le café, vous êtes prêt à avoir une base PostGreSQL fonctionnelle.

Vous avez (enfin) réussi à faire afficher "Build Success" à votre IDE, vous commencez à lire un ou 2 fichiers.java en vous demandant à quoi peut bien servir toutes ces annotations, et vous êtes décidé à lancer votre projet.
Problème. Je vous ai vendu Wildfly mais j'en ai pas encore parlé.

... hum.

################################################
# Wildfly
################################################

1. 	Téléchargez wildfly-12.0.0.Final.zip à partir du NAS (outils/Applications portables) puis décompressez le fichier dans D:\programs\portable
	Dans Netbeans, onglet Services (accessible par le menu Window s'il n'est pas affiché), clic droit sur Servers puis (Add Server)
2.	Choisissez WildFly Application Server, mettez le nom que vous voulez
3.	Indiquez le répertoire D:\programs\portable\wildfly-12.0.0.Final
	Normalement la ligne Server Configuration se remplit automatiquement avec D:\programs\portable\wildfly-12.0.0.Final\standalone\configuration\standalone-full.xml
4. 	Laissez le domaine, le host et le port tels quels, ou alors sachez ce que vous faites


De là, votre serveur est prêt à être lancé. Mais il n'est pas prêt à déployer votre projet !

################################################
# CE QUI SUIT NE CONCERNE QUE LES PLUS BRAVES,
# CEUX QUI ONT REUSSI A FAIRE AFFICHER "BUILD 
# SUCCESS" À LEUR IDE
################################################

Je précise que les 3 procédures qui suivent ne sont pas du tout idéales mais que si je savais faire mieux je le ferais

Je vous ai déjà dit qu'un .bat c'est bien ?
A ceux à qui je l'ai pas dit et/ou qui pensent le contraire (amis linuxiens rappel : y a pas de gnome-terminal ou de konsole sous Windaube), je vous invite à zapper cette partie et lire plus loin comment se prendre la tête

A ceux à qui je l'ai dit et/ou qui sont curieux...

################################################
# Voici mon consumer de déploiement du war du
# projet sur WildFly :
################################################

private Consumer<SpringBoot> consumerDeploiement = this.contenuConsumerDeploiement(this.vousBossez, this.serveurPasLance);

private void contenuConsumerDeploiement(final Object pVousBossez, final Object pServeurPasLance) {
	TANT_QUE (pVousBossez != FALSE)
		SI (pServeurPasLance != FALSE)
		ALORS ========= Lancement du serveur WildFly depuis Netbeans (Services, Servers, clic droit sur le serveur Wildfly, Start)
		FIN_SI
		======= Exécution du script bat, ou appui sur une touche avec le focus sur la fenêtre du .bat pour livrer le .war sur le wildfly
	FIN_TANT_QUE
}

#C'estPasFaux

Idéalement faudrait voir si on peut les déployer par la touche "debug" de l'IDE, pas essayé.

################################################
# Execution du projet
################################################

http://localhost:8080/client/index.xhtml

Quoi... les pages sont vides ?! Le projet est tout cassé ?! o_O
Meuh non : actuellement la base de données est censée être vide, il vous faut la remplir

Mais pour remplir une base faut déjà avoir une connexion établie
J'imagine donc que la box vagrant est déjà montée et provisionnée, parce que sinon vous allez avoir du mal à faire marcher ce qui suit :

################################################
# Se connecter à la BDD
################################################

1. 	Dans NETBEANS, Services, clic droit sur Databases, New Connection
2. 	Choisir PostGreSQL 9.2, normalement le driver est déjà sélectionné
	Sinon, il faut le driver nommé postgresql-9.2-1002.jdbc4.jar situé normalement dans le répertoire ide\modules\ext\ de votre NetBeans.
3.	Driver Name = PostGreSQL
	Host = 192.168.56.102
	Port = 5432
	Database = springboot_db (le schéma springboot_test_db n'est pas utilisé mais il est présent)
	User Name = postgres
	Password = postgres
	JDBC URL = jdbc:postgresql://192.168.56.102:5432/springboot_db
	
	Testez la connexion, si tout va bien cliquez sur Finish

################################################
# Remplir la BDD
################################################

La BDD est tapée par le projet BasicSpringbootProject, ce qui suit le concerne, lui et seulement lui.

1. 	Sous Netbeans, onglet Files, ouvrir src/main/resources/data.sql
2. 	CTRL+A puis CRTL+C
1. 	Dans Netbeans, onglet Services, Databases, clic droit sur la base de données springboot puis "Execute Command"
2.	CTRL+V puis CTRL+MAJ+E

################################################
# Execution du projet bis
################################################

http://localhost:8080/client/index.xhtml

Là normalement vous savez tout faire, il ne vous reste plus qu'à faire briller ce projet avec du code propre \o/

Bon dev :-)

Ah et pour ce qui est de savoir quoi lire/comprendre/modifier/pastoucher du code que vous avez devant les yeux... une présentation qui va bien devrait être faite par mes soins prochainement.

################################################
# NOTES :
################################################
- Pour définir la clé SSH utilisée pour le repo local :
$ git config --add --local core.sshCommand 'ssh -i /home/lucky/.ssh/springbootKeycloak'
