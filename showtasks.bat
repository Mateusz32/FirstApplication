call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openBrowser
echo.
echo runcrud.bat has error
goto fail

:openBrowser
start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Cannot open Chrome & http://localhost:8080/crud/v1/task/getTasks
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo showtasks is finished.