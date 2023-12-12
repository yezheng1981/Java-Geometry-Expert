@rem This script is a Windows translation of the script 4-create-resources.sh.

@echo off
setlocal enabledelayedexpansion

call :readSettings

echo Supported languages: %SUPPORTED_LANGUAGES%

for %%i in (%SUPPORTED_LANGUAGES%) do (
    echo Processing language: %%i
    msgfmt --java2 -d ..\classes -r i18n.Messages -l %%i po\%%i.po
)

goto :eof

:readSettings
for /f "delims=" %%a in (settings.conf) do (
    set "line=%%a"
    set "line=!line!#"
    set "settingName="
    set "settingValue="

    for /f "tokens=1,* delims==" %%b in ("!line!") do (
        set "settingName=%%b"
        set "settingValue=%%c"
    )

    if defined settingName (
        @rem We assume the line is longer than expected, by having an extra \r, FIXME
        set "!settingName!=!settingValue:~1,-2!"
        )
    )
)

goto :eof

