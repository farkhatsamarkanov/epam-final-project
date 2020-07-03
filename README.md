# EPAM-Final-Project

Развертывание приложения:

1. Установить Tomcat Web Server (http://tomcat.apache.org/tomcat-8.5-doc/setup.html);
2. Установить MySQL Community Server (https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/) 
   Желательно выделить серверу MySQL порт localhost:3306, если нет, то придется поменять порт на нужный в файле приложения (dbconfig.properties)
3. Открыть и выполнить через MySQL Workbench SQL скрипты в папке sql_scripts по очереди: 01-create-user.sql, 02-student-tracker.sql, 03-create-login-table.sql
4. В папке репозитория открыть коммандную строку (для Windows) и выполнить команды "mvn compile" и "mvn package"
5. В появившейся папке target скопировать war файл student-tracker.war в дирректорию webapps корневого каталога tomcat
6. Запустить веб сервер (startup.bat)
7. В браузере открыть страницу localhost:{порт tomcat}/student-tracker

(Tomcat иногда выдает предупреждение о невозможности закрыть драйвер jdbc. У меня после помещения в папку lib корневого каталога tomcat файла библиотеки mysql-connector.jar предупреждения исчезли)