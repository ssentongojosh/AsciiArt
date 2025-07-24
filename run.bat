@echo off
setlocal ENABLEDELAYEDEXPANSION

:: ========================
:: Config paths
:: ========================
set JAVAFX_LIB=C:\DevTools\openjfx-17.0.16_windows-x64_bin-sdk\javafx-sdk-17.0.16\lib
set SRC_DIR=src\main\java
set OUT_DIR=out
set JAR_NAME=AsciiArt.jar
set MANIFEST=manifest.txt

:: ========================
:: Prompt for args (with defaults)
:: ========================
set /p IMG="Enter image path [default: boyhacker.png]: "
if "%IMG%"=="" set IMG=boyhacker.png

set /p MAPPER="Enter mapping algorithm [default: linear]: "
if "%MAPPER%"=="" set MAPPER=linear

set /p WIDTH="Enter width [default: 1024]: "
if "%WIDTH%"=="" set WIDTH=1024

set /p ASPECT="Enter aspect ratio [default: 0.35]: "
if "%ASPECT%"=="" set ASPECT=0.35

echo.
echo 🛠️ Compiling Java source files...

for /f "delims=" %%f in ('dir /s /b %SRC_DIR%\*.java') do (
    set FILES=!FILES! %%f
)

if exist %OUT_DIR% rmdir /s /q %OUT_DIR%
mkdir %OUT_DIR%

javac --module-path "%JAVAFX_LIB%" --add-modules javafx.controls,javafx.fxml -d %OUT_DIR% %FILES%

if %ERRORLEVEL% NEQ 0 (
    echo 💥 Compilation failed.
    exit /b 1
)

echo.
echo 📦 Creating JAR...

jar --create --file %JAR_NAME% --manifest %MANIFEST% -C %OUT_DIR% .

if %ERRORLEVEL% NEQ 0 (
    echo 💣 JAR creation failed.
    exit /b 1
)

echo.
echo 🚀 Running app with:
echo    Image: %IMG%
echo    Mapper: %MAPPER%
echo    Width: %WIDTH%
echo    Aspect: %ASPECT%
echo.

java --module-path "%JAVAFX_LIB%" --add-modules javafx.controls,javafx.fxml -jar %JAR_NAME% %IMG% %MAPPER% %WIDTH% %ASPECT%

endlocal
pause
