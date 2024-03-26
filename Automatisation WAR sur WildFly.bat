@echo off
color 0a
title Mise … jour...
mode con: cols=159 lines=62

REM ## emplacement du .bat chez vous, que je puisse faire le test de mise เ jour #
set racineBatChezVous=%cd%
REM ## Nom du .bat
set nomBat=Automatisation WAR sur Wildfly.bat

REM ############## emplacement du WAR livre sur le serveur #######################
set batLivreSurCommun="R:\COMMUN\DPN\BasicSpringBootProject\%nomBat%"

for %%a in ("%racineBatChezVous%\%nomBat%") do set datecible=%%~ta
for %%a in (%batLivreSurCommun%) do set datesource=%%~ta

REM ## on enleve l'espace entre date et temps
set datecible=%datecible: =-%
set datesource=%datesource: =-%


REM On v้rifie que le fichier source est bien plus r้cent que le cible
set "sdatecible=%datecible:~6,4%%datecible:~3,2%%datecible:~0,2%
set "sdatesource=%datesource:~6,4%%datesource:~3,2%%datesource:~0,2%
if %sdatecible% LSS %sdatesource% if %sdatecible% NEQ %sdatesource% goto majdispo
goto variables

:majdispo
echo 	Une mise … jour est actuellement disponible.
echo.
echo.
echo ษอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออป
echo บ  Voulez-vous mettre le bat … jour ? 1 = oui, 0 = non      บ
echo ศอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออผ
set /p saisie=}
echo.

if /i %saisie% NEQ 1 goto majAnnulee
echo.
echo อออออ Dernier rempart : la conf sera mise … zro อออออ
echo 	- Confirmez en appuyant sur une touche.
echo 	- Annulez en fermant cette fenetre & pause>nul
echo.
echo 	Tlchargement en cours...
copy /y %batLivreSurCommun% %racineBatChezVous%
echo.
echo 	Flicitations ! Votre .bat est … jour !
start "bat" "%racineBatChezVous%\%nomBat%"
exit

:majAnnulee
echo.
echo 	La mise … jour a bien t annule ;-)
echo.









:variables
REM ##############################################################################
REM ######################### D้finir ces variables !!! ##########################
REM ##############################################################################
REM ####################################  |  #####################################
REM #################################### \|/ #####################################
REM ####################################  *  #####################################
REM ##############################################################################

REM ########################## emplacement du WAR UN #############################
set TARGET_UN=D:\developpement\java\TutoSpringBoot_et_REST\BasicSpringbootApiRest\target\
set WAR_UN=apiRest-0.0.1-SNAPSHOT

REM ########################## emplacement du WAR DEUX ###########################
set TARGET_DEUX=D:\developpement\java\TutoSpringBoot_et_REST\BasicSpringbootApiRest2\target\
set WAR_DEUX=apiRest2-0.0.1-SNAPSHOT

REM ########################## emplacement du WAR TROIS ##########################
set TARGET_TROIS=D:\developpement\java\TutoSpringBoot_et_REST\BasicSpringBootClientWeb\target\
set WAR_TROIS=springboot-restclient

REM ############## emplacement du r้pertoire deployments de Wildfly ##############
set WILDFLY=D:\programs\portable\wildfly-11.0.0.Final\standalone\deployments\

REM ##############################################################################
REM ####################################  ^  #####################################
REM #################################### /|\ #####################################
REM ####################################  |  #####################################
REM ##############################################################################















cls
title Deploiement des WARs sur Wildfly

REM Nombre d'iterations total
set tentatives=0
REM Nombre d'iterations reussies
set livrable=0

:1
echo.
echo  `.oyyys`/s:`.++` `.o+` `-o/`    `+o-`      `:o-` `/s'/yhhhys-`  +yyyyo. .++.`     `.+o.` `/o-`./-o`    `.oo.`    `.oyyys-` `.++.` `/o.` `++`ohhhys/  `.+so-`
echo  -shyyyo`smo`-hh.`:sdho-`/do`   `.yd:`      `od+``.ym'ymdyyso-` .ymyssho--hh-`    `-ohds- `/hs-.+oh.`  `-sddo-`  `.odysso-` -ohho-`.yd:``-dd`dNmhyo: `-sdhmh/`
echo  odo`   `odo.-dh-:hh/ods`odo`   `.yd:`      `sd+``.sd'yd+.`     .yd/..yd/-hh-`   `-sh++hy- `/yyoyo.`  `-yh++hy-` `:dy.`   `-sh++hy-.ydo\o/dd`hd:`    `/s:.smo`
echo  /dy//:.`omh+omh.sd+..sd.odo`   `.yd-`      `sds/+/ym'yds::.    .ydo:/hy:-hh-`   `/ds..+do` `-hd:`    `+do..+d+` `:dh---:-`/do..+do-ymmmmddh`hdo:-`    `:hd/`
echo  ' `mmmm .NNNNd/`Nhssdms.ds`   `.`d:`       `ommmNmmm'hmmmd/.   .ymddhy-`-hd-`   `+mh++yms` `-hd-`    `omy//ydo` `/mdshdmh-/mdsshms'hm'hd.hd`NNmh.`     -hd/`
echo  `.--/hd`smy/+dd`yNmmmmm`sds``.-`.hd:``-.`  `omdhhhdm'hdo-.     .yd+-..``-hd-` -..+mmmmmms` `-hd-`    `ommhhdmo` `:dh--/dh-/mmdddms'hd:..-hd`dm+:.`    `+do`
echo  `---/dh`odo.-dh.sdo--yd`ody::yd:-hd+:+dy.` `odo..:hm'yms/:-.   .yd:`   `-hd+:ods.+dy--ods` `-hh-`    `ods--odo` `-hh::+dy--ds--ods'yd:``.hd`hd/--.`   `/h/`
echo  oddmmmo.od+`-hh:sd/`.sd`omNmmNd:-hNNmmmh.` `od+``.hm'ymNNmmh:  .sd:`   `-dNmmmms./do. `do. `-hh-`    `+do.`+d+`  `:hdddh:.\do``/do-yh-``.yh`hNmmmdo`   `-``
echo  .-:::-.`.-.``-.`.-` `.-..::/::-``-://::-`  `.-.` `.-..:///:-   `.-'`    `-:///:.`.-.``.-.`  `..`     `.-.``.-.`   `.---.` .-.` `-.`..`  `..`-:::::.`  `/y/`
pause
set nbReussites=0

title %livrable% WAR on Wildfly

echo.

REM Nombre de relances
set relances=0

set /a tentatives=%tentatives%+1










:warUN

echo.
echo อออออ DEPLOIEMENT DU WAR UN... อออออออออออออออออออออ
echo.
echo 	NETTOYAGE DES ANCIENS FICHIERS DU WAR UN...
del %WILDFLY%%WAR_UN%.* /f /q /s
echo.
echo 	COPIE DU WAR SUR LE SERVEUR...
copy /y "%TARGET_UN%%WAR_UN%.war" "%WILDFLY%"
echo.
echo 	LE WAR EST EN TRAIN D'ETRE DEPLOYE, PATIENTEZ SVP...
:attenteUN
ping 127.0.0.1 -n 2 > NUL 2>&1
if exist %WILDFLY%%WAR_UN%.war if exist %WILDFLY%%WAR_UN%.war.deployed goto reussiteUN
if exist %WILDFLY%%WAR_UN%.war if exist %WILDFLY%%WAR_UN%.war.failed goto echecUN
if exist %WILDFLY%%WAR_UN%.war if exist %WILDFLY%%WAR_UN%.war.undeployed goto echecUN
if exist %WILDFLY%%WAR_UN%.war goto attenteUN
REM CAS D'ECHEC
:echecUN
echo ษอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออป
echo บออออ Le WAR UN n'a pas t dploy :-( ออออออออออออออออออออบ
echo ศอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออผ
if exist %WILDFLY%%WAR_UN%.war.failed goto echecServeurUN
REM Tentative de relance
set /a relances=%relances%+1
if /i %relances% EQU 3 goto warDEUX
echo 	Une relance de dploiement peut rgler ce probleme.
echo 	Au-dela de 2 relances inefficaces, j'abandonne.
echo 	Vous en etes … la %relances% relance.
goto warUN
:echecServeurUN
echo 	L'erreur rencontre est interne au serveur et ne peut etre
echo 	rgle par une simple relance.
echo 	Consulter les logs du serveur pourrait donner plus d'infos.
goto warDEUX
REM CAS NOMINAL
:reussiteUN
echo ษอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออป
echo บออออ Le WAR UN a t dploy avec grand succes ! ออออออออออบ
echo ศอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออผ
set /a nbReussites=1









:warDEUX
REM Nombre de relances
set relances=0
:DEUX
echo.
echo อออออ DEPLOIEMENT DU WAR DEUX... อออออออออออออออออออออออออออ
echo.
echo 	NETTOYAGE DES ANCIENS FICHIERS DU WAR DEUX...
del %WILDFLY%%WAR_DEUX%.* /f /q /s
echo.
echo 	COPIE DU WAR SUR LE SERVEUR...
copy /y "%TARGET_DEUX%%WAR_DEUX%.war" "%WILDFLY%"
echo.
echo 	LE WAR EST EN TRAIN D'ETRE DEPLOYE, PATIENTEZ SVP...
:attenteDEUX
ping 127.0.0.1 -n 2 > NUL 2>&1
if exist %WILDFLY%%WAR_DEUX%.war if exist %WILDFLY%%WAR_DEUX%.war.deployed goto reussiteDEUX
if exist %WILDFLY%%WAR_DEUX%.war if exist %WILDFLY%%WAR_DEUX%.war.failed goto echecDEUX
if exist %WILDFLY%%WAR_DEUX%.war if exist %WILDFLY%%WAR_DEUX%.war.undeployed goto echecDEUX
if exist %WILDFLY%%WAR_DEUX%.war goto attenteDEUX
REM CAS D'ECHEC
:echecDEUX
echo ษอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออป
echo บออออ Le WAR DEUX n'a pas t dploy :-( ออออออออออออออออออบ
echo ศอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออผ
if exist %WILDFLY%%WAR_DEUX%.war.failed goto echecServeurDEUX
REM Tentative de relance
set /a relances=%relances%+1
if /i %relances% EQU 3 goto warTROIS
echo 	Une relance de dploiement peut rgler ce probleme.
echo 	Au-dela de 2 relances inefficaces, j'abandonne.
echo 	Vous en etes … la %relances% relance.
goto DEUX
:echecServeurDEUX
echo 	L'erreur rencontre est interne au serveur et ne peut etre
echo 	rgle par une simple relance.
echo 	Consulter les logs du serveur pourrait donner plus d'infos.
goto warTROIS
REM CAS NOMINAL
:reussiteDEUX
echo ษอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออป
echo บออออ Le WAR DEUX a t dploy avec grand succes ! ออออออออบ
echo ศอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออผ
set /a nbReussites=%nbReussites%+1









:warTROIS
REM Nombre de relances
set relances=0
:TROIS
echo.
echo อออออ DEPLOIEMENT DU WAR TROIS... อออออออออออออออออออออออออออ
echo.
echo 	NETTOYAGE DES ANCIENS FICHIERS DU WAR TROIS...
del %WILDFLY%%WAR_TROIS%.* /f /q /s
echo.
echo 	COPIE DU WAR SUR LE SERVEUR...
copy /y "%TARGET_TROIS%%WAR_TROIS%.war" "%WILDFLY%"
echo.
echo 	LE WAR EST EN TRAIN D'ETRE DEPLOYE, PATIENTEZ SVP...
:attenteTROIS
ping 127.0.0.1 -n 2 > NUL 2>&1
if exist %WILDFLY%%WAR_TROIS%.war if exist %WILDFLY%%WAR_TROIS%.war.deployed goto reussiteTROIS
if exist %WILDFLY%%WAR_TROIS%.war if exist %WILDFLY%%WAR_TROIS%.war.failed goto echecTROIS
if exist %WILDFLY%%WAR_TROIS%.war if exist %WILDFLY%%WAR_TROIS%.war.undeployed goto echecTROIS
if exist %WILDFLY%%WAR_TROIS%.war goto attenteTROIS
REM CAS D'ECHEC
:echecTROIS
echo ษอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออป
echo บออออ Le WAR TROIS n'a pas t dploy :-( อออออออออออออออออบ
echo ศอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออผ
if exist %WILDFLY%%WAR_TROIS%.war.failed goto echecServeurTROIS
REM Tentative de relance
set /a relances=%relances%+1
if /i %relances% EQU 3 goto finDeploiement
echo 	Une relance de dploiement peut rgler ce probleme.
echo 	Au-dela de 2 relances inefficaces, j'abandonne.
echo 	Vous en etes … la %relances% relance.
goto TROIS
:echecServeurTROIS
echo 	L'erreur rencontre est interne au serveur et ne peut etre
echo 	rgle par une simple relance.
echo 	Consulter les logs du serveur pourrait donner plus d'infos.
goto finDeploiement
REM CAS NOMINAL
:reussiteTROIS
echo ษอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออป
echo บออออ Le WAR TROIS a t dploy avec grand succes ! อออออออบ
echo ศอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออผ
set /a nbReussites=%nbReussites%+1







:finDeploiement
goto relancerScript

echo.
REM Si au moins un WAR a reussi sur cette iteration, on incremente.
if /i %nbReussites% NEQ 0 set /a livrable=%livrable%+1
REM Si les 2 WARs n'ont pas ้t้ deploy้s, on affiche ce message
if /i %nbReussites% EQU 0 echo อออออ Des erreurs ont t rencontres pour les 2 WARs :'-) ออ
REM Si 1 WAR n'a pas ete deploye, on affiche ce message
if /i %nbReussites% EQU 1 echo อออออ Des erreurs ont t rencontres pour un des WARs. อออออ
REM Si tous les WARs ont ้t้ d้ploy้s
if /i %nbReussites% EQU 2 echo อออออ Les WARs ont tous t livrs sur le serveur ! อออออออออ

REM On affiche ce message pour moins de 10 livraisons reussies.
if /i %livrable% GTR 1 if /i %livrable% LSS 10 if /i %nbReussites% EQU 1 echo อออออ %livrable% livraisons ont t russies aujourd'hui ! อออออออออออ
REM On affiche celui-ci pour plus de 10 livraisons reussies.
if /i %livrable% GTR 9 if /i %livrable% LSS 100 if /i %nbReussites% EQU 1 echo อออออ %livrable% livraisons ont t russies aujourd'hui !
REM On affiche celui-ci pour plus de 100 livraisons reussies.
if /i %livrable% GTR 99 if /i %nbReussites% EQU 1 echo อออออ %livrable% livraisons ont t russies aujourd'hui ! Tu as tout mon respect. Comment t'as fait pour atteindre un tel niveau de determination ? o__O

set /a ratio=%livrable%/%tentatives%

if %tentatives% NEQ 0 set /a ratio=((%livrable%*100)/(%tentatives%))
if %tentatives% EQU 0 set ratio=0
echo Vous en etes a votre %tentatives% tentative, cela donne un taux de %ratio% / 100 ;-)











:relancerScript
title %livrable% WAR on Wildfly
echo.

echo ษอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออป
echo บ  Ces actions ont t termines ce jour … %time%      บ
echo ฬอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออน
echo บ Appuyez sur une touche pour deployer les WARs sur Wildfly บ
echo ศอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออผ &pause>nul
cls
goto 1
