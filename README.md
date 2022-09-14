################################################
# FIN DU PROJET : 02/08/2018
# Ceci explique la pile logicielle dat�e
# REPRISE DU PROJET : 14/09/2022
################################################

Wilkommen !

Vous trouverez sur ce dossier l'essentiel (et plus encore) pour cr�er un projet RESTful avec le framework springboot sous l'IDE Netbeans, et ce � partir du tutoriel springresttemplate.pdf (que je vous invite � parcourir le temps de l'installation/copie/d�compression/waiting for task ending/BSOD/ins�rerCauseD'AttenteIci)


La pile logicielle retenue pour l'ex�cution de ce projet est ~Netbeans 8.1 (fonctionne sous 8.2)~, Wilfly 12.0.0, PostGreSQL 9.2, ~springboot 2.0.1, jdk 1.8.0.172~ et maven 3.3.9.
UPDATE : D�sormais le code va fonctionner avec Netbeans 15, openjdk 13.0.7 et springboot 3.1

Je vais consid�rer votre JDK bien install� et votre IDE configur� sur elle, ainsi qu'avec maven.

################################################
# Tout part donc de Netbeans.
################################################

Dans Tools/Plugins, onglet Downloaded, vous pourrez ajouter le plugin 1520272942_nb-springboot-plugin-2.0.nbm
Il lui permet de cr�er/ouvrir des projets bas�s sur springboot.

Les projets BasicSpringbootProject et BasicSpringbootClient sont dans des conteneurs .7z, extrayez-les dans votre workspace netbeans.

De l�, la mise � jour de votre repository Maven va devenir rapidement une n�cessit�, le fichier repository.7z est l� pour �a.
1. Copiez-le � l'emplacement de votre dossier "repository" actuel
2. Clic-droit "extraire ici" avec 7-zip
3. Fusionner les dossiers
4. Ne pas r��crire sur l'existant (pour gain de temps)
5. Dans tout ce r�pertoire repository, recherche puis suppression des fichiers remote.repositories s'il en reste.
6. Ajouter le r�pertoire example dans le r�pertoire com de votre repository
7. R��crire par-dessus les fichiers d�j� pr�sents
8. re-indexer le repository.

L�, vous devriez �tre pr�t � compiler le projet sans heurt.

################################################
# Vagrant
################################################

1.	Extrayez le contenu du r�pertoire vagrant.7z dans le r�pertoire de ce projet.
2.	Dans le fichier Vagrantfile, ligne 15, remplacer la valeur actuelle de config.vm.box par "centos-7.4-IAP7.box"
3.	Dans le vagrant\.vagrant\machines\postgresql\virtualbox, ajouter les fichiers contenus dans le r�pertoire virtualbox qui est fourni
4. 	T�l�chargez le fichier "centos-7.4-IAP7.box" � partir du NAS (outils/Isos/boxes_vagrant) et copiez-le � la racine du vagrantfile

De l�, avec BillsKitchen, vagrant up, �a fait la provision et le caf�, vous �tes pr�t � avoir une base PostGreSQL fonctionnelle.

Vous avez (enfin) r�ussi � faire afficher "Build Success" � votre IDE, vous commencez � lire un ou 2 fichiers.java en vous demandant � quoi peut bien servir toutes ces annotations, et vous �tes d�cid� � lancer votre projet.
Probl�me. Je vous ai vendu Wildfly mais j'en ai pas encore parl�.

... hum.

################################################
# Wildfly
################################################

1. 	T�l�chargez wildfly-12.0.0.Final.zip � partir du NAS (outils/Applications portables) puis d�compressez le fichier dans D:\programs\portable
	Dans Netbeans, onglet Services (accessible par le menu Window s'il n'est pas affich�), clic droit sur Servers puis (Add Server)
2.	Choisissez WildFly Application Server, mettez le nom que vous voulez
3.	Indiquez le r�pertoire D:\programs\portable\wildfly-12.0.0.Final
	Normalement la ligne Server Configuration se remplit automatiquement avec D:\programs\portable\wildfly-12.0.0.Final\standalone\configuration\standalone-full.xml
4. 	Laissez le domaine, le host et le port tels quels, ou alors sachez ce que vous faites


De l�, votre serveur est pr�t � �tre lanc�. Mais il n'est pas pr�t � d�ployer votre projet !

################################################
# CE QUI SUIT NE CONCERNE QUE LES PLUS BRAVES,
# CEUX QUI ONT REUSSI A FAIRE AFFICHER "BUILD 
# SUCCESS" � LEUR IDE
################################################

Je pr�cise que les 3 proc�dures qui suivent ne sont pas du tout id�ales mais que si je savais faire mieux je le ferais

Je vous ai d�j� dit qu'un .bat c'est bien ?
A ceux � qui je l'ai pas dit et/ou qui pensent le contraire (amis linuxiens rappel : y a pas de gnome-terminal ou de konsole sous Windaube), je vous invite � zapper cette partie et lire plus loin comment se prendre la t�te

A ceux � qui je l'ai dit et/ou qui sont curieux...

################################################
# Voici mon consumer de d�ploiement du war du
# projet sur WildFly :
################################################

private Consumer<SpringBoot> consumerDeploiement = this.contenuConsumerDeploiement(this.vousBossez, this.serveurPasLance);

private void contenuConsumerDeploiement(final Object pVousBossez, final Object pServeurPasLance) {
	TANT_QUE (pVousBossez != FALSE)
		SI (pServeurPasLance != FALSE)
		ALORS ========= Lancement du serveur WildFly depuis Netbeans (Services, Servers, clic droit sur le serveur Wildfly, Start)
		FIN_SI
		======= Ex�cution du script bat, ou appui sur une touche avec le focus sur la fen�tre du .bat pour livrer le .war sur le wildfly
	FIN_TANT_QUE
}

#C'estPasFaux

Id�alement faudrait voir si on peut les d�ployer par la touche "debug" de l'IDE, pas essay�.

################################################
# Execution du projet
################################################

http://localhost:8080/client/index.xhtml

Quoi... les pages sont vides ?! Le projet est tout cass� ?! o_O
Meuh non : actuellement la base de donn�es est cens�e �tre vide, il vous faut la remplir

Mais pour remplir une base faut d�j� avoir une connexion �tablie
J'imagine donc que la box vagrant est d�j� mont�e et provisionn�e, parce que sinon vous allez avoir du mal � faire marcher ce qui suit :

################################################
# Se connecter � la BDD
################################################

1. 	Dans NETBEANS, Services, clic droit sur Databases, New Connection
2. 	Choisir PostGreSQL 9.2, normalement le driver est d�j� s�lectionn�
	Sinon, il faut le driver nomm� postgresql-9.2-1002.jdbc4.jar situ� normalement dans le r�pertoire ide\modules\ext\ de votre NetBeans.
3.	Driver Name = PostGreSQL
	Host = 192.168.56.102
	Port = 5432
	Database = springboot_db (le sch�ma springboot_test_db n'est pas utilis� mais il est pr�sent)
	User Name = postgres
	Password = postgres
	JDBC URL = jdbc:postgresql://192.168.56.102:5432/springboot_db
	
	Testez la connexion, si tout va bien cliquez sur Finish

################################################
# Remplir la BDD
################################################

La BDD est tap�e par le projet BasicSpringbootProject, ce qui suit le concerne, lui et seulement lui.

1. 	Sous Netbeans, onglet Files, ouvrir src/main/resources/data.sql
2. 	CTRL+A puis CRTL+C
1. 	Dans Netbeans, onglet Services, Databases, clic droit sur la base de donn�es springboot puis "Execute Command"
2.	CTRL+V puis CTRL+MAJ+E

################################################
# Execution du projet bis
################################################

http://localhost:8080/client/index.xhtml

L� normalement vous savez tout faire, il ne vous reste plus qu'� faire briller ce projet avec du code propre \o/

Bon dev :-)

Ah et pour ce qui est de savoir quoi lire/comprendre/modifier/pastoucher du code que vous avez devant les yeux... une pr�sentation qui va bien devrait �tre faite par mes soins prochainement.

################################################
# NOTES :
################################################
- Pour d�finir la cl� SSH utilis�e pour le repo local :
$ git config --add --local core.sshCommand 'ssh -i /home/lucky/.ssh/springbootKeycloak'
