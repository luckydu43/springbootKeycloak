@echo off
color 0a
title Netbeans
set compteur=0

:1
taskkill /im netbeans64.exe /f /t
taskkill /im sqldeveloper64W.exe /f /t 
taskkill /im java.exe /f /t

start "netbeans" "D:\programs\portable\netbeans\netbeans-8.1\bin\netbeans64.exe"

cls
echo.
echo Nombre de fois ou je me suis calm : %compteur%
echo.
echo ษออออออออออออออออออออออออออออออออออออออออออออออออออออออป
echo บ      Pour redmarrer TRANQUILLEMENT Netbeans...      บ
echo ศออออออออออออออออออออออออออออออออออออออออออออออออออออออผ
echo.          
pause

set /a compteur=%compteur%+1
title Netbeans : %compteur%
goto 1