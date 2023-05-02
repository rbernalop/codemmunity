@echo off
if "%~1" == "" (
    echo Please indicate the directory where you want to create the hexagonal architecture structure.
    exit /b 1
)

set target_directory=%~1

mkdir "%target_directory%\domain"
mkdir "%target_directory%\domain\aggregate"
mkdir "%target_directory%\domain\value_object"
mkdir "%target_directory%\domain\port"
mkdir "%target_directory%\domain\entity"

mkdir "%target_directory%\infrastructure"
mkdir "%target_directory%\infrastructure\controller"

mkdir "%target_directory%\application"

echo The hexagonal architecture structure has been created in the directory: %target_directory%
