Installation du programme Proxibanquev2.

I)PREREQUIS 

1- Installer le JRE sur le poste de travail.
	--> Télécharger le JRE de JAVA:  "https://www.java.com/fr/download/"
	--> Installer JAVA.exe

2- Ajouter l'application JAVA dans les variables d'environnement.
	Suivez cette démarche:
	--> Panneau de configuration 	--> Système 	--> Paramètres système avancés
	--> Cliquez sur l'onglet: Paramètres système avancés 	--> Variables d'environnement
	--> Dans la fenêtre "Variables utilisateur": Cliquez sur "Nouvelle"
	--> Ajoutez dans "Nom de la variable": PATH
	--> Ajouter dans "Valeur de la variable" le chemin d'accès :
		 "C:\Program Files\Java\jre1.8.0_121\bin" 
	--> Cliquez sur OK


3- Installer l'application Wampserver64 sur le poste de travail
   --> Telecharger l'application wampserver64 sur "http://www.wampserver.com/"
   --> Installer et configurer l'application : 
	--> Lancer le serveur et aller dans pHpMyAdmin
   	--> se connecter avec l'identifiant "root" sans mot de passe
  	--> Créer une nouvelle base de données nommée "proxiv2" avec interclassement "utf8-general-ci"
  	--> Importer la base de données test "proxiv2.sql" qui se trouve dans le dossier d'installation ProxiV2VSME --> BDD 

4- Installer le serveur d'application apache-tomcat-9.0.0
   --> Telecharger le serveur apache-tomcat-9.0.0.M19.zip  sur "https://tomcat.apache.org/download-90.cgi" prendre la version : 
	Binary Distributions --> Core: --> zip 
   --> le dezipper et installer les fichiers à la racine du disque C:/


5- Mise en place du fichier .war sur le poste de travail.
   --> Placer le fichier .war à la racine du disque C:/


II) INSTALLATION DE PROXI V2

1- Lancement du serveur d'application TOMCAT 
   --> Lancer "startup.bat" depuis le dossier "C:\apache-tomcat-9.0.0.M19\bin"

2- Relever le numéro de port du serveur d'application TOMCAT dans le fichier "server.xml" dans le dossier "C:\apache-tomcat-9.0.0.M19\conf"
  --> il faudra remplacer NumPort par ce numero dans la suite du fichier d'installation suivant

3- Exécution du programme .war
	--> Ouvrer votre navigateur internet (chrome, internet explorer..)
	--> Entrer l'URL "http://localhost:NumPort/ProxiV2_VSME/index.html"

4- afin de tester les fonctionnalités, deux conseillers ont été crée, ci-dessous leur login :

(identifiant)
(mot de passe)

bourne
bourne

MEspuche
gtm
 
