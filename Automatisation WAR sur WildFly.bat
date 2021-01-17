@echo off
color 0a
title Mise a jour...
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
echo 	Une mise a jour est actuellement disponible.
echo.
echo.
echo ษอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออป
echo บ  Voulez-vous mettre le bat a jour ? 1 = oui, 0 = non      บ
echo ศอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออผ
set /p saisie=}
echo.

if /i %saisie% NEQ 1 goto majAnnulee
echo.
echo อออออ Dernier rempart : la conf sera mise a zro อออออ
echo 	- Confirmez en appuyant sur une touche.
echo 	- Annulez en fermant cette fenetre & pause>nul
echo.
echo 	Tlchargement en cours...
copy /y %batLivreSurCommun% %racineBatChezVous%
echo.
echo 	Flicitations ! Votre .bat est a jour !
start "bat" "%racineBatChezVous%\%nomBat%"
exit

:majAnnulee
echo.
echo 	La mise a jour a bien t annule ;-)
echo.

:variables
REM ##############################################################################
REM ######################### Definir ces variables !!! ##########################
REM ##############################################################################
REM ####################################  |  #####################################
REM #################################### \|/ #####################################
REM ####################################  *  #####################################
REM ##############################################################################

REM ####################### emplacement du WAR UN #######################
set TARGET_UN=D:\utilisateurs\l.duperron\Documents\NetBeansProjects\BasicSpringbootProject\target\
set WAR_UN=basic-0.0.1-SNAPSHOT

REM ########################## emplacement du WAR DEUX ##########################
set TARGET_DEUX=D:\utilisateurs\l.duperron\Documents\NetBeansProjects\BasicSpringBootClient\target\
set WAR_DEUX=springboot-restclient

REM ############## emplacement du r้pertoire deployments de Wildfly ##############
set WILDFLY=d:\programs\portable\wildfly-12.0.0.Final\standalone\deployments\

REM ##############################################################################
REM ####################################  ^  #####################################
REM #################################### /|\ #####################################
REM ####################################  |  #####################################
REM ##############################################################################

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
echo.

set nbReussites=0
if /i %tentatives% EQU 1 goto warUN
if /i %tentatives% GTR 1 goto warDEUX

:nettoyage
title %livrable% WAR on Wildfly

echo 	NETTOYAGE DU REPERTOIRE DEPLOIEMENTS DE WILDFLY
echo.
del %WILDFLY%*.* /f /q /s

echo.
if /i %tentatives% EQU 0 goto relancerScript

echo.
echo ษอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออป
echo บ  Appuyez sur une touche pour livrer les WARs sur Wildfly  บ
echo ศอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออผ &pause>nul
echo.
echo ---
echo ------
echo ---------
echo ------------
echo ---------------
echo ------------------
echo -------------------------------------------------------------
echo ------------------
echo ---------------
echo ------------
echo ---------
echo ------
echo ---
echo.

REM Nombre de relances
set relances=0
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
echo 	Vous en etes a la %relances% relance.
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
if /i %relances% EQU 3 goto finDeploiement
echo 	Une relance de dploiement peut rgler ce probleme.
echo 	Au-dela de 2 relances inefficaces, j'abandonne.
echo 	Vous en etes a la %relances% relance.
goto DEUX
:echecServeurDEUX
echo 	L'erreur rencontre est interne au serveur et ne peut etre
echo 	rgle par une simple relance.
echo 	Consulter les logs du serveur pourrait donner plus d'infos.
goto finDeploiement
REM CAS NOMINAL
:reussiteDEUX
echo ษอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออป
echo บออออ Le WAR DEUX a t dploy avec grand succes ! ออออออออบ
echo ศอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออผ
set /a nbReussites=%nbReussites%+1

:finDeploiement
echo.
REM Si au moins un WAR a reussi sur cette iteration, on incremente.
if /i %nbReussites% NEQ 0 set /a livrable=%livrable%+1

REM Si, dans la premiere iteration, 2 WARs n'ont pas ete deployes, on affiche ce message
if /i %livrable% EQU 1 if /i %nbReussites% NEQ 2 echo อออออ Des erreurs ont t rencontres. ออออออออออออออออออออออ
REM Sinon on affiche celui-la
if /i %livrable% EQU 1 if /i %nbReussites% EQU 2 echo อออออ Les WARs ont t livrs sur le serveur ! ออออออออออออออ

REM Si, dans les iterations suivantes, 1 WAR n'a pas ete deploye, on affiche ce message
if /i %livrable% GTR 1 if /i %nbReussites% NEQ 1 echo อออออ Des erreurs ont t rencontres. ออออออออออออออออออออออ
REM Sinon on affiche celui-la
if /i %livrable% GTR 1 if /i %nbReussites% EQU 1 echo อออออ Le WAR a t livr sur le serveur ! อออออออออออออออออออ

REM On affiche ce message pour moins de 10 livraisons reussies.
if /i %livrable% GTR 1 if /i %livrable% LSS 10 if /i %nbReussites% EQU 1 echo อออออ %livrable% livraisons ont t russies aujourd'hui ! อออออออออออ
REM On affiche celui-ci pour plus de 10 livraisons reussies.
if /i %livrable% GTR 9 if /i %livrable% LSS 100 if /i %nbReussites% EQU 1 echo อออออ %livrable% livraisons ont t russies aujourd'hui !
REM Celui qui fait afficher ce message dans un cadre "normal" merite mon respect. Mais peut-on parler de normalite a un tel niveau de determination ?
if /i %livrable% GTR 99 if /i %nbReussites% EQU 1 echo อออออ %livrable% livraisons ont t russies aujourd'hui ! Comment t'as fait ? o__O

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
echo บ  Appuyez sur une touche pour deployer le WAR sur Wildfly  บ
echo ศอออออออออออออออออออออออออออออออออออออออออออออออออออออออออออผ &pause>nul
cls
set /a tentatives=%tentatives%+1
goto 1